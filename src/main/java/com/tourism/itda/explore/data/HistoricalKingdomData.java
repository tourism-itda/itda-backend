package com.tourism.itda.explore.data;

import com.tourism.itda.explore.enums.Kingdom;

import java.util.Map;

public class HistoricalKingdomData {

    public record KingdomInfo(
            String name,
            String timePeriod,
            String description,
            String imageUrl
    ) {}

    public static final Map<Kingdom, KingdomInfo> KINGDOMS = Map.ofEntries(

            Map.entry(
                    Kingdom.GOGURYEO,
                    new KingdomInfo(
                            "고구려",
                            "기원전 37년 ~ 668년",
                            "한반도 북부와 만주 일대를 중심으로 성장한 고대 국가로, 광활한 영토를 바탕으로 강력한 군사력을 갖추었습니다.",
                            "https://dimg.donga.com/wps/NEWS/IMAGE/2019/10/13/97856030.2.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.BAEKJE,
                    new KingdomInfo(
                            "백제",
                            "기원전 18년 ~ 660년",
                            "한반도 서남부를 중심으로 발전한 고대 국가로, 해상 교류와 문화 발전이 활발했으며 일본과 동아시아에 문화를 전파했습니다.",
                            "https://tour.chungnam.go.kr/thumbnail/trsrcn/920_TRSRCN_202507111101541149.JPG"
                    )
            ),

            Map.entry(
                    Kingdom.SILLA,
                    new KingdomInfo(
                            "신라",
                            "기원전 57년 ~ 935년",
                            "한반도 동남부에서 성장한 고대 국가로, 삼국을 통일하고 이후 통일신라로 발전했습니다.",
                            "https://i.namu.wiki/i/FlW1g-t0WhNQ2dKSiiaAEUPpMHkO4hP9DsAyhTmNMHXWGTNK2CVI_5oVnNwV0oDeGrimKua8KDJVUNxh-5TLxQ.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.GAYA,
                    new KingdomInfo(
                            "가야",
                            "42년 ~ 562년",
                            "한반도 남부 낙동강 유역을 중심으로 여러 소국이 연맹을 이루었던 국가로, 철 생산과 해상 교역이 발달했습니다.",
                            "https://img7.yna.co.kr/etc/inner/KR/2023/09/17/AKR20230917021100005_03_i_P4.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.UNIFIED_SILLA,
                    new KingdomInfo(
                            "통일신라",
                            "676년 ~ 935년",
                            "신라가 삼국 통일을 이룬 이후의 시기로, 불교문화와 예술이 크게 발전하며 신라 문화의 전성기를 이루었습니다.",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCCETiHbmOoyAsRqhd43AA4IlkGrAs06xmXTLZ2UYa-J6Av7fNB80zExc&s=10"
                    )
            ),

            Map.entry(
                    Kingdom.BALHAE,
                    new KingdomInfo(
                            "발해",
                            "698년 ~ 926년",
                            "고구려의 계승을 표방하며 만주와 한반도 북부를 중심으로 발전한 국가로, 당나라 및 주변 국가와 활발하게 교류했습니다.",
                            "https://mblogthumb-phinf.pstatic.net/MjAyMjAyMjdfMzAw/MDAxNjQ1OTU1NzQ4NDAw.QggNCRkyaa3a4ErF48I18gqwMSltI3RAc5gwTB10Llkg.h7hCfKvhuVTU5yWu80792HrUgJwfdGvL4WW69TDEveIg.JPEG.alskaeodhkd/3.jpg?type=w800"
                    )
            ),

            Map.entry(
                    Kingdom.LATER_GOGURYEO,
                    new KingdomInfo(
                            "후고구려",
                            "901년 ~ 918년",
                            "궁예가 건국한 후삼국 시대의 국가로, 철원과 송악 등을 중심으로 세력을 확장하며 고려 건국의 기반이 되었습니다.",
                            "https://i0.wp.com/wulinamu.com/wp-content/uploads/2020/02/IMG_8756-1.jpg?w=1160&ssl=1"
                    )
            ),

            Map.entry(
                    Kingdom.LATER_BAEKJE,
                    new KingdomInfo(
                            "후백제",
                            "900년 ~ 936년",
                            "견훤이 건국한 후삼국 시대의 국가로, 전라도 지역을 중심으로 세력을 확대하며 고려와 경쟁했습니다.",
                            "https://cdn.jjan.kr/data2/content/image/2023/10/09/.cache/512/20231009580188.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.GORYEO,
                    new KingdomInfo(
                            "고려",
                            "918년 ~ 1392년",
                            "왕건이 건국한 중세 국가로, 후삼국을 통일하고 약 470년 동안 한반도를 지배했습니다. 고려청자와 불교문화가 크게 발전했습니다.",
                            "https://ojsfile.ohmynews.com/STD_IMG_FILE/2007/1227/IE000850873_STD.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.JOSEON,
                    new KingdomInfo(
                            "조선",
                            "1392년 ~ 1897년",
                            "이성계가 건국한 왕조 국가로, 유교를 국가의 통치 이념으로 삼았습니다. 한글 창제와 다양한 학문 및 문화의 발전을 이루었습니다.",
                            "https://devin.aks.ac.kr/image/7fb47219-31a1-4dd9-bc41-d1a1bea62190?preset=orig"
                    )
            ),

            Map.entry(
                    Kingdom.KOREAN_EMPIRE,
                    new KingdomInfo(
                            "대한제국",
                            "1897년 ~ 1910년",
                            "조선의 국호를 대한제국으로 변경하고 근대 국가로의 전환을 추진했던 시기로, 광무개혁 등을 통해 근대화를 시도했습니다.",
                            "https://s.bizhankook.com/attachments/2026/05/c4a467ef92dc.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.JAPANESE_COLONY,
                    new KingdomInfo(
                            "일제강점기",
                            "1910년 ~ 1945년",
                            "일본 제국주의에 의해 국권을 상실하고 식민 지배를 받았던 시기로, 독립운동과 민족문화 수호를 위한 다양한 활동이 전개되었습니다.",
                            "https://onimg.nate.com/orgImg/hi/2018/03/11/cfe8c2da4ac041b8b69b1dcaa178c8c9.jpg"
                    )
            ),

            Map.entry(
                    Kingdom.FIRST_REPUBLIC_OF_KOREA,
                    new KingdomInfo(
                            "대한민국 제1공화국",
                            "1948년 ~ 1960년",
                            "대한민국 정부 수립 이후 제1공화국이 존속했던 시기로, 대한민국의 초기 국가 체제가 형성되었습니다.",
                            "https://flexible.img.hani.co.kr/flexible/normal/618/832/imgdb/original/2023/0901/20230901502733.webp"
                    )
            )
    );
}