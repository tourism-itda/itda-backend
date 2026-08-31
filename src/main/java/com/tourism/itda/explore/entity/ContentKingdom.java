package com.tourism.itda.explore.entity;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.explore.enums.Kingdom;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "content_kingdom")
@Getter
@NoArgsConstructor
@IdClass(ContentKingdom.ContentKingdomId.class)
public class ContentKingdom {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "kingdom")
    private Kingdom kingdom;

    public ContentKingdom(Content content, Kingdom kingdom) {
        this.content = content;
        this.kingdom = kingdom;
    }

    @EqualsAndHashCode
    @NoArgsConstructor
    public static class ContentKingdomId implements Serializable {

        private Long content;
        private Kingdom kingdom;
    }
}