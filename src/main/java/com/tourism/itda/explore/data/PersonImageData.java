package com.tourism.itda.explore.data;

import com.tourism.itda.explore.entity.Person;
import com.tourism.itda.explore.enums.Kingdom;

import java.util.Map;

public final class PersonImageData {

    private PersonImageData() {
    }

    private record PersonImageKey(
            String name,
            Kingdom kingdom
    ) {
    }

    private static final Map<PersonImageKey, String> IMAGE_URLS = Map.ofEntries(

            // ==================================================
            // 삼국 시대 - 고구려
            // ==================================================

            Map.entry(new PersonImageKey("주몽", Kingdom.GOGURYEO), "https://i.namu.wiki/i/G8bA_oUzk0ErDCrw4n8E65CP3dmNQA_qbaB39v8edOwIvZB9EQshRzg4BDsCouNf-0fTmgSjV3229lkQwntmm9G50HbJg9aY8Jru7NXELeO2AX8Nz3pNZS8boSRovAie4g09r1MugsqWLqtlyIbkSw.webp"),
            Map.entry(new PersonImageKey("광개토대왕", Kingdom.GOGURYEO), "https://i.namu.wiki/i/KK-51B4NZjikVlk-V-GRLx44XxoU7VY9lz9ZcProgEy5HB9U5tJXOsgs4pXzBz9mhwGSwK4BIilDD0x82XVFu5qjkPcvlje9KbFl4IaeW7_FuVGP4FgtJIZeJg6dtU9hpgqcnWfa3ltlEM-A--pMJg.webp"),
            Map.entry(new PersonImageKey("장수왕", Kingdom.GOGURYEO), "https://www.imaeil.com/photos/2018/08/29/2018082913043994155_l.jpg"),
            Map.entry(new PersonImageKey("을지문덕", Kingdom.GOGURYEO), "https://i.namu.wiki/i/ydhwQOI6CI3CHyVAyRlqSPmQg9DzY3SoN2my-qhhecV1vl_yeORoYYQGsmeZSXBsGYe-5QtDxKuKPgYIhqTbjp2ogxs2x5T5PLOUVSbtPovN6WkglSsl1umMK2wF_TFhVXLuoJmzbPEPFEhMj4KGlg.webp"),
            Map.entry(new PersonImageKey("연개소문", Kingdom.GOGURYEO), "https://i.namu.wiki/i/NCnqYKIWFgzD9stBzndEGX8_nDmhgTEc2MJWvmZZFcV8Tol_GTejVH9u1M5Mr3_G36KfEKNRlW7wdkGEMPEEhJcMuZSIzMBx4a8ubdYmFiKIn5SS2W5cgBvFYrNktEz1kmOR3rEsIa--b84sXJ8V_A.jpg"),

            // ==================================================
            // 삼국 시대 - 백제
            // ==================================================

            Map.entry(new PersonImageKey("온조왕", Kingdom.BAEKJE), "https://i.namu.wiki/i/2Qr-gAPAEx3aVwTx1FnS7Ulj1QkBEk1LPYLUET0fTkEo3m_fm5NW349ZtRJhc_Oz-rRFBdCQCdb5qWYGj1GXVvfdUC4lKIlUKNGLVeH0kjvuuEi6l3gnQXFdfqkJ-o0M2WgbDL9QmkQCb65kmb_vyQ.webp"),
            Map.entry(new PersonImageKey("근초고왕", Kingdom.BAEKJE), "https://mblogthumb-phinf.pstatic.net/20101114_204/hwarm_1289716671842PRM0N_JPEG/%B1%D9%C3%CA%B0%ED%BF%D5_2.jpg?type=w420"),
            Map.entry(new PersonImageKey("무령왕", Kingdom.BAEKJE), "https://i.namu.wiki/i/SJe0hXF5kOcpNoYFI8cxj3IpUmZjBuhpX08-XcKk4_bjHNq9S1GQhPk-mG-6gha0MGNNHv_dTI439p65NeF3Iw2s_X4Zu2k4AGOGsYtP-rTDVInH9NEwFY3925-QnSYYDnP6KtGofkortjDUGLSO2A.webp"),
            Map.entry(new PersonImageKey("성왕", Kingdom.BAEKJE), "https://i.namu.wiki/i/ER7uJQvXxUByRh2YBHMnnPrUsBD7-s8nuSMToglfiN9Hv6gnWu_XVQQfGOuqe-qHypS4ecNqMd_oMDP_jSI8SlzaZufRHLJgrUgHPrBh_OE0cLmXc1ZA-ZVRTdzuNeuu-UB9DFm8jxyn1UiGZm4dfw.webp"),
            Map.entry(new PersonImageKey("계백", Kingdom.BAEKJE), "https://i.namu.wiki/i/OYDs9Ff3OAKe54XOnivjcTyia7aciHp3rkzrgu54bu4VSAL2AH-iC06K9dKmeTMYJakmMV2Hk1nIU8c7fqcfptHGQoTcSGnerz3TEC3OUdGU7fIXS0xicXVpO8uBjqnx91oiMSKGBLk7O9MvtGcFpg.webp"),

            // ==================================================
            // 삼국 시대 - 신라
            // ==================================================

            Map.entry(new PersonImageKey("박혁거세", Kingdom.SILLA), "https://i.namu.wiki/i/O3_k_VP52-MYlsW69ZxUbvjjQWukjjD87ibGMI_zdfbDolU5fUTPk62ozUdaUKrEOGJpKekHQF1EnZAn4wHluMTlIS4MOZSeO1TbU8VdAJCyCFw_S9CZ0-t0NDhcVKqYLRc6xJkNeJnTvW-t_-fcGg.webp"),
            Map.entry(new PersonImageKey("진흥왕", Kingdom.SILLA), "https://blog.kakaocdn.net/dna/mRGxo/btr5dqmEzxQ/AAAAAAAAAAAAAAAAAAAAAIX4X6FZsATLME93w49V-ChzXKp4lbDQZZCakUfTY4v7/img.jpg?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1788188399&allow_ip=&allow_referer=&signature=1bfITeEDXduRCqindvtDH3s38jM%3D"),
            Map.entry(new PersonImageKey("선덕여왕", Kingdom.SILLA), "https://i.namu.wiki/i/P4dw8tSk5kiZIAMCtvFLF9-B1nB4XY8m8ch903A6FVmKQEDqUgT2Tw3XEIIKe3JQzDS5yZOFFtlIPppAFa6diCRIiHJ8xAj1P-yKBUnUb81DvK1SHSohqhMBwCekix_5C-Gkamz6GCGPmI6K8k3M_Q.webp"),
            Map.entry(new PersonImageKey("김유신", Kingdom.SILLA), "https://i.namu.wiki/i/fg_poUJAcOuZTIJsvCzPq5xZzIxqXJWCQQnSsLaIFT26dOLioiAI-DXQGgN_dxm2DN8WiXOEcrNByNb-1lD4CVDvqceWJifVsY_VuWGJex-y0YEPckuIZSK1hQjwkyMTmqZr72aakSsslR2q5W1n-Q.webp"),

            // ==================================================
            // 삼국 시대 - 가야
            // ==================================================

            Map.entry(new PersonImageKey("김수로왕", Kingdom.GAYA), "https://i.namu.wiki/i/C4fgawu6p-_gygQ8ejVwAM9PBFNqqWKQJDUqV3XMv5kvCL0rdStQYhhIACw2No4gCw9xf0ntHt_TUhPbry_wrYog3zwT4GSIcFqpjgywRfZFItU78nhUtNmZfCQZzuTyIsQvuhylnkw_DQhLqRt3HA.webp"),

            // ==================================================
            // 통일신라
            // ==================================================

            Map.entry(new PersonImageKey("문무왕", Kingdom.UNIFIED_SILLA), "/images/persons/munmuwang-tomb.jpg"),
            Map.entry(new PersonImageKey("신문왕", Kingdom.UNIFIED_SILLA), "/images/persons/sinmunwang-tomb.jpg"),
            Map.entry(new PersonImageKey("원효", Kingdom.UNIFIED_SILLA), "https://i.namu.wiki/i/Zf03NlY-6b42k_vulSjns1LnLHy9IuF_oHTxkp6nUpXGf3HUI3n_v6Iq3uh0_q9rbVgzwG3Uh3EbhzKq1GEnz0VzAhamfTeJ3Bwh5h1yGGSzAfQ4VdAyeXnDFdrOyGtxaMErLNsEPjTQA_LGQx4SqA.webp"),
            Map.entry(new PersonImageKey("의상", Kingdom.UNIFIED_SILLA), "https://i.namu.wiki/i/Blnc8K-Xoup15e4wrIg8id45Y10XGoDERW0OohcmHl35fdNCyxVQkeRT_pK6ZFbRM0rO0cS6u6mA-mdi6MaBc28SqgU3bEoChNiDsndCZOsnwzmcIJYBhRipC1NR-R8e0HYHbCyVbNYY7lCdpu7B8w.jpg"),

            // ==================================================
            // 발해
            // ==================================================

            Map.entry(new PersonImageKey("대조영", Kingdom.BALHAE), "https://i.namu.wiki/i/NIAYbKPO_NsyI8rYqAED-yF6r7xXZrXL09h0fH95Fyd_CeDSJQe34BNX1jGvmMQDvk4IELLph2jN0stzSQsDVSdOmW_Sg6NK8QPXC582OPaDHKGcGWEEUkqbcne5VlY8r_MIySRm5LZIqftNU33bFQ.webp"),
            Map.entry(new PersonImageKey("무왕", Kingdom.BALHAE), "https://mblogthumb-phinf.pstatic.net/20140114_24/chefjhkim_1389695991874Qk7fb_JPEG/%BF%EB%B8%D3%B8%AE%B5%B92817.JPG?type=w420"),
            Map.entry(new PersonImageKey("문왕", Kingdom.BALHAE), "https://i.namu.wiki/i/u6oH7NscjsFMKRqJz2HAm2aYF18VgfpgmqpoyORwGT6t_dqEpp0c6wnKbCHchfvAagtAxklIfk6fyR4LnmD2OA.webp"),

            // ==================================================
            // 후삼국 시대
            // ==================================================

            Map.entry(new PersonImageKey("궁예", Kingdom.LATER_GOGURYEO), "https://i.namu.wiki/i/ln-9Kj24iTFiGGD8B6wLoNrnohaJTLmeEHrAGHMApft4tCcKGKQOdRQpD1-VJOmfsFa2_nYmulxFuvDykhGMj6tpP_sk5G8C5zD_zvV_wYkriZjxKjhDoYHTAfbWXrGC2zhw6z5rAVtQfYivCFByjA.webp"),
            Map.entry(new PersonImageKey("견훤", Kingdom.LATER_BAEKJE), "https://i.namu.wiki/i/eV5tB4LOtCh2GfMn3DCW3uuWW76ynuHRpqsr_5gWd7kWLwon3i1_Un6dxU4Pa13V0sbD_gvd3XtKzSRekDsA8naxTopjE5ASToKgwmECLZjH0TbRDAYJ_bggWIgbsymB5X1B8BdDCjO60dVS1KwY9Q.webp"),

            // ==================================================
            // 고려
            // ==================================================

            Map.entry(new PersonImageKey("태조 왕건", Kingdom.GORYEO), "/images/persons/taejo-wang-geon.png"),
            Map.entry(new PersonImageKey("광종", Kingdom.GORYEO), "/images/persons/gwangjong.png"),
            Map.entry(new PersonImageKey("공민왕", Kingdom.GORYEO), "/images/persons/gongminwang.png"),
            Map.entry(new PersonImageKey("서희", Kingdom.GORYEO), "/images/persons/seo-hui.png"),
            Map.entry(new PersonImageKey("강감찬", Kingdom.GORYEO), "/images/persons/gang-gamchan.png"),
            Map.entry(new PersonImageKey("최무선", Kingdom.GORYEO), "/images/persons/choe-museon.png"),
            // ==================================================
            // 조선 - 왕
            // ==================================================

            Map.entry(new PersonImageKey("태조", Kingdom.JOSEON), "/images/persons/taejo.png"),
            Map.entry(new PersonImageKey("정종", Kingdom.JOSEON), "/images/persons/jeongjong.png"),
            Map.entry(new PersonImageKey("태종", Kingdom.JOSEON), "/images/persons/taejong.png"),
            Map.entry(new PersonImageKey("세종", Kingdom.JOSEON), "/images/persons/sejong.png"),
            Map.entry(new PersonImageKey("문종", Kingdom.JOSEON), "/images/persons/munjong.png"),
            Map.entry(new PersonImageKey("단종", Kingdom.JOSEON), "/images/persons/danjong.png"),
            Map.entry(new PersonImageKey("세조", Kingdom.JOSEON), "/images/persons/sejo.png"),
            Map.entry(new PersonImageKey("예종", Kingdom.JOSEON), "/images/persons/yejong.png"),
            Map.entry(new PersonImageKey("성종", Kingdom.JOSEON), "/images/persons/seongjong.png"),
            Map.entry(new PersonImageKey("연산군", Kingdom.JOSEON), "/images/persons/yeonsangun.png"),
            Map.entry(new PersonImageKey("중종", Kingdom.JOSEON), "/images/persons/jungjong.png"),
            Map.entry(new PersonImageKey("인종", Kingdom.JOSEON), "/images/persons/injong.png"),
            Map.entry(new PersonImageKey("명종", Kingdom.JOSEON), "/images/persons/myeongjong.png"),
            Map.entry(new PersonImageKey("선조", Kingdom.JOSEON), "/images/persons/seonjo.png"),
            Map.entry(new PersonImageKey("광해군", Kingdom.JOSEON), "/images/persons/gwanghaegun.png"),
            Map.entry(new PersonImageKey("인조", Kingdom.JOSEON), "/images/persons/injo.png"),
            Map.entry(new PersonImageKey("효종", Kingdom.JOSEON), "/images/persons/hyojong.png"),
            Map.entry(new PersonImageKey("현종", Kingdom.JOSEON), "/images/persons/hyeonjong.png"),
            Map.entry(new PersonImageKey("숙종", Kingdom.JOSEON), "/images/persons/sukjong.png"),
            Map.entry(new PersonImageKey("경종", Kingdom.JOSEON), "/images/persons/gyeongjong.png"),
            Map.entry(new PersonImageKey("영조", Kingdom.JOSEON), "/images/persons/yeongjo.png"),
            Map.entry(new PersonImageKey("정조", Kingdom.JOSEON), "/images/persons/jeongjo.png"),
            Map.entry(new PersonImageKey("순조", Kingdom.JOSEON), "/images/persons/sunjo.png"),
            Map.entry(new PersonImageKey("헌종", Kingdom.JOSEON), "/images/persons/heonjong.png"),
            Map.entry(new PersonImageKey("철종", Kingdom.JOSEON), "/images/persons/cheoljong.png"),
            Map.entry(new PersonImageKey("고종", Kingdom.JOSEON), "/images/persons/gojong.png"),
            Map.entry(new PersonImageKey("순종", Kingdom.JOSEON), "/images/persons/sunjong.png"),

// ==================================================
// 조선 - 주요 인물
// ==================================================

            Map.entry(new PersonImageKey("이순신", Kingdom.JOSEON), "/images/persons/yi-sun-sin.png"),
            Map.entry(new PersonImageKey("장영실", Kingdom.JOSEON), "/images/persons/jang-yeong-sil.png"),
            Map.entry(new PersonImageKey("허준", Kingdom.JOSEON), "/images/persons/heo-jun.png"),
            Map.entry(new PersonImageKey("정약용", Kingdom.JOSEON), "/images/persons/jeong-yak-yong.png"),
            Map.entry(new PersonImageKey("이황", Kingdom.JOSEON), "/images/persons/yi-hwang.png"),
            Map.entry(new PersonImageKey("이이", Kingdom.JOSEON), "/images/persons/yi-i.png"),

// ==================================================
// 개화기 / 대한제국
// ==================================================

            Map.entry(new PersonImageKey("명성황후", Kingdom.JOSEON), "/images/persons/myeongseong-hwanghu.png"),
            Map.entry(new PersonImageKey("고종", Kingdom.KOREAN_EMPIRE), "/images/persons/gojong-korean-empire.png"),

// ==================================================
// 일제강점기
// ==================================================

            Map.entry(new PersonImageKey("안중근", Kingdom.JAPANESE_COLONY), "/images/persons/an-jung-geun.png"),
            Map.entry(new PersonImageKey("유관순", Kingdom.JAPANESE_COLONY), "/images/persons/yu-gwan-sun.png"),
            Map.entry(new PersonImageKey("김구", Kingdom.JAPANESE_COLONY), "/images/persons/kim-gu.png"),

// ==================================================
// 대한민국 제1공화국
// ==================================================
    Map.entry(new PersonImageKey("이승만", Kingdom.FIRST_REPUBLIC_OF_KOREA), "/images/persons/syngman-rhee.png"),
            // ==================================================
// 조선 - 추가 주요 인물
// ==================================================

            Map.entry(new PersonImageKey("정도전", Kingdom.JOSEON), "images/persons/jeong-do-jeon.png"),
            Map.entry(new PersonImageKey("김종서", Kingdom.JOSEON), "images/persons/kim-jong-seo.png"),
            Map.entry(new PersonImageKey("사도세자", Kingdom.JOSEON), "images/persons/sado-seja.png"),
            Map.entry(new PersonImageKey("정약전", Kingdom.JOSEON), "images/persons/jeong-yak-jeon.png"),
            Map.entry(new PersonImageKey("장승업", Kingdom.JOSEON), "images/persons/jang-seung-eop.png"),
            Map.entry(new PersonImageKey("김홍도", Kingdom.JOSEON), "images/persons/kim-hong-do.png"),
            Map.entry(new PersonImageKey("신사임당", Kingdom.JOSEON), "images/persons/shin-saimdang.png"),
            Map.entry(new PersonImageKey("황진이", Kingdom.JOSEON), "images/persons/hwang-jini.png"),
            Map.entry(new PersonImageKey("전봉준", Kingdom.JOSEON), "images/persons/jeon-bong-jun.png"),
            Map.entry(new PersonImageKey("김옥균", Kingdom.JOSEON), "images/persons/kim-ok-gyun.png"),

// ==================================================
// 개화기
// ==================================================

            Map.entry(new PersonImageKey("흥선대원군", Kingdom.JOSEON), "images/persons/heungseon-daewongun.png"),

// ==================================================
// 일제강점기 - 추가 인물
// ==================================================

            Map.entry(new PersonImageKey("윤봉길", Kingdom.JAPANESE_COLONY), "images/persons/yun-bong-gil.png"),
            Map.entry(new PersonImageKey("이봉창", Kingdom.JAPANESE_COLONY), "images/persons/yi-bong-chang.png"),
            Map.entry(new PersonImageKey("안창호", Kingdom.JAPANESE_COLONY), "images/persons/an-chang-ho.png"),
            Map.entry(new PersonImageKey("신채호", Kingdom.JAPANESE_COLONY), "images/persons/shin-chae-ho.png"),
            Map.entry(new PersonImageKey("홍범도", Kingdom.JAPANESE_COLONY), "images/persons/hong-beom-do.png"),
            Map.entry(new PersonImageKey("김좌진", Kingdom.JAPANESE_COLONY), "images/persons/kim-jwa-jin.png"),
            Map.entry(new PersonImageKey("김원봉", Kingdom.JAPANESE_COLONY), "images/persons/kim-won-bong.png"),
            Map.entry(new PersonImageKey("한용운", Kingdom.JAPANESE_COLONY), "images/persons/han-yong-un.png"),
            Map.entry(new PersonImageKey("윤동주", Kingdom.JAPANESE_COLONY), "images/persons/yun-dong-ju.png"),
            Map.entry(new PersonImageKey("이육사", Kingdom.JAPANESE_COLONY), "images/persons/yi-yuk-sa.png"),
            Map.entry(new PersonImageKey("이회영", Kingdom.JAPANESE_COLONY), "images/persons/yi-hoe-yeong.png"),
            Map.entry(new PersonImageKey("지청천", Kingdom.JAPANESE_COLONY), "images/persons/ji-cheong-cheon.png"),
            Map.entry(new PersonImageKey("박열", Kingdom.JAPANESE_COLONY), "images/persons/park-yeol.png"),
            Map.entry(new PersonImageKey("남자현", Kingdom.JAPANESE_COLONY), "images/persons/nam-ja-hyeon.png"),
            Map.entry(new PersonImageKey("김두한", Kingdom.JAPANESE_COLONY), "images/persons/kim-du-han.png")
    );

    public static String getImageUrl(Person person) {

        return IMAGE_URLS.get(
                new PersonImageKey(
                        person.getName(),
                        person.getKingdom()
                )
        );
    }
}