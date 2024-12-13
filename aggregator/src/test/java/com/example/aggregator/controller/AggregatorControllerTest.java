package com.example.aggregator.controller;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AggregatorController.class)
public class AggregatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AggregatorService aggregatorService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // Test for "getWordsStartingWith" endpoint
    @Test
    public void testGetWordsStartingWith() throws Exception {
        List<Entry> entries = Arrays.asList(new Entry("test", "definition"));
        Mockito.when(aggregatorService.getWordsStartingWith(anyString())).thenReturn(entries);

        mockMvc.perform(get("/getWordsStartingWith/te"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"word\":\"test\",\"definition\":\"definition\"}]"));
    }

    // Test for "getWordsEndingWith" endpoint
    @Test
    public void testGetWordsEndingWith() throws Exception {
        List<Entry> entries = Arrays.asList(new Entry("test", "definition"));
        Mockito.when(aggregatorService.getWordsEndingWith(anyString())).thenReturn(entries);

        mockMvc.perform(get("/getWordsEndingWith/st"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"word\":\"test\",\"definition\":\"definition\"}]"));
    }

    // Test for "getAllPalindromes" endpoint
    @Test
    public void testGetAllPalindromes() throws Exception {
        List<Entry> entries = Arrays.asList(new Entry("madam", "definition"));
        Mockito.when(aggregatorService.getAllPalindromes()).thenReturn(entries);

        mockMvc.perform(get("/getAllPalindromes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"word\":\"madam\",\"definition\":\"definition\"}]"));
    }
}
