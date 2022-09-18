package com.mms.dataapi.api.controller;

import com.mms.dataapi.api.model.Dialog;
import com.mms.dataapi.api.model.Utterance;
import com.mms.dataapi.api.repository.DialogRepository;
import com.mms.dataapi.api.repository.UtteranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DialogController.class)
class DialogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DialogRepository dialogRepository;
    @MockBean
    private UtteranceRepository utteranceRepository;

    @BeforeEach
    void setUp() {
        Dialog d = new Dialog(2L, 12L);
        //d.setConsent(true);
        //dialogRepository.save(d);

        Utterance u = new Utterance(d, "Hey you", "EN");
        //utteranceRepository.save(u);

        Mockito.when(dialogRepository.save(any(Dialog.class))).thenReturn(d);
        Mockito.when(utteranceRepository.save(any(Utterance.class))).thenReturn(u);

    }

    @Test
    void postDataShouldCreateObjectAndReturn201() throws Exception {
        mockMvc.perform(post("/data/12/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"Heyo\",\"language\":\"EN\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Heyo")));
    }

    @Test
    void postConsentsWhenNoEntryShouldReturn404() throws Exception {
        mockMvc.perform(post("/consents/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("true"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDataShouldReturnEmptyTable() throws Exception {
        mockMvc.perform(get("/data?language=EN&pageNo=0&pageSize=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}