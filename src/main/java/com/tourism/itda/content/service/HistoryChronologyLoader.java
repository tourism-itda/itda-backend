package com.tourism.itda.content.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 국사편찬위원회 '오늘의역사(연표)' XML을 애플리케이션 기동 시 1회 파싱해
 * 연도별 사건 목록을 메모리에 캐싱한다. (918~2008년, 약 15,580건)
 *
 * XML 구조: level1(년) > level2(월) > level3(일) > level4(사건)
 * 각 level4 안의 <mainTitle> 텍스트가 사건 제목이다.
 */
@Slf4j
@Component
public class HistoryChronologyLoader {

    private static final String XML_PATH = "korean-history-chronology.xml";

    // 한 인물의 재위/활동 기간이 길어도 프롬프트가 비대해지지 않도록 상한을 둔다.
    private static final int MAX_EVENTS = 60;

    public record ChronologyEvent(int year, int month, int day, String title) {}

    // year -> 해당 연도 사건 목록 (연/월/일 순 정렬 보장을 위해 TreeMap)
    private final Map<Integer, List<ChronologyEvent>> eventsByYear = new TreeMap<>();

    @PostConstruct
    public void load() {
        long start = System.currentTimeMillis();
        try (InputStream in = new ClassPathResource(XML_PATH).getInputStream()) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
            factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader reader = factory.createXMLStreamReader(in);

            int currentYear = 0, currentMonth = 0, currentDay = 0;
            boolean inMainTitle = false;
            boolean titleCaptured = false; // level4당 첫 mainTitle만 사용

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    switch (name) {
                        case "level1" -> currentYear = parseValue(reader);
                        case "level2" -> currentMonth = parseValue(reader);
                        case "level3" -> currentDay = parseValue(reader);
                        case "level4" -> titleCaptured = false;
                        case "mainTitle" -> {
                            if (!titleCaptured && currentDay > 0) {
                                inMainTitle = true;
                            }
                        }
                        default -> { }
                    }
                } else if (event == XMLStreamConstants.CHARACTERS && inMainTitle) {
                    String title = reader.getText().trim();
                    if (!title.isEmpty()) {
                        eventsByYear.computeIfAbsent(currentYear, k -> new ArrayList<>())
                                .add(new ChronologyEvent(currentYear, currentMonth, currentDay, title));
                        titleCaptured = true;
                    }
                    inMainTitle = false;
                } else if (event == XMLStreamConstants.END_ELEMENT) {
                    if ("mainTitle".equals(reader.getLocalName())) {
                        inMainTitle = false;
                    }
                }
            }
            reader.close();

            int total = eventsByYear.values().stream().mapToInt(List::size).sum();
            log.info("연표 XML 로드 완료: {}개 연도, {}건 사건 ({}ms)",
                    eventsByYear.size(), total, System.currentTimeMillis() - start);

        } catch (Exception e) {
            log.error("연표 XML 로드 실패: {}", e.toString());
        }
    }

    /**
     * startYear~endYear 구간의 사건을 연/월/일 순으로 반환한다.
     * 상한(MAX_EVENTS)을 넘으면 구간 전체에서 고르게 샘플링한다.
     */
    public List<ChronologyEvent> getEventsBetween(Integer startYear, Integer endYear) {
        if (startYear == null) return List.of();
        int end = (endYear != null) ? endYear : startYear;

        List<ChronologyEvent> collected = new ArrayList<>();
        for (int y = startYear; y <= end; y++) {
            List<ChronologyEvent> yearEvents = eventsByYear.get(y);
            if (yearEvents != null) collected.addAll(yearEvents);
        }

        if (collected.size() <= MAX_EVENTS) return collected;

        // 너무 많으면 구간 전체에서 균등 샘플링해 시대 흐름을 보존한다.
        List<ChronologyEvent> sampled = new ArrayList<>(MAX_EVENTS);
        double step = (double) collected.size() / MAX_EVENTS;
        for (int i = 0; i < MAX_EVENTS; i++) {
            sampled.add(collected.get((int) (i * step)));
        }
        return sampled;
    }

    public String toPromptText(List<ChronologyEvent> events) {
        if (events.isEmpty()) return "(연표 정보 없음)";
        StringBuilder sb = new StringBuilder();
        for (ChronologyEvent e : events) {
            sb.append(e.year()).append("년 ")
                    .append(e.month()).append("월 ")
                    .append(e.day()).append("일 - ")
                    .append(e.title()).append("\n");
        }
        return sb.toString();
    }

    private static int parseValue(XMLStreamReader reader) {
        String v = reader.getAttributeValue(null, "value");
        if (v == null) return 0;
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
