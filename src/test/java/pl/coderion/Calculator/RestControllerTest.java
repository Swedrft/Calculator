package pl.coderion.Calculator;

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

        // Tworzenie obiektu request
        CalculationRequest request = new CalculationRequest();
        request.setKwota(BigDecimal.valueOf(5000));
        request.setLiczbaRat(6);
        request.setDataPoczatkowa(LocalDate.of(2025, 3, 13));

        // Walidacja danych wejściowych przed wysłaniem żądania
        assertTrue(request.getKwota().compareTo(BigDecimal.ZERO) > 0, "Kwota powinna być większa od 0");
        assertTrue(request.getLiczbaRat() > 0, "Liczba rat powinna być większa od 0");

        // Tworzenie HTTP requestu
        HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);

        // Wysłanie POST requestu do endpointu
        ResponseEntity<Oferta> response = restTemplate.exchange(
                "/api/calculate",
                HttpMethod.POST,
                httpEntity,
                Oferta.class
        );

        // Logowanie odpowiedzi
        System.out.println("Odpowiedź serwera: " + response);

        // Asercje sprawdzające poprawność odpowiedzi
        assertNotNull(response.getBody(), "Odpowiedź nie powinna być nullem");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status odpowiedzi powinien być 200 OK");
        assertNotNull(response.getBody().getRaty(), "Lista rat nie powinna być nullem");

        // Logowanie danych odpowiedzi, by zrozumieć problem
        Oferta oferta = response.getBody();
        System.out.println("Oferta: " + oferta);
        assertNotNull(oferta.getRaty(), "Raty w ofercie nie mogą być nullem");
    }
}
