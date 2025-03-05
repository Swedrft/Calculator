package Calculator.Coderion.Calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
public class CalculatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int suma_odsetek= (int) 571.04;

		System.out.println("Wpisz Datę kredytu:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna Data. Wpisz datę:");
			scanner.next();
		}
		int Data = scanner.nextInt();
		scanner.next();

		System.out.println("Wpisz kwote:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz kwote:");
			scanner.next();
		}
		int kwota = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Wpisz ilosc rat:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz ilosc rat:");
			scanner.next();
		}
		int liczba_rat = scanner.nextInt();
		scanner.nextLine();


		System.out.println("Kwota: " + kwota);
		System.out.println("Ilosc rat: " + liczba_rat);
		System.out.println("Oprocentowanie roczne: " + 23 + "%");
		System.out.println( "Suma odsetek: " +suma_odsetek+ "zl" );

	CalculatorService CalculatorService= new CalculatorService();



		System.out.println("Rata kredytu: " + CalculatorService.calculate(kwota,liczba_rat,suma_odsetek));
	}
}