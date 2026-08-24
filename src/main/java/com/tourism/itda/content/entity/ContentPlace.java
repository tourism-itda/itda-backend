package com.tourism.itda.content.entity;

import com.tourism.itda.place.entity.Place;
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

    @MapsId("placeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private Integer recommendOrder;

    public ContentPlace(Content content, Place place, Integer recommendOrder) {
        this.content = content;
        this.place = place;
        this.id = new ContentPlaceId(
                content.getId(),
                place.getId()
        );
        this.recommendOrder = recommendOrder;
    }
}