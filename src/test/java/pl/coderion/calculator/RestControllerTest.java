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
    void calculateShouldReturnOfertaWhenInputIsValid() {


        CalculationRequest request = new CalculationRequest(
                BigDecimal.valueOf(5000), // Amount
                6,                        // Installments
                LocalDate.of(2025, 3, 18) // Date
        );


        assertTrue(request.getKwota().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(request.getLiczbaRat() > 0);


        HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);


        ResponseEntity<Oferta> response = restTemplate.exchange(
                "/api/calculate",
                HttpMethod.POST,
                httpEntity,
                Oferta.class
        );


        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP status should be OK");
        assertNotNull(response.getBody().getRaty(), "Field 'raty' should not be null");


        Oferta oferta = response.getBody();
        System.out.println("Received offer: " + oferta);
        assertNotNull(oferta.getRaty(), "Installments in the offer should not be null");
    }
}
