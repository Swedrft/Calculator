package pl.coderion.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

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
