package Calculator.Coderion.Calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
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

		int Karencja_w_miesiacach = 0;

		LocalDate data = LocalDate.of(2025,3,6);
		int rok = data.getYear();
		int miesiac = data.getMonthValue();
		int dzien = data.getDayOfMonth();

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

		int Rata_kapitalowa_techniczna = kwota / liczba_rat;
		BigDecimal zaokraglona_Rata_kapitalowa = BigDecimal.valueOf(Rata_kapitalowa_techniczna).setScale(2, RoundingMode.HALF_UP);




		System.out.println("Kwota: " + kwota);
		System.out.println("Ilosc rat: " + liczba_rat);
		System.out.println("Oprocentowanie roczne: " + 23 + "%");
		System.out.println( "Suma odsetek: " +suma_odsetek+ "zl" );
		System.out.println("Rata Kapitalowa techniczna: " +zaokraglona_Rata_kapitalowa);




	CalculatorService CalculatorService= new CalculatorService();



		System.out.println("Rata kredytu: " + CalculatorService.calculate(kwota,liczba_rat,suma_odsetek,Rata_kapitalowa_techniczna));
		for (int i = 1; i <= liczba_rat; i++) {
			LocalDate wynikowaData;


			if (data.getDayOfMonth() >= 21) {
				wynikowaData = data.plusMonths(2 + Karencja_w_miesiacach).withDayOfMonth(10);
			} else {
				wynikowaData = data.plusMonths(1).withDayOfMonth(10);
			}


			long liczba_dni_na_splate = ChronoUnit.DAYS.between(data, wynikowaData);


			data = wynikowaData;


			System.out.println("Liczba rat: " + i + "  | Termin platnosci: " + wynikowaData + "  | Liczba dni na splate: " + liczba_dni_na_splate);
		}

	}
}