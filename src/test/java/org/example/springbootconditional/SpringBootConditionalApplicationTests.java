package org.example.springbootconditional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalApplicationTests {
    private static GenericContainer<?> devapp = new GenericContainer<>("devapp").withExposedPorts(8080);
    private static GenericContainer<?> prodapp = new GenericContainer<>("devapp").withExposedPorts(8081);
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoadsDevapp() {
        final String expected ="Current profile is dev";
        String url = String.format("http://localhost:%d/app/profile", devapp.getMappedPort(8080));
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Assert.assertEquals(expected, forEntity.getBody());
    }

    @Test
    void contextLoadsProdapp() {
        final String expected ="Current profile is production";
        String url = String.format("http://localhost:%d/app/profile", prodapp.getMappedPort(8081));
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Assert.assertEquals(expected, forEntity.getBody());
    }

}
