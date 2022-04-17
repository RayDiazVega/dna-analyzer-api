package com.mercadolibre.dnaanalyzerapi.human.infrastructure.adapters;

import com.mercadolibre.dnaanalyzerapi.constant.Constant;
import com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports.HumanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HumanControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  HumanRepository humanRepository;

  String notMutant = Constant.notMutant;

  @Test
  void validateDNAStatus200() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
            .contentType(MediaType.APPLICATION_JSON).content(Constant.mutant))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

    humanRepository.delete(humanRepository.findByDna(Constant.dna).get());
  }

  @Test
  void validateDNAStatus400() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/mutant/"))
        .andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.error")
                .value("Request method 'DELETE' not supported"),
            MockMvcResultMatchers.jsonPath("$.message").value("Client error"))
        .andDo(MockMvcResultHandlers.print());

    notMutant = notMutant.replace("A", "X");

    mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
            .contentType(MediaType.APPLICATION_JSON).content(notMutant))
        .andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.error.[0]")
                .value("only values A,T,C and G are allowed"),
            MockMvcResultMatchers.jsonPath("$.message").value("Client error"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void isNotMutantStatus403() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
            .contentType(MediaType.APPLICATION_JSON).content(notMutant))
        .andExpectAll(MockMvcResultMatchers.status().isForbidden())
        .andDo(MockMvcResultHandlers.print());
  }
}