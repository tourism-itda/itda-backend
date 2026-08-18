package com.tourism.itda.explore.data;

import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.explore.enums.PersonType;

import java.util.List;

public class HistoricalPersonData {

        public static final List<Person> PEOPLE = List.of(

                // ==================================================
                // 삼국 시대 - 고구려
                // ==================================================

                new Person(
                        "주몽",
                        "고구려 건국자",
                        Kingdom.GOGURYEO,
                        PersonType.KING
                ),
                new Person(
                        "광개토대왕",
                        "고구려의 영토를 크게 확장한 왕",
                        Kingdom.GOGURYEO,
                        PersonType.KING
                ),
                new Person(
                        "장수왕",
                        "고구려 전성기를 이끈 왕",
                        Kingdom.GOGURYEO,
                        PersonType.KING
                ),
                new Person(
                        "을지문덕",
                        "살수대첩을 이끈 고구려 장군",
                        Kingdom.GOGURYEO,
                        PersonType.GENERAL
                ),
                new Person(
                        "연개소문",
                        "고구려 말기의 권력자",
                        Kingdom.GOGURYEO,
                        PersonType.GENERAL
                ),

                // ==================================================
                // 삼국 시대 - 백제
                // ==================================================

                new Person(
                        "온조왕",
                        "백제 건국자",
                        Kingdom.BAEKJE,
                        PersonType.KING
                ),
                new Person(
                        "근초고왕",
                        "백제 전성기를 이끈 왕",
                        Kingdom.BAEKJE,
                        PersonType.KING
                ),
                new Person(
                        "무령왕",
                        "백제 중흥을 이끈 왕",
                        Kingdom.BAEKJE,
                        PersonType.KING
                ),
                new Person(
                        "성왕",
                        "백제의 사비 천도를 추진한 왕",
                        Kingdom.BAEKJE,
                        PersonType.KING
                ),
                new Person(
                        "계백",
                        "황산벌 전투를 이끈 백제 장군",
                        Kingdom.BAEKJE,
                        PersonType.GENERAL
                ),

                // ==================================================
                // 삼국 시대 - 신라
                // ==================================================

                new Person(
                        "박혁거세",
                        "신라 건국자",
                        Kingdom.SILLA,
                        PersonType.KING
                ),
                new Person(
                        "진흥왕",
                        "신라 영토 확장의 기반을 만든 왕",
                        Kingdom.SILLA,
                        PersonType.KING
                ),
                new Person(
                        "선덕여왕",
                        "신라 최초의 여왕",
                        Kingdom.SILLA,
                        PersonType.KING
                ),
                new Person(
                        "김유신",
                        "삼국통일을 이끈 신라 장군",
                        Kingdom.SILLA,
                        PersonType.GENERAL
                ),

                // ==================================================
                // 삼국 시대 - 가야
                // ==================================================

                new Person(
                        "김수로왕",
                        "금관가야 건국자",
                        Kingdom.GAYA,
                        PersonType.KING
                ),

                // ==================================================
                // 남북국 시대 - 통일신라
                // ==================================================

                new Person(
                        "문무왕",
                        "삼국통일을 완성한 왕",
                        Kingdom.UNIFIED_SILLA,
                        PersonType.KING
                ),
                new Person(
                        "신문왕",
                        "통일신라 왕권을 강화한 왕",
                        Kingdom.UNIFIED_SILLA,
                        PersonType.KING
                ),
                new Person(
                        "원효",
                        "신라의 대표적인 불교 사상가",
                        Kingdom.UNIFIED_SILLA,
                        PersonType.MONK
                ),
                new Person(
                        "의상",
                        "화엄종을 발전시킨 승려",
                        Kingdom.UNIFIED_SILLA,
                        PersonType.MONK
                ),

                // ==================================================
                // 남북국 시대 - 발해
                // ==================================================

                new Person(
                        "대조영",
                        "발해 건국자",
                        Kingdom.BALHAE,
                        PersonType.KING
                ),
                new Person(
                        "무왕",
                        "발해 영토 확장을 이끈 왕",
                        Kingdom.BALHAE,
                        PersonType.KING
                ),
                new Person(
                        "문왕",
                        "발해의 전성기를 이끈 왕",
                        Kingdom.BALHAE,
                        PersonType.KING
                ),

                // ==================================================
                // 후삼국 시대
                // ==================================================

                new Person(
                        "궁예",
                        "후고구려를 건국한 인물",
                        Kingdom.LATER_GOGURYEO,
                        PersonType.KING
                ),
                new Person(
                        "견훤",
                        "후백제 건국자",
                        Kingdom.LATER_BAEKJE,
                        PersonType.KING
                ),

                // ==================================================
                // 고려
                // ==================================================

                new Person(
                        "태조 왕건",
                        "고려 건국자",
                        Kingdom.GORYEO,
                        PersonType.KING
                ),
                new Person(
                        "광종",
                        "노비안검법과 과거제를 실시한 왕",
                        Kingdom.GORYEO,
                        PersonType.KING
                ),
                new Person(
                        "공민왕",
                        "개혁 정책을 추진한 왕",
                        Kingdom.GORYEO,
                        PersonType.KING
                ),
                new Person(
                        "서희",
                        "거란과의 외교 협상을 이끈 문신",
                        Kingdom.GORYEO,
                        PersonType.POLITICIAN
                ),
                new Person(
                        "강감찬",
                        "귀주대첩을 승리로 이끈 장군",
                        Kingdom.GORYEO,
                        PersonType.GENERAL
                ),
                new Person(
                        "최무선",
                        "화약 기술을 개발한 인물",
                        Kingdom.GORYEO,
                        PersonType.INVENTOR
                ),

                // ==================================================
                // 조선 - 왕
                // ==================================================

                new Person(
                        "태조",
                        "조선 제1대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "정종",
                        "조선 제2대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "태종",
                        "조선 제3대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "세종",
                        "조선 제4대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "문종",
                        "조선 제5대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "단종",
                        "조선 제6대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "세조",
                        "조선 제7대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "예종",
                        "조선 제8대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "성종",
                        "조선 제9대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "연산군",
                        "조선 제10대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "중종",
                        "조선 제11대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "인종",
                        "조선 제12대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "명종",
                        "조선 제13대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "선조",
                        "조선 제14대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "광해군",
                        "조선 제15대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "인조",
                        "조선 제16대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "효종",
                        "조선 제17대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "현종",
                        "조선 제18대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "숙종",
                        "조선 제19대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "경종",
                        "조선 제20대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "영조",
                        "조선 제21대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "정조",
                        "조선 제22대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "순조",
                        "조선 제23대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "헌종",
                        "조선 제24대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "철종",
                        "조선 제25대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "고종",
                        "조선 제26대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),
                new Person(
                        "순종",
                        "조선 제27대 왕",
                        Kingdom.JOSEON,
                        PersonType.KING
                ),

                // ==================================================
                // 조선 - 주요 인물
                // ==================================================

                new Person(
                        "이순신",
                        "임진왜란을 이끈 조선 장군",
                        Kingdom.JOSEON,
                        PersonType.GENERAL
                ),
                new Person(
                        "장영실",
                        "조선의 과학자",
                        Kingdom.JOSEON,
                        PersonType.INVENTOR
                ),
                new Person(
                        "허준",
                        "동의보감을 편찬한 의학자",
                        Kingdom.JOSEON,
                        PersonType.SCHOLAR
                ),
                new Person(
                        "정약용",
                        "실학자이자 개혁 사상가",
                        Kingdom.JOSEON,
                        PersonType.SCHOLAR
                ),
                new Person(
                        "이황",
                        "성리학자",
                        Kingdom.JOSEON,
                        PersonType.SCHOLAR
                ),
                new Person(
                        "이이",
                        "성리학자",
                        Kingdom.JOSEON,
                        PersonType.SCHOLAR
                ),

                // ==================================================
                // 개화기
                // ==================================================

                new Person(
                        "명성황후",
                        "조선의 왕비로 개화기와 대한제국 성립 과정에서 중요한 역할을 한 인물",
                        Kingdom.JOSEON,
                        PersonType.POLITICIAN
                ),
                new Person(
                        "고종",
                        "조선 제26대 왕이자 대한제국 초대 황제",
                        Kingdom.KOREAN_EMPIRE,
                        PersonType.KING
                ),

                // ==================================================
                // 일제강점기
                // ==================================================

                new Person(
                        "안중근",
                        "독립운동가",
                        Kingdom.JAPANESE_COLONY,
                        PersonType.INDEPENDENCE_ACTIVIST
                ),
                new Person(
                        "유관순",
                        "3·1 운동 독립운동가",
                        Kingdom.JAPANESE_COLONY,
                        PersonType.INDEPENDENCE_ACTIVIST
                ),
                new Person(
                        "김구",
                        "대한민국 임시정부 지도자",
                        Kingdom.JAPANESE_COLONY,
                        PersonType.POLITICIAN
                ),

                // ==================================================
                // 대한민국 제1공화국
                // ==================================================

                new Person(
                        "이승만",
                        "대한민국 제1대 대통령",
                        Kingdom.FIRST_REPUBLIC_OF_KOREA,
                        PersonType.POLITICIAN
                )
        );
}