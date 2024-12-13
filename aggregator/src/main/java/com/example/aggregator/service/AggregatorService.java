package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    private AggregatorRestClient aggregatorRestClient;

    public AggregatorService(AggregatorRestClient aggregatorRestClient) {
        this.aggregatorRestClient = aggregatorRestClient;
    }

    // Method to get words starting with a specific set of characters
    public List<Entry> getWordsStartingWith(String chars) {
        List<Entry> allWords = aggregatorRestClient.getAllWords();
        List<Entry> results = new ArrayList<>();

        for (Entry entry : allWords) {
            if (entry.getWord().startsWith(chars)) {
                results.add(entry);
            }
        }

        return results;
    }

    // Method to get words ending with a specific value
    public List<Entry> getWordsEndingWith(String value) {
        List<Entry> allWords = aggregatorRestClient.getAllWords();
        List<Entry> results = new ArrayList<>();

        for (Entry entry : allWords) {
            if (entry.getWord().endsWith(value)) {
                results.add(entry);
            }
        }

        return results;
    }

    // Extra Credit: Rewritten palindrome service method without using streams
    public List<Entry> getAllPalindromes() {
        List<Entry> candidates = new ArrayList<>();

        // Get all words first (optimization to reduce redundant API calls)
        List<Entry> allWords = aggregatorRestClient.getAllWords();

        // Iterate over each word to check if it is a palindrome
        for (Entry entry : allWords) {
            String word = entry.getWord();

            // Check if word is a palindrome (same forward and backward)
            String reverse = new StringBuilder(word).reverse().toString();
            if (word.equals(reverse)) {
                candidates.add(entry);
            }
        }

        // Sort candidates by word
        candidates.sort((e1, e2) -> e1.getWord().compareTo(e2.getWord()));

        return candidates;
    }
}