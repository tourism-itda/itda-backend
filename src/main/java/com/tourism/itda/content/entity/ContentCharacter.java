package com.tourism.itda.content.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ContentCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contentId;

    private String actorName;

    private String characterName;

    private Boolean isHistorical;

    private Integer sortOrder;

    public ContentCharacter(
            Long contentId,
            String actorName,
            String characterName
    ){
        this.contentId = contentId;
        this.actorName = actorName;
        this.characterName = characterName;
    }

    public void changeIsHistorical(Boolean isHistorical) {
        this.isHistorical = isHistorical;
    }

    public void changeSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}