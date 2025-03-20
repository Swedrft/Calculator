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
        void calculateShouldHandleVariousCases() {


            CalculationRequest validRequest = new CalculationRequest(
                    BigDecimal.valueOf(5000),
                    6,
                    LocalDate.of(2025, 3, 18)
            );
            ResponseEntity<Oferta> validResponse = sendRequest(validRequest, Oferta.class);
            assertEquals(HttpStatus.OK, validResponse.getStatusCode(), "Przypadek 1: Oczekiwany status OK.");
            assertNotNull(validResponse.getBody(), "Przypadek 1: Oferta nie powinna być null.");

            Oferta oferta = validResponse.getBody();
            assertNotNull(oferta.getRaty(), "Przypadek 1: Raty w odpowiedzi nie powinny być null.");
            BigDecimal expectedInstallment = BigDecimal.valueOf(833.33); // Przykładowa oczekiwana rata
            assertEquals(expectedInstallment, oferta.getRaty().get(0), "Przypadek 1: Pierwsza rata nie zgadza się z oczekiwaną wartością.");


            CalculationRequest zeroRatsRequest = new CalculationRequest(
                    BigDecimal.valueOf(5000),
                    0,
                    LocalDate.of(2025, 3, 18)
            );
            ResponseEntity<String> zeroRatsResponse = sendRequest(zeroRatsRequest, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, zeroRatsResponse.getStatusCode(), "Przypadek 2: Oczekiwany status BAD_REQUEST.");


            CalculationRequest negativeKwotaRequest = new CalculationRequest(
                    BigDecimal.valueOf(-1000),
                    6,
                    LocalDate.of(2025, 4, 1)
            );
            ResponseEntity<String> negativeKwotaResponse = sendRequest(negativeKwotaRequest, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, negativeKwotaResponse.getStatusCode(), "Przypadek 3: Oczekiwany status BAD_REQUEST.");
        }

        private <T> ResponseEntity<T> sendRequest(CalculationRequest request, Class<T> responseType) {
            HttpEntity<CalculationRequest> httpEntity = new HttpEntity<>(request);
            return restTemplate.exchange(
                    "/api/calculate",
                    HttpMethod.POST,
                    httpEntity,
                    responseType
            );
        }
    }
