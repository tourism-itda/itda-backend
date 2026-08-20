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
public class ContentCategory {

    @EmbeddedId
    private ContentCategoryId id;

    @MapsId("contentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    public ContentCategory(Content content, Long categoryId) {
        this.content = content;
        this.id = new ContentCategoryId(content.getId(), categoryId);
    }
}
