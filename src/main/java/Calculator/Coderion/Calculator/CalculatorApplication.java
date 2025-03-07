package Calculator.Coderion.Calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CalculatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Wpisz kwote:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz kwote:");
			scanner.next();
		}
		 int kwota = scanner.nextInt();

		System.out.println("Wpisz liczbe rat:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz liczbe rat:");
			scanner.next();
		}
		int liczbaRat = scanner.nextInt();



		LocalDate dataPoczatkowa = LocalDate.now();
		System.out.println("Kwota: " + kwota);
		System.out.println("Ilosc rat: " + liczbaRat);
		System.out.println("Oprocentowanie roczne: " + 23 + "%");
		//System.out.println( "Suma odsetek: " +suma_odsetek+ "zl" );


		CalculatorService calculatorService = new CalculatorService();
		List<Rata> raty = calculatorService.calculate(kwota, liczbaRat, dataPoczatkowa);

		System.out.println("Harmonogram rat:");
		raty.forEach(System.out::println);
	}
}
