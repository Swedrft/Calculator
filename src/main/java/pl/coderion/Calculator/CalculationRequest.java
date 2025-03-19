package pl.coderion.Calculator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CalculationRequest {
    private BigDecimal kwota;
    private int liczbaRat;
    private LocalDate dataPoczatkowa;
}
