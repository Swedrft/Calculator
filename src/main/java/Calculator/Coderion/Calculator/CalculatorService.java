package Calculator.Coderion.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalculatorService {
    public BigDecimal rataKredytu;

    public List<Rata> calculate(int kwota, int liczba_rat, LocalDate dataPoczatkowa, int karencjaWMiesiacach) {
        int oprocentowanie_roczne = 23;
        double oprocentowanie_miesieczne = (double) oprocentowanie_roczne / 12 / 100;
        //double rata_kredytu = (double) (kwota + suma_odsetek) / liczba_rat;

        //rataKredytu = BigDecimal.valueOf(rata_kredytu).setScale(2, RoundingMode.HALF_UP);


        List<Rata> raty = new ArrayList<>();
        LocalDate data = dataPoczatkowa;

        for (int i = 1; i <= liczba_rat; i++) {
            LocalDate wynikowaData;

            if (data.getDayOfMonth() >= 21) {
                wynikowaData = data.plusMonths(2 + karencjaWMiesiacach).withDayOfMonth(10);
            } else {
                wynikowaData = data.plusMonths(1).withDayOfMonth(10);
            }

            long liczba_dni_na_splate = ChronoUnit.DAYS.between(data, wynikowaData);


            Rata rata = new Rata(i, wynikowaData);
            raty.add(rata);

            System.out.println("Liczba rat: " + i +  " | Liczba dni na splate: " + liczba_dni_na_splate);


            data = wynikowaData;
        }

        return raty;
    }
}
