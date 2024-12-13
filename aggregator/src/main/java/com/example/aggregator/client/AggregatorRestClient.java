package com.example.aggregator.client;

import com.example.aggregator.model.Entry;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AggregatorRestClient {

    private static final String DICTIONARY_API_URL = "http://your-api-endpoint";  // Replace with your actual endpoint
    private RestTemplate restTemplate;

    public AggregatorRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to get all words (you may need to change this according to your API)
    public List<Entry> getAllWords() {
        // Make the API call to fetch all words
        return restTemplate.exchange(
                DICTIONARY_API_URL + "/words",  // Replace with the correct URL path
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Entry>>() {}
        ).getBody();
    }

    // Existing methods...
}