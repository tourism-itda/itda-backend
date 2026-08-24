package com.tourism.itda.explore.entity;

import com.tourism.itda.content.entity.Content;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "content_person")
@Getter
@NoArgsConstructor
@IdClass(ContentPerson.ContentPersonId.class)
public class ContentPerson {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public ContentPerson(Content content, Person person) {
        this.content = content;
        this.person = person;
    }

    @EqualsAndHashCode
    @NoArgsConstructor
    public static class ContentPersonId implements Serializable {

        private Long content;
        private Long person;
    }
}