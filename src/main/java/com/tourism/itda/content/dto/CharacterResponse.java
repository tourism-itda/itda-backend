package com.tourism.itda.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.content.entity.ContentCharacter;

public record CharacterResponse(
        @JsonProperty("content_character_id") Long contentCharacterId,
        @JsonProperty("character_name") String characterName,
        @JsonProperty("actor_name") String actorName,
        @JsonProperty("is_historical") Boolean isHistorical,
        @JsonProperty("sort_order") Integer sortOrder
) {
    public static CharacterResponse from(ContentCharacter character) {
        return new CharacterResponse(
                character.getId(),
                character.getCharacterName(),
                character.getActorName(),
                character.getIsHistorical(),
                character.getSortOrder()
        );
    }
}
