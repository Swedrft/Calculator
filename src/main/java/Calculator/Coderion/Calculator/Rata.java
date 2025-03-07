package Calculator.Coderion.Calculator;

import java.time.LocalDate;

public class Rata {
    private final int numerRaty;
    private final LocalDate terminPlatnosci;

    public Rata(int numerRaty, LocalDate terminPlatnosci) {
        this.numerRaty = numerRaty;
        this.terminPlatnosci = terminPlatnosci;
    }

    public int getNumerRaty() {
        return numerRaty;
    }

    public LocalDate getTerminPlatnosci() {
        return terminPlatnosci;
    }

    @Override
    public String toString() {
        return "Rata nr: " + numerRaty + ", Termin platnosci: " + terminPlatnosci;
    }
}
