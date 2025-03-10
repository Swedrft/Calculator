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
    private static final  int dzien_splaty_raty=10;


    public List<Rata> calculate(double kwota, int liczba_rat, LocalDate dataPoczatkowa) {
        List<Rata> raty = new ArrayList<>();
        LocalDate data = dataPoczatkowa;



        double prowizja_operacyjna_brutto =337.66;
        double wysokosc_raty = (kwota + prowizja_operacyjna_brutto)/liczba_rat;


        double rataKapitalowaTechniczna = kwota / liczba_rat;

        for (int i = 1; i <= liczba_rat; i++) {
            LocalDate wynikowaData;

            if (data.getDayOfMonth() >= 21) {
                wynikowaData = data.plusMonths(2).withDayOfMonth(10);
            } else {
                wynikowaData = data.plusMonths(1).withDayOfMonth(10);
            }

            long liczbaDniNaSplate = ChronoUnit.DAYS.between(data, wynikowaData);
            data = wynikowaData;

            kwota -= wysokosc_raty;


            double dziennaStopaProcentowa = 0.23 / 365;
            double odsetki = kwota*dziennaStopaProcentowa*liczbaDniNaSplate;


            BigDecimal zaokragloneOdsetki = new BigDecimal(odsetki).setScale(2, RoundingMode.HALF_UP);


            Rata rata = new Rata(i, wynikowaData, liczbaDniNaSplate, zaokragloneOdsetki.doubleValue());
            raty.add(rata);
        }

        return raty;
    }
}
