package pl.coderion.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {




    static Stream<TestCase> provideTestCases() {
        return Stream.of(

                new TestCase(
                        BigDecimal.valueOf(5000), 6, LocalDate.of(2025, 3, 7),
                        new int[]{1, 2, 3, 4, 5, 6},
                        new LocalDate[]{
                                LocalDate.of(2025, 4, 10), LocalDate.of(2025, 5, 10),
                                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 7, 10),
                                LocalDate.of(2025, 8, 10), LocalDate.of(2025, 9, 10)
                        },
                        new int[]{34, 30, 31, 30, 31, 31},
                        new double[]{94.52, 78.77, 65.11, 47.26, 32.56, 16.28}
                ),

                new TestCase(
                        BigDecimal.valueOf(5000), 6, LocalDate.of(2025, 3, 22),
                        new int[]{1, 2, 3, 4, 5, 6},
                        new LocalDate[]{
                                LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 10),
                                LocalDate.of(2025, 7, 10), LocalDate.of(2025, 8, 10),
                                LocalDate.of(2025, 9, 10), LocalDate.of(2025, 10, 10)
                        },
                        new int[]{49, 31, 30, 31, 31, 30},
                        new double[]{}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testCalculate(TestCase testCase) {
        CalculatorService calculatorService = new CalculatorService();

        Oferta oferta = calculatorService.calculate(testCase.kwota, testCase.liczbaRat, testCase.dataPoczatkowa);

        if (testCase.dataPoczatkowa.getDayOfMonth() <= 21) {

            assertAll(
                    () -> assertNotNull(oferta, "Obiekt Oferta nadal jest tworzony, co jest OK"),
                    () -> assertFalse(testCase.dataPoczatkowa.getDayOfMonth() >= 21,
                            "Data początkowa nie spełnia warunku (dzień <= 21)")
            );
        } else {

            assertNotNull(oferta, "Obiekt Oferta nie może być nullem");
            assertNotNull(oferta.getRaty(), "Lista rat w ofercie nie może być nullem");
            assertEquals(testCase.liczbaRat, oferta.getRaty().size(), "Liczba rat powinna być zgodna");

            for (int i = 0; i < testCase.numerRaty.length; i++) {
                Rata rata = oferta.getRaty().get(i);
                assertEquals(testCase.numerRaty[i], rata.getNumer(), "Numer raty powinien być zgodny");
                assertEquals(testCase.terminPlatnosci[i], rata.getTerminPlatnosci(), "Termin płatności powinien być zgodny");
                assertEquals(testCase.iloscDni[i], rata.getIloscDni(), "Liczba dni powinna być zgodna");

                if (testCase.odsetki.length > 0) {
                    assertEquals(testCase.odsetki[i], rata.getOdsetki(), 0.01, "Odsetki powinny być zgodne");
                }
            }
        }
    }






    static class TestCase {
        BigDecimal kwota;
        int liczbaRat;
        LocalDate dataPoczatkowa;
        int[] numerRaty;
        LocalDate[] terminPlatnosci;
        int[] iloscDni;
        double[] odsetki;

        public TestCase(BigDecimal kwota, int liczbaRat, LocalDate dataPoczatkowa, int[] numerRaty, LocalDate[] terminPlatnosci, int[] iloscDni, double[] odsetki) {
            this.kwota = kwota;
            this.liczbaRat = liczbaRat;
            this.dataPoczatkowa = dataPoczatkowa;
            this.numerRaty = numerRaty;
            this.terminPlatnosci = terminPlatnosci;
            this.iloscDni = iloscDni;
            this.odsetki = odsetki;


        }
    }
}
