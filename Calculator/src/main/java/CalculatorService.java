 import java.util.Scanner;
public class CalculatorService {
    public void calculate(){
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

        double oprocentowanie_miesieczne = (double) oprocentowanie_roczne / 12 / 100;
        double Rata_kredytu = kwota * oprocentowanie_miesieczne * Math.pow(1 + oprocentowanie_miesieczne, liczba_rat_miesiac) /
                (Math.pow(1 + oprocentowanie_miesieczne, liczba_rat_miesiac) - 1);
        System.out.println("Rata kredytu: " + Rata_kredytu);
    }
}
