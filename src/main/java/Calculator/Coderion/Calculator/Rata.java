package Calculator.Coderion.Calculator;

import java.time.LocalDate;

public class Rata {
    private int numer;
    private LocalDate terminPlatnosci;
    private long iloscDni;
    private double odsetki;
    private double prowizja_operacyjna;

    public Rata(int numer, LocalDate terminPlatnosci, long iloscDni, double odsetki) {
        this.numer = numer;
        this.terminPlatnosci = terminPlatnosci;
        this.iloscDni = iloscDni;
        this.odsetki = odsetki;

    }


    public double getOdsetki() {
        return odsetki;
    }

    public int getNumerRaty() {
        return numer;
    }

    public LocalDate getTerminPlatnosci() {
        return terminPlatnosci;
    }

    public long getIloscDni() {
        return iloscDni;
    }



    @Override
    public String toString() {
        return "Numer raty: " + numer +
                ", Termin platnosci: " + terminPlatnosci +
                ", Liczba dni na splate: " + iloscDni +
                ", Odsetki: " + odsetki;


    }
}
