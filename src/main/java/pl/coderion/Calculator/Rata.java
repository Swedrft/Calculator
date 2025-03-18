package pl.coderion.Calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class Rata {
    private int numer;
    private LocalDate terminPlatnosci;
    private long iloscDni;
    private double odsetki;
    private BigDecimal sumaodsetek;
    private double prowizja_operacyjna;



    @Override
    public String toString() {
        return "Numer raty: " + numer +
                ", Termin platnosci: " + terminPlatnosci +
                ", Liczba dni na splate: " + iloscDni +
                ", Odsetki: " + odsetki;

    }
}

