package pl.coderion.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/calculate")
public class RestController {

    private final CalculatorService calculatorService;

    @Autowired
    public RestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public Oferta calculate(@RequestBody CalculationRequest request) {

        return calculatorService.calculate(
                request.getKwota(),
                request.getLiczbaRat(),
                request.getDataPoczatkowa()
        );
    }
}

class CalculationRequest {
    private BigDecimal kwota;
    private int liczbaRat;
    private LocalDate dataPoczatkowa;

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public int getLiczbaRat() {
        return liczbaRat;
    }

    public void setLiczbaRat(int liczbaRat) {
        this.liczbaRat = liczbaRat;
    }

    public LocalDate getDataPoczatkowa() {
        return dataPoczatkowa;
    }

    public void setDataPoczatkowa(LocalDate dataPoczatkowa) {
        this.dataPoczatkowa = dataPoczatkowa;
    }
}
