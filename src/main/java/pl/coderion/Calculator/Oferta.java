package pl.coderion.Calculator;

import java.math.BigDecimal;
import java.util.List;


public class Oferta {
    private List<Rata> raty;
    private BigDecimal suma_odsetki;
    static BigDecimal Wysokoscraty;

    public Oferta(List<Rata> raty, BigDecimal suma_odsetki,BigDecimal Wysokosc_raty) {
        this.raty = raty;
        this.suma_odsetki = suma_odsetki;
        this.Wysokoscraty = Wysokosc_raty;
    }

    public List<Rata> getRaty() {
        return raty;
    }

    public BigDecimal getSumaOdsetki() {
        return suma_odsetki;
    }

    public BigDecimal getWysokoscraty(){
        return Wysokoscraty;
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
