package Calculator.Coderion.Calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    @Test
    public void testCalculate() {

        int kwota = 5000;
        int liczba_rat = 6;
        LocalDate dataPoczatkowa = LocalDate.of(2025, 3, 7);

        CalculatorService calculatorService = new CalculatorService();

        Oferta oferta = calculatorService.calculate(kwota, liczba_rat, dataPoczatkowa);

        assertNotNull(oferta, "Obiekt Oferta nie może być nullem");
        assertNotNull(oferta.getRaty(), "Lista rat w ofercie nie może być nullem");
        assertEquals(liczba_rat, oferta.getRaty().size(), "Liczba rat powinna być zgodna");

        assertEquals(1, oferta.getRaty().get(0).getNumerRaty());
        assertEquals(LocalDate.of(2025, 4, 10), oferta.getRaty().get(0).getTerminPlatnosci());
        assertEquals(34, oferta.getRaty().get(0).getIloscDni());

        assertEquals(2, oferta.getRaty().get(1).getNumerRaty());
        assertEquals(LocalDate.of(2025, 5, 10), oferta.getRaty().get(1).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(1).getIloscDni());

        assertEquals(3, oferta.getRaty().get(2).getNumerRaty());
        assertEquals(LocalDate.of(2025, 6, 10), oferta.getRaty().get(2).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(2).getIloscDni());

        assertEquals(4, oferta.getRaty().get(3).getNumerRaty());
        assertEquals(LocalDate.of(2025, 7, 10), oferta.getRaty().get(3).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(3).getIloscDni());

        assertEquals(5, oferta.getRaty().get(4).getNumerRaty());
        assertEquals(LocalDate.of(2025, 8, 10), oferta.getRaty().get(4).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(4).getIloscDni());

        assertEquals(6, oferta.getRaty().get(5).getNumerRaty());
        assertEquals(LocalDate.of(2025, 9, 10), oferta.getRaty().get(5).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(5).getIloscDni());

        assertTrue(oferta.getSumaOdsetki().compareTo(BigDecimal.ZERO) > 0, "Suma odsetek powinna być większa od zera");
    }

    @Test
    public void testCalculatePoDniu21() {

        int kwota = 5000;
        int liczba_rat = 6;
        LocalDate dataPoczatkowa = LocalDate.of(2025, 3, 22);

        CalculatorService calculatorService = new CalculatorService();

        Oferta oferta = calculatorService.calculate(kwota, liczba_rat, dataPoczatkowa);

        assertNotNull(oferta, "Obiekt Oferta nie może być nullem");
        assertNotNull(oferta.getRaty(), "Lista rat w ofercie nie może być nullem");
        assertEquals(liczba_rat, oferta.getRaty().size(), "Liczba rat powinna być zgodna");

        assertEquals(1, oferta.getRaty().get(0).getNumerRaty());
        assertEquals(LocalDate.of(2025, 5, 10), oferta.getRaty().get(0).getTerminPlatnosci());
        assertEquals(49, oferta.getRaty().get(0).getIloscDni());

        assertEquals(2, oferta.getRaty().get(1).getNumerRaty());
        assertEquals(LocalDate.of(2025, 6, 10), oferta.getRaty().get(1).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(1).getIloscDni());

        assertEquals(3, oferta.getRaty().get(2).getNumerRaty());
        assertEquals(LocalDate.of(2025, 7, 10), oferta.getRaty().get(2).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(2).getIloscDni());

        assertEquals(4, oferta.getRaty().get(3).getNumerRaty());
        assertEquals(LocalDate.of(2025, 8, 10), oferta.getRaty().get(3).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(3).getIloscDni());

        assertEquals(5, oferta.getRaty().get(4).getNumerRaty());
        assertEquals(LocalDate.of(2025, 9, 10), oferta.getRaty().get(4).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(4).getIloscDni());

        assertEquals(6, oferta.getRaty().get(5).getNumerRaty());
        assertEquals(LocalDate.of(2025, 10, 10), oferta.getRaty().get(5).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(5).getIloscDni());

        assertTrue(oferta.getSumaOdsetki().compareTo(BigDecimal.ZERO) > 0, "Suma odsetek powinna być większa od zera");
    }
    @Test
    public void testOdsetki() {
        int kwota = 5000;
        int liczbaRat = 6;
        LocalDate dataPoczatkowa = LocalDate.of(2025, 3, 11);

        CalculatorService calculatorService = new CalculatorService();

        Oferta oferta = calculatorService.calculate(kwota, liczbaRat, dataPoczatkowa);

        assertNotNull(oferta, "Obiekt Oferta nie może być nullem");
        assertNotNull(oferta.getRaty(), "Lista rat w ofercie nie może być nullem");
        assertEquals(liczbaRat, oferta.getRaty().size(), "Liczba rat powinna być zgodna");


        assertEquals(1, oferta.getRaty().get(0).getNumerRaty());
        assertEquals(LocalDate.of(2025, 4, 10), oferta.getRaty().get(0).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(0).getIloscDni());
        assertEquals(94.52, oferta.getRaty().get(0).getOdsetki(), "Odsetki powinny wynosić 94.52");

        assertEquals(2, oferta.getRaty().get(1).getNumerRaty());
        assertEquals(LocalDate.of(2025, 5, 10), oferta.getRaty().get(1).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(1).getIloscDni());
        assertEquals(78.77, oferta.getRaty().get(1).getOdsetki(), "Odsetki powinny wynosić 78.77");

        assertEquals(3, oferta.getRaty().get(2).getNumerRaty());
        assertEquals(LocalDate.of(2025, 6, 10), oferta.getRaty().get(2).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(2).getIloscDni());
        assertEquals(65.11, oferta.getRaty().get(2).getOdsetki(), "Odsetki powinny wynosić 65.11");

        assertEquals(4, oferta.getRaty().get(3).getNumerRaty());
        assertEquals(LocalDate.of(2025, 7, 10), oferta.getRaty().get(3).getTerminPlatnosci());
        assertEquals(30, oferta.getRaty().get(3).getIloscDni());
        assertEquals(47.26, oferta.getRaty().get(3).getOdsetki(), "Odsetki powinny wynosić 47.26");

        assertEquals(5, oferta.getRaty().get(4).getNumerRaty());
        assertEquals(LocalDate.of(2025, 8, 10), oferta.getRaty().get(4).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(4).getIloscDni());
        assertEquals(32.56, oferta.getRaty().get(4).getOdsetki(), "Odsetki powinny wynosić 32.56");

        assertEquals(6, oferta.getRaty().get(5).getNumerRaty());
        assertEquals(LocalDate.of(2025, 9, 10), oferta.getRaty().get(5).getTerminPlatnosci());
        assertEquals(31, oferta.getRaty().get(5).getIloscDni());
        assertEquals(16.28, oferta.getRaty().get(5).getOdsetki(), "Odsetki powinny wynosić 16.28");
    }

}
