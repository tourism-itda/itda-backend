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


    public ContentCharacter(
            Long contentId,
            String actorName,
            String characterName
    ){
        this.contentId = contentId;
        this.actorName = actorName;
        this.characterName = characterName;
    }
}