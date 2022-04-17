package com.mercadolibre.dnaanalyzerapi.rest.controller;

import com.mercadolibre.dnaanalyzerapi.human.application.HumanService;
import com.mercadolibre.dnaanalyzerapi.stats.application.StatsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DnaAnalyzerControllerStatus500Test {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  StatsService statsService;

  @Test
  void getStats() throws Exception {
    Mockito.when(statsService.getStats()).thenThrow(NullPointerException.class);

    mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
        .andExpectAll(MockMvcResultMatchers.status().isInternalServerError(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.message").value("Server error"))
        .andDo(MockMvcResultHandlers.print());
  }
}