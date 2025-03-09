package Calculator.Coderion.Calculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    public List<Rata> calculate(int kwota, int liczba_rat, LocalDate dataPoczatkowa) {
        List<Rata> raty = new ArrayList<>();
        LocalDate data = dataPoczatkowa;


        for (int i = 1; i <= liczba_rat; i++) {
            LocalDate wynikowaData;

            if (data.getDayOfMonth() >= 21) {
                wynikowaData = data.plusMonths(2).withDayOfMonth(10);
            } else {
                wynikowaData = data.plusMonths(1).withDayOfMonth(10);
            }

            long liczbaDniNaSplate = ChronoUnit.DAYS.between(data, wynikowaData);


            Rata rata = new Rata(i, wynikowaData, liczbaDniNaSplate);
            raty.add(rata);


            data = wynikowaData;
        }

        return raty;
    }

}
