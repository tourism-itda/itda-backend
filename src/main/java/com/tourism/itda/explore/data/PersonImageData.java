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

            Map.entry(new PersonImageKey("문무왕", Kingdom.UNIFIED_SILLA), "https://i.namu.wiki/i/EvQ9rkPBMox3TL2vbNE_QQvGejAM3vvmTJCWZdnaXVDqO2fE0v0_uyarqpm9gL6WWweuxz2JC6G_LYR465uX-SPwWGBeFAIGva2jtgF60oquWlPMgx9QlIrNh03Cx81ynJeThd44Vz45g21t7bmkGw.webp"),
            Map.entry(new PersonImageKey("신문왕", Kingdom.UNIFIED_SILLA), "https://i.namu.wiki/i/cQ_Ucbu7Jf5kMkZZZQNSs7bnH9pW0TiJ-9ZKGb3DJAzujGDoXGKeiIuHpbqOQuSDYyoX5jlY-kOiCeRFEApPBJMkr7S3FBOac-gLRE4gkBiLQ_jqCSKJ5p_A1kDh_11mdqcsX1UhcmxR7gpF3OyLbw.webp"),
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

            Map.entry(new PersonImageKey("태조 왕건", Kingdom.GORYEO), "https://i.namu.wiki/i/Yk_OcEZWQw_zBULUwxgtkTKfZYvPU1f88uEAGLS8C_hs4Z22qOlyqNhbt-xOe3pIUI9IpHj2vGgLVufGQCCBUF58Qpn7R4dspOiow5eQA9OjSen43cLuYxkJw7H8hlVrwUQJeraHnH6uGzwVGq3pWw.webp"),
            Map.entry(new PersonImageKey("광종", Kingdom.GORYEO), "https://i.namu.wiki/i/-mIcOacmJhYvqkiDo9LwK4NTwiXXP2Y93OdwNbYtPAIyL05RxI35UWkdiKfbnzqlcXEoyLYL6WQWeq5D3vzp8DK5987bwFj6OZhwPcDbZS9K7Bjwy7n4qOu5G-738FUg649Y6U41B1fYfgZWQtKrhg.webp"),
            Map.entry(new PersonImageKey("공민왕", Kingdom.GORYEO), "https://i.namu.wiki/i/xo6mI8ExW1Ujgz_AtXsOwFNlQJBaQ4oDKRkDFRvFIIYoHz4PWrSkLMWuNmlQsEq2MWIxsYc4l2dsy1DFGXlk7RsclX5-LVXBJusrHuPuOW8HN_mN0sBByDIyHRG9F27nQqvann_KD86GhBMb27ZOsQ.webp"),
            Map.entry(new PersonImageKey("서희", Kingdom.GORYEO), "https://i.namu.wiki/i/4wl8Em8dljJ1D5Bwjp5gmM0A5VTJnE6uR1mzXyzoQI-RrqhzMUTV8EvfaOeBAEIUHStRSMiEfVe4Ni8PTS9DH8V_lZY1bnF4bwVYwpH1vjgFdCCR5qcTClPWVlHyKesCYSpO6MZEfdUkaW4f_kWmKQ.webp"),
            Map.entry(new PersonImageKey("강감찬", Kingdom.GORYEO), "https://i.namu.wiki/i/uVvUpMeU-0kst0OMZWIkScxBliI4b15Lo1PqXn9BRK_IHRFR5kskpbgI01NT20-0JCcTOspb9na0wFTB6tddtCyffOgdTtSSWx6P_KosUdT_F4UlkPd5ZaVO7s3c9JVpXh7i2pl0w3jqyMJOJS_wpA.webp"),
            Map.entry(new PersonImageKey("최무선", Kingdom.GORYEO), "https://i.namu.wiki/i/P31_LqL99xCWcd7547wrLCWwwjeBXXVS6r5RmG7UJ-2_mFV8_Xs2ZcxBJF4QEJabBZOaf_EnnDyStrTcK5l9qYaEf5scBKfri8ZuiI0ab9LMF_fwTLOtvXoWlQCfc4skc1IJ6XPFC28JyFOKQBhmdw.webp"),

            // ==================================================
            // 조선 - 왕
            // ==================================================

            Map.entry(new PersonImageKey("태조", Kingdom.JOSEON), "https://i.namu.wiki/i/F_-4l5Pc2zMq62MYcbV8PCTj9qOnl7AkDVMUBlwjL7Cdd0z0hKjLEnBpDga8sSZLShzdEiGr9R-g9CF8wfYDdF8TbQ2gsMMhcYQslI3dqdXotevwCcETEK3s95_O4S0bbxjCkjfz61PMY0W_KHoKDQ.webp"),
            Map.entry(new PersonImageKey("정종", Kingdom.JOSEON), "https://blog.kakaocdn.net/dna/bBUMXj/btsfeAl87z9/AAAAAAAAAAAAAAAAAAAAAH1DEmCMp42DL0gsrEdufIJJKVueiSBhOl6mI45IHgQs/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1788188399&allow_ip=&allow_referer=&signature=TgXFXHl8ctSAwWUPXBG1RnIGUZ8%3D"),
            Map.entry(new PersonImageKey("태종", Kingdom.JOSEON), "https://i.namu.wiki/i/WSgeiNyPEkikjmCkRfZolTCOT2ZfuOulHs9wGStlIkN9UuLj_TaX_GV65lVHvL0alCPM2R7cr7JiiEPK1EiNnc5NzafOgc7fd18ouwBnHH5r8KbQvPy0gzGvTq4k5FtMPxdDLo0MfEmZ6zGZwXfaZA.webp"),
            Map.entry(new PersonImageKey("세종", Kingdom.JOSEON), "https://i.namu.wiki/i/TgR3x1_R0yBYtqfYmK33Hb_DxuEZmUSIftA5Y89XVjr4OCiHZ7-zwQ_JvfsZmqEu9ciBLuoHopjMifCLhSawI_t1t5Ot6Msf0hEjm_9OXWBGTDFpCIB57CD5G6KCNMe5z-YlmCT4K7_9HQcTL0-PBA.webp"),
            Map.entry(new PersonImageKey("문종", Kingdom.JOSEON), "https://i.namu.wiki/i/wL6o6ck5NEV2iiO5Q_dnn-l7WNLxOQ77EXzIM3lmNcgyzBFUv-TMZ5C0Uz-NIMIQVyFkDRcPQvYYnuG6QxCkxe63yQDkrt_WA3UPvF9v0riO2hMgIclHEJa25rqv3UVyz6s272-bUEnZOzGOygKcZA.webp"),
            Map.entry(new PersonImageKey("단종", Kingdom.JOSEON), "https://i.namu.wiki/i/Ej1gSQnnDlrhwcdeNETCZWR7t7OfApVrH8Qid7fg77ErcTT40DXWxJ5XxNXkbZRMkgqdL0zUAsydW8sgmd9r2vmVtnDn9PdXZEcpLKHyQWxeNZo9289xNb_-P0ftMkBKBdK6wdYWNp2fO5joY-PmmA.webp"),
            Map.entry(new PersonImageKey("세조", Kingdom.JOSEON), "https://i.namu.wiki/i/ssnoMH6I9yKfUrxqJeg8w0z-ln8V3fZTQWThjlJEJLxkP5xpRqKq1_FdG8s_nJzHT4pVhkamnb1YOIaOtTMToE66ZHjlhkhflMQ1YLDehHCQJUhS-tOaV37DW-Pw_6Qp3eCs5mONuhO_f3r4S1s-VQ.webp"),
            Map.entry(new PersonImageKey("예종", Kingdom.JOSEON), "https://i.namu.wiki/i/CpRvMWDa3ClbIpRoCmwnW_JsNhkY_F22odVGQOPBUk_aTz-0wKxkWNP1ctiLdVj5QF2knWLLiK_KH81C_-KMZmXYPC8OWixxd40TSGgUUY1G1V2gj1v9fDTRU9b7AfwqB41gg5tckutXWtV_9Hq8sw.webp"),
            Map.entry(new PersonImageKey("성종", Kingdom.JOSEON), "https://i.namu.wiki/i/tORGIRn9inprVVrU0Op2YhJAkDQJK4wmLq0B1z0TqHTP1hjnuCwro9bp_Gt2oh70wxdp55kTVEqi76ARl37JZ3it_WKRRoXFl2UYfGrC6AYQKuOw53OF4xcdgzEIfTynIUisU00pIeVCwFd-yW3Vxg.webp"),
            Map.entry(new PersonImageKey("연산군", Kingdom.JOSEON), "https://i.namu.wiki/i/MsETNUQABoiwYKNXq7hIIKn6qt_Nh2gkxSkegfqG321X9fpKKbDXMOM1lsF-49itGhAxnQ8fxOFUhIX9PRCXTQ4mpCvkfh6Hkr0qhukkLTF6Bdk1w8SW80uWMUmL0-DMuNIYhLiYRSegL6AYNbXt3Q.webp"),
            Map.entry(new PersonImageKey("중종", Kingdom.JOSEON), "https://i.namu.wiki/i/IRprSmkiq4jN764Ajx08jmCbv1SU_S6S0gzSlRJzLtAbZtpr4tOoyGRqWat1GEgfDpQZuGR891qMv1vUO7yKuOTixAYCJSwW9pXEe7oxan3fHeQOns_6lOhhrex5OUkCbEbpRm3kcyqK36T6Hrr3HA.webp"),
            Map.entry(new PersonImageKey("인종", Kingdom.JOSEON), "https://i.namu.wiki/i/YwK6stA0FaXfUn_Sf77irDucYkmmjCbZwT4WzwYeDr4KES3HnsyFoDNw19UBCfRS7iMoQlGGbPrfPbeNUUM2daKNgO8eTMA73-7yV6tqgK6QO14mhbZbHZCdiEHCK5v4hKjLFhv9_pp_fmodPjVGLQ.webp"),
            Map.entry(new PersonImageKey("명종", Kingdom.JOSEON), "https://i.namu.wiki/i/TzaSqdbq6Ruk3hqMjWF_j0cAOGPWWb49fNWIiDX9k8ADcoHFTkPbf1Gq7Zzu_rF9rTU-4brJttnJXG7LVUPC4NboR5lGQGT5am4LblJaEbUcLNKz-kpJlbfthrejmN7vC_Co3p7rWH1CBoHaxgzqXQ.webp"),
            Map.entry(new PersonImageKey("선조", Kingdom.JOSEON), "https://i.namu.wiki/i/NypxZ7aTI0PIcasBP9PuBSfcqDb68M5nbwwZHJW7kYGa-UzufZHsJtuGabfYmdpB_hHT74Zuh45ZkUgJW0nflhsI0Js7NU8BrhLZu41lqZDDMhlyCmzlp3cZu24i5owLAKqTMAdVTGe_DfnS59qg8A.webp"),
            Map.entry(new PersonImageKey("광해군", Kingdom.JOSEON), "https://i.namu.wiki/i/vEbZ2ndXgUQ_2nnJql9mj3KeM338-jrYlZBJxZyPog1GR_JwP_IGhJLRSKf8ZptTc3EKuX8hIEzu2NBHiKP0bbCid1KWpzEfwCZXopWgDZgg1_T3sarSiv8r7s-z-RJgT81TclyXXPWQKfz2pkBTpw.jpg"),
            Map.entry(new PersonImageKey("인조", Kingdom.JOSEON), "https://i.namu.wiki/i/wsj_SzcqhHhOBdgm-pZmg6uNqdqwruA3SpDWc-0eUpfiIIWfPvpI84r8OVpRC8vPpS_mOHfH5nyvYXv9d9aHkAvr19yosFY-3c3vpN6FmM9syOOlv_Jk965LI2Q8ZE0tMxYaP4g0kXbcrxFphjCTyQ.webp"),
            Map.entry(new PersonImageKey("효종", Kingdom.JOSEON), "https://i.namu.wiki/i/D_Q77Z-VqoSIoOuLkMtwPfHFwhOalFl1SyRApdQWruY19itxg0EOOrJx134hCaJFQ9Kk_qQpxhbZdX92QeY9v78mletH4uSIiOn4B1NclgavZirZLuB_9GBg6R0h3oiqwIWpkvtVfFkc9IQBmq6jHQ.webp"),
            Map.entry(new PersonImageKey("현종", Kingdom.JOSEON), "https://i.namu.wiki/i/wK6sokq351r-YCcfZ7eoQpp2SzAJw3dPmW0Sj2R5lF3AofEa9dIRsPTqk2g-6zk3oObE2A3i52xNcJyg2xvq7zjxhcvw--zHWQsTsNKzyCEZMOlIn-PnDHUXv5R84pjCmmieYWOk4BMuiwQyVsq0tg.webp"),
            Map.entry(new PersonImageKey("숙종", Kingdom.JOSEON), "https://i.namu.wiki/i/vcHVT5kFvJwKW4fziwG9I33Djm0EZA76fqrKMzrWEEdkyt5gi3W1Z_7LC13NFtoaQyqoCLeNE0I1oxgFRj6_udyT2UCn3os-YSCWCZXENf1tCni-d4eo_MSWfZK6FMw0v-y1spDWuYM-I7juPgYewQ.webp"),
            Map.entry(new PersonImageKey("경종", Kingdom.JOSEON), "https://i.namu.wiki/i/GXy56iCRG976NYjV1etsjPFQieoRYKlYGQqt7MdAtiGKUKeCrct3r-XHgsErxATcBcTAZCF_IxQyl3WhpXVz_3c7JAxEwj-qVeCWyGyNYIriudAqY9p7JGtFIAry-Z1A8m4UhNjHkkjqOMi5Y3jw1w.webp"),
            Map.entry(new PersonImageKey("영조", Kingdom.JOSEON), "https://i.namu.wiki/i/ghbJYC5VeuUDgGm3CaDmiRVQrXVgS48Vkd40aohUnAcEhdJCLyem0jEv-Jm_nrLIIRICdrMbGHNb7mCXQImQqa9l6RwCBjtg2MUVRU_rPFZ5QEll9QWrNjRKbFCdiMFZyaIhF68fwGHYl7nM53FUVA.webp"),
            Map.entry(new PersonImageKey("정조", Kingdom.JOSEON), "https://i.namu.wiki/i/F4wW4_85j-h8WenMA4Y5bAnMLCUBZLc5dgRXQwLYJ0bTlDbIvEjeOZfbbQgO8OC7BxlXZfCvcQRGznqLhtDbS_QbGRSEEwuRbOREo5tczxum20cey8QtZu3dHgI39TAmxYr1Z3PaI_8p7eXM_hgnMw.webp"),
            Map.entry(new PersonImageKey("순조", Kingdom.JOSEON), "https://i.namu.wiki/i/8ddlbA5ste8x-jWnC1VdIBTQU5SM9oAocu4tq8qgRcqD209IGyenZEMcnuSJWFRwY1YS1vkX6BrbcmZh_OUs_5MMbb-OXn8mVClyNeTnuwfhKaEOOgAcduGWvSCAhFHCk4qXD1U5eroAzqD0qd8TLw.webp"),
            Map.entry(new PersonImageKey("헌종", Kingdom.JOSEON), "https://i.namu.wiki/i/Ypc3IjLkfvaO3K546_5gjasKScE9-AMzyhFWGi6Or4jPxj-2tMOwcgEgFzIQte-dXyU_xdEDM5piJ1WvHJPWHsdkpyD9-G96XMjTVmUnVMK0pmTmXW9yABZILM-BWWbaSKe2h4ieoEzLLXeqDQl0Gg.webp"),
            Map.entry(new PersonImageKey("철종", Kingdom.JOSEON), "https://i.namu.wiki/i/relv2cWQVxtpXUW2X6Ue20przh9uqfwpg2RgRVyYRyBdeltpOF5naSeAQgB6--9yXd9zEn_ynNunROONgn5bnwSmvp_XJjfyTs-qVw7-lvkz2lZcN_ebQzrW1yRVAjktXw3f-f6DwhP3LMg_VB8eHw.webp"),
            Map.entry(new PersonImageKey("고종", Kingdom.JOSEON), "https://i.namu.wiki/i/gpRME_GgjwKqZ2v-IbkEwzwFIETIOoVhKxXS3JdUl-hlzY9XMi09p6gllrfFLJRhv-qae_c3048FKEIXGH1KHIHEtLyqib1CHpDNGTecgIxGIsCEJqAltKJg-AVl5hSIKnPKZVk2cogbUy28jsLrqg.webp"),
            Map.entry(new PersonImageKey("순종", Kingdom.JOSEON), "https://i.namu.wiki/i/t747O_bRpYl8PBSmZYAXk3yxTOhGEoytlDAqEkx7BVDnPJ3HS0-nkKqH204U95l1hIAn3nM0qECrh5pKEn6c8yIgKkqmcqJEwGBtV2o7m-qQhD3KtLQF3MHxHUWG0pX3TfBnxVQ071Xkg6xZeh1W_Q.webp"),

            // ==================================================
            // 조선 - 주요 인물
            // ==================================================

            Map.entry(new PersonImageKey("이순신", Kingdom.JOSEON), "https://i.namu.wiki/i/gsI2NGWZWCwPfTbfFA86eznNH2Xc9u08BFY0lzHg6AHl074M0KDPVrIixQvvoxLN3SrF7FZtx6ZtJxvey3alvcGSJTH0loLpiMR9ZSx-In9M4epaIRJr7O3gOqLRnzPaHcMWEskTaaIA0Va_7j49iQ.webp"),
            Map.entry(new PersonImageKey("장영실", Kingdom.JOSEON), "https://i.namu.wiki/i/Gqb8RfNs5muKqS7XdjFMVL6bBomLnilQ6SdMnx-Z80G0VTHTJeI63X41XjD3REk6RkGXBqW-sNq_EabwnbWWNo3pyRKWVPuVWkynJ-HGMyt89AGkoRfHOBi7QNuzFyuxUm8JccVhfYI3yOu2MuT1UQ.webp"),
            Map.entry(new PersonImageKey("허준", Kingdom.JOSEON), "https://i.namu.wiki/i/RnCHXqa20a_6oqO91QY4-QhFGa_aNxnzSMOWGorbVFmwZpu6QzbrYHN1SZ2dvf7IboE7vQOpQivrfQ8c7tqbm8iC07y0AED6hhzxN0fheio908bt6DfWFC4xXPTNmANgv_vFlwCa8AUIW6pL36vcyw.webp"),
            Map.entry(new PersonImageKey("정약용", Kingdom.JOSEON), "https://i.namu.wiki/i/y-Yk13IuR1JY7nnL1iqkBvN_ydWHRsfo1Bvs_8XMbUuQnFMozdkUMU2v5xOA0u82calMYGbz7qOXGFEVX9wJjf79j-3eBN5iy1d3NkBgdjfVM4RNELXLWhGsmMNszvU4Sg7wMcNoXLWS0J5USi5vFQ.webp"),
            Map.entry(new PersonImageKey("이황", Kingdom.JOSEON), "https://i.namu.wiki/i/SCLxzb8mz3tdyjuc-j0W43pXD6RgtDpJB0QcbRxx1mmQ3BfZ_KdNntRY45NR1uemvx8j4Drj6cG3HJX63v0FwvgAkyHr7YjuY3b_CYufTPvv-M7-YWQz_N8azVcClDw34NgcXZ_NZmULW53K1l1xFw.webp"),
            Map.entry(new PersonImageKey("이이", Kingdom.JOSEON), "https://i.namu.wiki/i/VCUFuoUsQwNuXgOGshUUJeaHirTsac5CYxEwUpkE3udwYafoFypvPlD_RpnW0ldOYN5P17YY6LbZI3ukGx66z94ZnK2SXmCv291PvVbO_7IVshe8XUUI7yEBCoiEHX2kwCXzRLCH59k2vBWQcrgybw.webp"),

            // ==================================================
            // 개화기 / 대한제국
            // ==================================================

            Map.entry(new PersonImageKey("명성황후", Kingdom.JOSEON), "https://i.namu.wiki/i/5WAwA2okkK48B95Px9CRUmYaEp51xGV1VFDn1m4ot7b9H7sEuHXTET5MIgsNAcqahze4gqo30sMhnxIDWYi1QC-QwDkmhAMOlTBstLJmxpNKM1f4jKGxthyu3X9ZfZkv8C6rNh2wEsdNfgNMiQlCRA.webp"),
            Map.entry(new PersonImageKey("고종", Kingdom.KOREAN_EMPIRE), "https://i.namu.wiki/i/cPw7r66I2A12MXl6Kv79G-DzuWT2SefKV6NHweKc9_N7QrVD3EcvPyuXqcIGlPrCGgMhMqoHQfDclCV0yBgsuv447-ELgw4MaZ5SBCvu1UyZD-5L9hJ3uLEUcNZkG2k5G2Au66PGzswCCCFzKwN3Mg.webp"),

            // ==================================================
            // 일제강점기
            // ==================================================

            Map.entry(new PersonImageKey("안중근", Kingdom.JAPANESE_COLONY), "https://i.namu.wiki/i/SnDX4lJSJwEOvREGKRMmGYHMN-7X156JvxWVDgH3b1ldwWIxtdEBJaXeB4AXhFMCZFKuwjqGtab464IQ6A9miguvtdKcUAvBTkH8-13aRzzttQwwweICjympg4jo8BxUajhJWzLwznay6LvqiimQpw.webp"),
            Map.entry(new PersonImageKey("유관순", Kingdom.JAPANESE_COLONY), "https://i.namu.wiki/i/CpXfH1CBe74yXbEFzC-Zi-cZorflghlv-uIFa22s2n5TmzvbUCcvcCTd_TVlAl3rWduWUP-UiTVP0hdNyqZIaphW3NlP97CF-fSNBIVDOKAWzW1kRdUa3XWuC6W1rw6gTjiBost7UTVgiyEs9WBwDg.webp"),
            Map.entry(new PersonImageKey("김구", Kingdom.JAPANESE_COLONY), "https://i.namu.wiki/i/FjdzlrQja7I9qDf5PGk3CgOUMVXunWVdehLqmTT82RFMOr6nQNx8q079s9LdrcdehfV5aSwrV4P4vtYCRAm2dwT7CBqdciNtXcSu3HnbCnD2zxSTWJZS7nsND4_r2TDNhmzyjrtr0MSJP9rdbbZVQA.webp"),

            // ==================================================
            // 대한민국 제1공화국
            // ==================================================

            Map.entry(new PersonImageKey("이승만", Kingdom.FIRST_REPUBLIC_OF_KOREA), "https://i.namu.wiki/i/n53DnvoP_Pnz7SntVHOI7FHJdJ2HLW9qYHjX9Bg42kWdjI2kNnsGOQXzNOlKAYaUaW1CExmt0chuWEkizEliti8sAJE76u3YtkwPpn2kksqdqDbs3zKey1f2ueJhO8CaVUbVd0ChcJ3EEvEVPGYyTg.webp")
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