package Calculator.Coderion.Calculator;

import java.time.LocalDate;

public class Rata {
    private int numer;
    private LocalDate terminPlatnosci;
    private long iloscDni;

    public Rata(int numer, LocalDate terminPlatnosci, long iloscDni) {
        this.numer = numer;
        this.terminPlatnosci = terminPlatnosci;
        this.iloscDni = iloscDni;
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
                ", Liczba dni na splate: " + iloscDni;
    }
}
