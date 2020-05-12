package com.example.pactconsumerapi;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.pactconsumerapi.repositories.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class CreateUserPactTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Rule
    public PactProviderRuleMk2 mockPactProviderApi = new PactProviderRuleMk2("pact-provider-api", "localhost", 8081, this);

    @Pact(consumer = "pact-consumer-api")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("Create Ineligible User")
                    .uponReceiving("Ineligible user")
                    .path("/is-eligible")
                    .query("email=test@kainos.com")
                    .method("GET")
                    .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body("{\"isEligible\": false}")
                .given("Create Eligible User")
                    .uponReceiving("Eligible user")
                    .path("/is-eligible")
                    .query("email=test@gmail.com")
                    .method("GET")
                    .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body("{\"isEligible\": true}")
                .toPact();
    }

    @Test
    @PactVerification
    public void createUserPactTests() throws Exception {
        // Test 1 - Ineligible User - Returns 422
        mockMvc.perform(
                post("/users")
                        .contentType("application/json")
                        .content("{\"name\": \"Invalid User\", \"email\": \"test@kainos.com\"}")
        ).andExpect(status().isUnprocessableEntity());

        // Test 2 - Eligible User - Returns 201
        when(userRepository.saveAndFlush(ArgumentMatchers.any())).thenReturn(null);

        mockMvc.perform(
                post("/users")
                        .contentType("application/json")
                        .content("{\"name\": \"Valid User\", \"email\": \"test@gmail.com\"}")
        ).andExpect(status().isCreated());
    }
}
