package com.example.pactproviderapi;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@RunWith(PactRunner.class)
@Provider("pact-provider-api")
@PactFolder("../pacts")
@SpringBootTest
public class CreateUserPactTests {

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 8081);

    private static ConfigurableWebApplicationContext application;

    @BeforeClass
    public static void start() {
        application = (ConfigurableWebApplicationContext) SpringApplication.run(PactProviderApiApplication.class);
    }

    @State("Create Ineligible User")
    public void createIneligibleUser() { }

    @State("Create Eligible User")
    public void createEligibleUser() { }

}
