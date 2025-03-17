package pl.coderion.Calculator;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
class CalculationRequest {
    private BigDecimal kwota;
    private int liczbaRat;
    private LocalDate dataPoczatkowa;
}


@RequestMapping(value = "/api/calculate")
@RequiredArgsConstructor
public class RestController {

    private final CalculatorService calculatorService;

    @Autowired
    public Oferta calculate(@RequestBody CalculationRequest request) {
        return calculatorService.calculate(
                request.getKwota(),
                request.getLiczbaRat(),
                request.getDataPoczatkowa()
        );
    }
}
