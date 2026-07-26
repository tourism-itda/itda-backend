package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RelatedTourismItem(
        @JsonProperty("baseYm") String baseYm,
        @JsonProperty("tAtsCd") String tAtsCd,
        @JsonProperty("tAtsNm") String tAtsNm,
        @JsonProperty("areaCd") String areaCd,
        @JsonProperty("areaNm") String areaNm,
        @JsonProperty("signguCd") String signguCd,
        @JsonProperty("signguNm") String signguNm,
        @JsonProperty("rlteTatsCd") String rlteTatsCd,
        @JsonProperty("rlteTatsNm") String rlteTatsNm,
        @JsonProperty("rlteRegnCd") String rlteRegnCd,
        @JsonProperty("rlteRegnNm") String rlteRegnNm,
        @JsonProperty("rlteSignguCd") String rlteSignguCd,
        @JsonProperty("rlteSignguNm") String rlteSignguNm,
        @JsonProperty("rlteCtgryLclsNm") String rlteCtgryLclsNm,
        @JsonProperty("rlteCtgryMclsNm") String rlteCtgryMclsNm,
        @JsonProperty("rlteCtgrySclsNm") String rlteCtgrySclsNm,
        @JsonProperty("rlteRank") String rlteRank
) {}
