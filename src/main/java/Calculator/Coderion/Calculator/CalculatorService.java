package Calculator.Coderion.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    public BigDecimal rataKredytu;
    private static final int KARENCJA_W_MIESIACACH = 0;
    private static final  int oprocentowanie_roczne = 23;
    public List<Rata> calculate(int kwota, int liczba_rat, LocalDate dataPoczatkowa) {

        double oprocentowanie_miesieczne = (double) oprocentowanie_roczne / 12 / 100;

        List<Rata> raty = new ArrayList<>();
        LocalDate data = dataPoczatkowa;

        for (int i = 1; i <= liczba_rat; i++) {
            LocalDate wynikowaData;

            if (data.getDayOfMonth() >= 21) {
                wynikowaData = data.plusMonths(2 + KARENCJA_W_MIESIACACH).withDayOfMonth(10);
            } else {
                wynikowaData = data.plusMonths(1).withDayOfMonth(10);
            }

            long liczba_dni_na_splate = ChronoUnit.DAYS.between(data, wynikowaData);

            Rata rata = new Rata(i, wynikowaData);
            raty.add(rata);

            System.out.println("Liczba rat: " + i + " | Liczba dni na splate: " + liczba_dni_na_splate);

            data = wynikowaData;
        }

        return raty;
    }
}
