package pl.coderion.calculator;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class CalculatorService {
    private static final int oprocentowanie_roczne = 23;

    public Oferta calculate(BigDecimal kwota, int liczba_rat, LocalDate dataPoczatkowa) {

        if (kwota == null || kwota.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Kwota musi być wieksza od 0");
        }
        if (liczba_rat <= 0) {
            throw new IllegalArgumentException("Liczba rat musi być wieksza od 0");
        }
        if (dataPoczatkowa == null) {
            throw new IllegalArgumentException("Data poczatkowa nie moze być pusta");
        }


        List<Rata> raty = new ArrayList<>();
        BigDecimal rataKapitalowaTechniczna = kwota.divide(BigDecimal.valueOf(liczba_rat), 2, RoundingMode.HALF_UP);

        BigDecimal pozostalyKapital = kwota;
        BigDecimal sumaOdsetki = BigDecimal.ZERO;

        LocalDate data = dataPoczatkowa;

        for (int i = 1; i <= liczba_rat; i++) {
            LocalDate terminPlatnosci = data.plusMonths(1).withDayOfMonth(10);
            long dniNaSplate = ChronoUnit.DAYS.between(data, terminPlatnosci);
            data = terminPlatnosci;

            BigDecimal dziennaStopaProcentowa = BigDecimal.valueOf(oprocentowanie_roczne)
                    .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_UP);

            BigDecimal odsetki = pozostalyKapital
                    .multiply(dziennaStopaProcentowa)
                    .multiply(BigDecimal.valueOf(dniNaSplate))
                    .setScale(2, RoundingMode.HALF_UP);

            sumaOdsetki = sumaOdsetki.add(odsetki);
            pozostalyKapital = pozostalyKapital.subtract(rataKapitalowaTechniczna);

            Rata rata = new Rata(i, terminPlatnosci, dniNaSplate, odsetki.doubleValue(), sumaOdsetki, 337.66);
            raty.add(rata);
        }

        BigDecimal wysokoscRaty = kwota.add(sumaOdsetki)
                .divide(BigDecimal.valueOf(liczba_rat), 2, RoundingMode.HALF_UP);

        return new Oferta(raty, sumaOdsetki, wysokoscRaty);
    }
}


