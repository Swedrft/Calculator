package pl.coderion.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testValidCalculationRequest() {

        CalculationRequest request = new CalculationRequest(
                BigDecimal.valueOf(5000),
                6,
                LocalDate.of(2025, 3, 18)
        );

        HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);
        ResponseEntity<Oferta> response = restTemplate.exchange(
                "/api/calculate",
                HttpMethod.POST,
                httpEntity,
                Oferta.class
        );


        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testZeroInstallmentsShouldReturnBadRequest() {

        CalculationRequest request = new CalculationRequest(
                BigDecimal.valueOf(5000),
                0,
                LocalDate.of(2025, 3, 18)
        );

        HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/calculate",
                HttpMethod.POST,
                httpEntity,
                String.class
        );


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    void testNegativeAmountShouldReturnBadRequest() {

        CalculationRequest request = new CalculationRequest(
                BigDecimal.valueOf(-1000),
                6,
                LocalDate.of(2025, 3, 18)
        );

        HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/calculate",
                HttpMethod.POST,
                httpEntity,
                String.class
        );


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
}
