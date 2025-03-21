package pl.coderion.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
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
        assertNotNull(response.getBody());
        Oferta oferta = response.getBody();
        assertNotNull(oferta.getRaty());

        Rata pierwszaRata = oferta.getRaty().get(0);
        assertEquals(1, pierwszaRata.getNumer());
        assertEquals(LocalDate.of(2025, 4, 10), pierwszaRata.getTerminPlatnosci());
        assertEquals(23, pierwszaRata.getIloscDni());
        assertEquals(72.47, pierwszaRata.getOdsetki());
    }

    @Test
    void testZeroInstallmentsShouldReturnInternalServerError() {
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

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
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

    @PostMapping("/api/calculate")
    public ResponseEntity<Oferta> calculate(@RequestBody CalculationRequest request) {

        if (request.getKwota().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kwota musi być większa od 0");
        }
        if (request.getLiczbaRat() <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Liczba rat musi być większa od 0");
        }


        Oferta oferta = new Oferta();

        return ResponseEntity.ok(oferta);

    }

}
