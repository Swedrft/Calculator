package Calculator.Coderion.Calculator;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorService {
    public BigDecimal rataKredytu;

    public BigDecimal calculate(int kwota, int liczba_rat,int suma_odsetek) {
        int oprocentowanie_roczne = 23;
        double oprocentowanie_miesieczne = (double) oprocentowanie_roczne / 12 / 100;
        double rata_kredytu = (double) (kwota + suma_odsetek) /liczba_rat;
        rataKredytu = BigDecimal.valueOf(rata_kredytu).setScale(2, RoundingMode.HALF_UP);
        return rataKredytu;
    }


}

