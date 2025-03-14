package pl.coderion.Calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestControllerTest {

    @Test
    void calculateShouldReturnOfertaWhenInputIsValid() {

        CalculatorService mockService = mock(CalculatorService.class);
        List<Rata> rata = List.of(new Rata(1, LocalDate.now(), 30, 5.0));


        Oferta mockOferta = new Oferta(rata, BigDecimal.valueOf(50), BigDecimal.valueOf(150));
        when(mockService.calculate(
                BigDecimal.valueOf(5000), 6, LocalDate.of(2025, 3, 7)))
                .thenReturn(mockOferta);


        RestController controller = new RestController(mockService);


        CalculationRequest request = new CalculationRequest();
        request.setKwota(BigDecimal.valueOf(5000));
        request.setLiczbaRat(6);
        request.setDataPoczatkowa(LocalDate.of(2025, 3, 7));


        Oferta result = controller.calculate(request);


        assertNotNull(result, "Oferta nie powinna być nullem");
        assertEquals(mockOferta.getRaty(), result.getRaty(), "Lista rat powinna być zgodna");
        assertEquals(mockOferta.getSumaOdsetki(), result.getSumaOdsetki(), "Suma odsetek powinna być zgodna");
        assertEquals(mockOferta.getWysokoscraty(), result.getWysokoscraty(), "Wysokość raty powinna być zgodna");
    }

    @Test
    void calculateShouldThrowExceptionWhenInputIsInvalid() {

        CalculatorService mockService = mock(CalculatorService.class);
        when(mockService.calculate(any(), anyInt(), any())).thenThrow(IllegalArgumentException.class);


        RestController controller = new RestController(mockService);


        CalculationRequest request = new CalculationRequest();
        request.setKwota(BigDecimal.valueOf(-100));
        request.setLiczbaRat(0);
        request.setDataPoczatkowa(LocalDate.of(2025, 3, 7));


        assertThrows(IllegalArgumentException.class, () -> controller.calculate(request),
                "Powinno rzucić wyjątek dla niepoprawnych danych wejściowych");
    }
}
