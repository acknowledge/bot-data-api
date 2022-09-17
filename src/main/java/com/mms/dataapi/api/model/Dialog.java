package com.mms.dataapi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Dialog {
    @Id
    private Long dialogId;
    @Getter @Setter
    private Long customerId;
    @Getter @Setter
    private Boolean consent;

    @OneToMany(mappedBy = "dialog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Utterance> utterances;

    public Dialog() {
        this.consent = false;
    }

    public Dialog(Long dialogId, Long customerId) {
        this.dialogId = dialogId;
        this.customerId = customerId;
        this.consent = false;
    }
}
