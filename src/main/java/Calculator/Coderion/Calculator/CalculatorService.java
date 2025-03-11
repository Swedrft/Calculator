package Calculator.Coderion.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class CalculatorService {
    public BigDecimal rataKredytu;
    private static final int KARENCJA_W_MIESIACACH = 0;
    private static final int oprocentowanie_roczne = 23;
    private static final int dzien_splaty_raty = 10;

    public List<Rata> calculate(double kwota, int liczba_rat, LocalDate dataPoczatkowa) {
        List<Rata> raty = new ArrayList<>();
        LocalDate data = dataPoczatkowa;

        BigDecimal suma_odsetki = BigDecimal.ZERO;

        BigDecimal prowizjaOperacyjnaBrutto = BigDecimal.valueOf(337.66);
        BigDecimal wysokoscRaty = BigDecimal.valueOf(kwota)
                .add(prowizjaOperacyjnaBrutto)
                .divide(BigDecimal.valueOf(liczba_rat), 2, RoundingMode.HALF_UP);

        BigDecimal rataKapitalowaTechniczna = BigDecimal.valueOf(kwota)
                .divide(BigDecimal.valueOf(liczba_rat), 2, RoundingMode.HALF_UP);

        BigDecimal pozostalyKapital = BigDecimal.valueOf(kwota);


        for (int i = 1; i <= liczba_rat; i++) {
            if (pozostalyKapital.compareTo(BigDecimal.ZERO) <= 0) {
                pozostalyKapital = BigDecimal.ZERO;
                break;
            }

            LocalDate wynikowaData = (data.getDayOfMonth() >= 21)
                    ? data.plusMonths(2).withDayOfMonth(10)
                    : data.plusMonths(1).withDayOfMonth(10);

            long liczbaDniNaSplate = ChronoUnit.DAYS.between(data, wynikowaData);
            data = wynikowaData;

            BigDecimal dziennaStopaProcentowa = BigDecimal.valueOf(oprocentowanie_roczne)
                    .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_UP);

            BigDecimal odsetki = pozostalyKapital
                    .multiply(dziennaStopaProcentowa)
                    .multiply(BigDecimal.valueOf(liczbaDniNaSplate))
                    .setScale(2, RoundingMode.HALF_UP);

            Rata rata = new Rata(i, wynikowaData, liczbaDniNaSplate, odsetki.doubleValue());
            raty.add(rata);


              suma_odsetki = suma_odsetki.add(odsetki).setScale(2, RoundingMode.HALF_UP);

            pozostalyKapital = pozostalyKapital.subtract(rataKapitalowaTechniczna).setScale(2, RoundingMode.HALF_UP);
        }


        System.out.println("Suma odsetek: " + suma_odsetki + " zl");

        return raty;
    }
}
