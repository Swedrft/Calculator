package pl.coderion.calculator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Oferta {

    private List<Rata> raty;
    private BigDecimal sumaOdsetki;
    private BigDecimal wysokoscRaty;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Oferta:\n");
        raty.forEach(rata -> builder.append(rata.toString()).append("\n"));
        builder.append("Suma odsetek: ").append(sumaOdsetki).append(" zl");
        return builder.toString();
    }
}