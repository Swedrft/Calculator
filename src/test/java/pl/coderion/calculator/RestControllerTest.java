package pl.coderion.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@AllArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void calculateShouldReturnOfertaWhenInputIsValid() {

        CalculationRequest request = new CalculationRequest(
                BigDecimal.valueOf(5000),
                6,
                LocalDate.of(2025, 3, 13)
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

        System.out.println("Odpowiedź serwera: " + response);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getRaty());

        Oferta oferta = response.getBody();
        System.out.println("Oferta: " + oferta);
        assertNotNull(oferta.getRaty());
    }
}
