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
        LocalDate dataPoczatkowa = LocalDate.now();
        int karencjaWMiesiacach = 0;


        CalculatorService calculatorService = new CalculatorService();


        List<Rata> raty = calculatorService.calculate(kwota, liczba_rat, dataPoczatkowa, karencjaWMiesiacach);


        assertNotNull(raty, "Lista rat nie może być nullem");
        assertEquals(liczba_rat, raty.size(), "Liczba rat powinna być zgodna");


        for (int i = 0; i < raty.size(); i++) {
            Rata rata = raty.get(i);

            // Numer raty
            assertEquals(i + 1, rata.getNumerRaty(), "Numer raty powinien być zgodny");


            LocalDate oczekiwanaData = dataPoczatkowa.plusMonths(i + 1 + karencjaWMiesiacach).withDayOfMonth(10);
            assertEquals(oczekiwanaData, rata.getTerminPlatnosci(), "Data raty powinna być zgodna");


            long liczbaDniNaSplate = ChronoUnit.DAYS.between(
                    dataPoczatkowa.plusMonths(i).withDayOfMonth(10), oczekiwanaData);


            System.out.println("Numer raty: " + rata.getNumerRaty() + ", Data: " + rata.getTerminPlatnosci() +
                    ", Oczekiwane dni: " + liczbaDniNaSplate);
        }
    }
}
