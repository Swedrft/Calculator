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


        Oferta oferta = response.getBody();
        assertNotNull(oferta);


        assertEquals(BigDecimal.valueOf(885.41), oferta.getWysokoscRaty());


        assertNotNull(oferta.getRaty());
        assertEquals(6, oferta.getRaty().size());

       Rata rata1 = oferta.getRaty().get(0);
       assertEquals(6,oferta.getRaty().size());
       assertEquals(1,rata1.getNumer());
       assertEquals(23,rata1.getIloscDni());
       assertEquals(72.47,rata1.getOdsetki());


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
