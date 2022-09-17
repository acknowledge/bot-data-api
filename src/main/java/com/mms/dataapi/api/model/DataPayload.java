package com.mms.dataapi.api.model;

import lombok.Getter;

public class DataPayload {

    @Getter
    private String text;
    @Getter
    private String language;

    DataPayload() {}

    DataPayload(String text, String language) {
        this.text = text;
        this.language = language;
    }
}
