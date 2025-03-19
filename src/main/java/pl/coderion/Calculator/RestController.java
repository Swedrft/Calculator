package pl.coderion.Calculator;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {

    private final CalculatorService calculatorService;

    @PostMapping("/calculate")
    public Oferta calculate(@RequestBody pl.coderion.Calculator.CalculationRequest request) {
        {
            return calculatorService.calculate(
                    request.getKwota(),
                    request.getLiczbaRat(),
                    request.getDataPoczatkowa()
            );
        }
    }
}
