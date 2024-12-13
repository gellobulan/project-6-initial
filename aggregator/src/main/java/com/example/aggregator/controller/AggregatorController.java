package com.example.aggregator.controller;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregatorController {

    private final AggregatorService aggregatorService;

    // Constructor Injection
    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    // Method to get words starting with a specific set of characters
    @GetMapping("/getWordsStartingWith/{chars}")
    public List<Entry> getWordsStartingWith(@PathVariable String chars) {
        return aggregatorService.getWordsStartingWith(chars);
    }

    // Method to get words ending with a specific value
    @GetMapping("/getWordsEndingWith/{value}")
    public List<Entry> getWordsEndingWith(@PathVariable String value) {
        return aggregatorService.getWordsEndingWith(value);
    }

    // Method to get all palindromes (added)
    @GetMapping("/getAllPalindromes")
    public List<Entry> getAllPalindromes() {
        return aggregatorService.getAllPalindromes();
    }
}