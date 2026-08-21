package com.tourism.itda.content.entity;

import com.tourism.itda.explore.entity.Category;
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

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public ContentCategory(Content content, Category category) {
        this.content = content;
        this.category = category;
        this.id = new ContentCategoryId(content.getId(), category.getCategoryId());
    }
}
