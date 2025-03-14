package pl.coderion.Calculator;

import org.springframework.web.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
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


@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/calculate")
public class RestController {

    private CalculatorService calculatorService;

    public RestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }



    @PostMapping
    public Oferta calculate(@RequestBody CalculationRequest request) {
        return calculatorService.calculate(
                request.getKwota(),
                request.getLiczbaRat(),
                request.getDataPoczatkowa()
        );
    }
}


