package com.tourism.itda.content.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ContentPlace {

    @EmbeddedId
    private ContentPlaceId id;

    @MapsId("contentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private Integer recommendOrder;

    public ContentPlace(Content content, Long placeId, Integer recommendOrder) {
        this.content = content;
        this.id = new ContentPlaceId(content.getId(), placeId);
        this.recommendOrder = recommendOrder;
    }
}
