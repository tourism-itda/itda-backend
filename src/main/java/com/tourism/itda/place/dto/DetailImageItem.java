package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetailImageItem(
        @JsonProperty("contentid") String contentId,
        @JsonProperty("originimgurl") String originImgUrl,
        @JsonProperty("imgname") String imgName,
        @JsonProperty("smallimageurl") String smallImageUrl,
        @JsonProperty("cpyrhtDivCd") String cpyrhtDivCd,
        @JsonProperty("serialnum") String serialNum
) {}
