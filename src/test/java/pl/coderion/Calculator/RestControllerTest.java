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


        CalculationRequest request = new CalculationRequest();
        request.setKwota(BigDecimal.valueOf(5000));
        request.setLiczbaRat(6);
        request.setDataPoczatkowa(LocalDate.of(2025, 3, 13));


        assertTrue(request.getKwota().compareTo(BigDecimal.ZERO) > 0, "Kwota powinna być większa od 0");
        assertTrue(request.getLiczbaRat() > 0, "Liczba rat powinna być większa od 0");


        HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);


        ResponseEntity<Oferta> response = restTemplate.exchange(
                "/api/calculate",
                HttpMethod.POST,
                httpEntity,
                Oferta.class
        );


        System.out.println("Odpowiedź serwera: " + response);


        assertNotNull(response.getBody(), "Odpowiedź nie powinna być nullem");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status odpowiedzi powinien być 200 OK");
        assertNotNull(response.getBody().getRaty(), "Lista rat nie powinna być nullem");


        Oferta oferta = response.getBody();
        System.out.println("Oferta: " + oferta);
        assertNotNull(oferta.getRaty(), "Raty w ofercie nie mogą być nullem");
    }
}
