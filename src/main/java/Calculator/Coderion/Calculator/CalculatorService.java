package Calculator.Coderion.Calculator;

import java.util.Scanner;
public class CalculatorService {
    public void calculate(int kwota, int liczba_rat_miesiac){
        int oprocentowanie_roczne =23;
        double oprocentowanie_miesieczne = (double) oprocentowanie_roczne / 12 / 100;
        double Rata_kredytu = kwota * oprocentowanie_miesieczne * Math.pow(1 + oprocentowanie_miesieczne, liczba_rat_miesiac) /
                (Math.pow(1 + oprocentowanie_miesieczne, liczba_rat_miesiac) - 1);
        System.out.println("Rata kredytu: " + Rata_kredytu);
    }
}
