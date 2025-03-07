package Calculator.Coderion.Calculator;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    @Test
    public void testCalculate() {

        int kwota = 5000;
        int liczba_rat = 6;
        LocalDate dataPoczatkowa = LocalDate.parse("2025-03-07");


        CalculatorService calculatorService = new CalculatorService();


        List<Rata> raty = calculatorService.calculate(kwota, liczba_rat, dataPoczatkowa);


        assertNotNull(raty, "Lista rat nie może być nullem");
        assertEquals(liczba_rat, raty.size(), "Liczba rat powinna być zgodna");

        assertEquals(1, raty.get(0).getNumerRaty());
        assertEquals(LocalDate.of(2025, 4, 10), raty.get(0).getTerminPlatnosci());

        assertEquals(2, raty.get(1).getNumerRaty());
        assertEquals(LocalDate.of(2025, 5, 10), raty.get(0).getTerminPlatnosci());

        assertEquals(3, raty.get(2).getNumerRaty());
        assertEquals(LocalDate.of(2025, 6, 10), raty.get(0).getTerminPlatnosci());

        assertEquals(4, raty.get(3).getNumerRaty());
        assertEquals(LocalDate.of(2025, 7, 10), raty.get(0).getTerminPlatnosci());

        assertEquals(5, raty.get(4).getNumerRaty());
        assertEquals(LocalDate.of(2025, 8, 10), raty.get(0).getTerminPlatnosci());

        assertEquals(6, raty.get(5).getNumerRaty());
        assertEquals(LocalDate.of(2025, 9, 10), raty.get(0).getTerminPlatnosci());


    }
    }

