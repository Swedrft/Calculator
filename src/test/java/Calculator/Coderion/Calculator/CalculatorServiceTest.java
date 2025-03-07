package Calculator.Coderion.Calculator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    @Test
    public void testCalculate() {

        int kwota = 5000;
        int liczba_rat = 6;
        LocalDate dataPoczatkowa = LocalDate.of(2025, 3, 7);


        CalculatorService calculatorService = new CalculatorService();


        List<Rata> raty = calculatorService.calculate(kwota, liczba_rat, dataPoczatkowa);


        assertNotNull(raty, "Lista rat nie moze być nullem");
        assertEquals(liczba_rat, raty.size(), "Liczba rat powinna być zgodna");


        assertEquals(1, raty.get(0).getNumerRaty());
        assertEquals(LocalDate.of(2025, 4, 10), raty.get(0).getTerminPlatnosci());
        assertEquals(34, raty.get(0).getIloscDni());

        assertEquals(2, raty.get(1).getNumerRaty());
        assertEquals(LocalDate.of(2025, 5, 10), raty.get(1).getTerminPlatnosci());
        assertEquals(30, raty.get(1).getIloscDni());

        assertEquals(3, raty.get(2).getNumerRaty());
        assertEquals(LocalDate.of(2025, 6, 10), raty.get(2).getTerminPlatnosci());
        assertEquals(31, raty.get(2).getIloscDni());
        assertEquals(4, raty.get(3).getNumerRaty());
        assertEquals(LocalDate.of(2025, 7, 10), raty.get(3).getTerminPlatnosci());
        assertEquals(30, raty.get(3).getIloscDni());

        assertEquals(5, raty.get(4).getNumerRaty());
        assertEquals(LocalDate.of(2025, 8, 10), raty.get(4).getTerminPlatnosci());
        assertEquals(31, raty.get(4).getIloscDni());

        assertEquals(6, raty.get(5).getNumerRaty());
        assertEquals(LocalDate.of(2025, 9, 10), raty.get(5).getTerminPlatnosci());
        assertEquals(31, raty.get(5).getIloscDni());




    }
}
