package Calculator.Coderion.Calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {

		SpringApplication.run(CalculatorApplication.class, args);
		int Numer_raty = 1;
		int ilosc_rat = 18;
		double wysokosc_raty =203.95;
		double wynik;
		if (Numer_raty>ilosc_rat){
			wynik = 0;
			System.out.println(wynik);
		}else System.out.println(wysokosc_raty);

	}






}
