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

        if (request.getKwota() == null || request.getKwota().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kwota musi byc wieksza od 0");
        }
        if (request.getLiczbaRat() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Liczba rat musi byc wieksza od 0");
        }
        if (request.getDataPoczatkowa() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data poczatkowa nie moze byc pusta");
        }


        return calculatorService.calculate(
                request.getKwota(),
                request.getLiczbaRat(),
                request.getDataPoczatkowa()
        );
    }
}
