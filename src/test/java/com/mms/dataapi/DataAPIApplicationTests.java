package com.mms.dataapi;

import com.mms.dataapi.api.controller.DialogController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataAPIApplicationTests {

    @Autowired
    private DialogController dialogController;
    @Test
    public void contextLoads() throws Exception {
        assertThat(dialogController).isNotNull();
    }

}
