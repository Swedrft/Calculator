package Calculator.Coderion.Calculator;

import java.math.BigDecimal;
import java.util.List;

public class Oferta {
    private List<Rata> raty;
    private BigDecimal suma_odsetki;

    public Oferta(List<Rata> raty, BigDecimal suma_odsetki) {
        this.raty = raty;
        this.suma_odsetki = suma_odsetki;
    }

    public List<Rata> getRaty() {
        return raty;
    }

    public BigDecimal getSumaOdsetki() {
        return suma_odsetki;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Oferta:\n");
        raty.forEach(rata -> builder.append(rata.toString()).append("\n"));
        builder.append("Suma odsetek: ").append(suma_odsetki).append(" zl");
        return builder.toString();
    }
}
