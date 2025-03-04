package Calculator.Coderion.Calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
		scanner.nextLine();

		System.out.println("Wpisz ilosc rat:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz ilosc rat:");
			scanner.next();
		}
		int ilosc_rat = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Wpisz oprocentowanie roczne:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz oprocentowanie roczne:");
			scanner.next();
		}
		int oprocentowanie_roczne = scanner.nextInt();
		scanner.nextLine();

		System.out.println("liczba miesiecznych rat:");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba. Wpisz liczba miesiecznych rat:");
			scanner.next();
		}
		int liczba_rat_miesiac = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Kwota: " + kwota);
		System.out.println("Ilosc rat: " + ilosc_rat);
		System.out.println("Oprocentowanie roczne: " + oprocentowanie_roczne + "%");
		System.out.println("Liczba rat miesiecznie: " + liczba_rat_miesiac);
	CalculatorService CalculatorService= new CalculatorService();
	CalculatorService.calculate();
	}
}