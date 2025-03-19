package pl.coderion.Calculator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter



@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {

    private final CalculatorService calculatorService;

    @PostMapping("/calculate")
    public Oferta calculate(@RequestBody CalculationRequest request) {
        return calculatorService.calculate(
                request.getKwota(),
                request.getLiczbaRat(),
                request.getDataPoczatkowa()
        );
    }
}
