package pl.coderion.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class CalculationRequest {

    @NotNull
    @DecimalMin(value = "0.1",message = "kwota musi być wieksza od zera")
    private BigDecimal kwota;

    @Min(value = 1,message = "Liczba rat nie moze być zerem" )
    private int liczbaRat;

    @NotNull(message = "data poczatkowa nie moze być pusta")
    private LocalDate dataPoczatkowa;

}
