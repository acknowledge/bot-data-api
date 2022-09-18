package com.mms.dataapi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Utterance {
    @Id @GeneratedValue
    private UUID utteranceId;
    @Setter @Getter
    private String text;
    @Setter @Getter
    private String language;
    @Setter @Getter
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "dialog_id", nullable = false)
    private Dialog dialog;

    public Utterance() {}

    public Utterance(Dialog dialog, String text, String language) {
        this.text = text;
        this.language = language;
        this.creationDate = LocalDateTime.now();
        this.dialog = dialog;
    }
}
