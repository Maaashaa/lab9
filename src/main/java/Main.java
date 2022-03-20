import fraction.Fraction;
import fraction.FractionStatementParser;
import fraction.FractionStatementResolver;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);

    private static FractionStatementParser.ComplexFractionStatement getStringFractionFromConsole() {
        System.out.println("Введите строку с несколькими дробями и операциями,которые необходимо выполнить.\nСтрока должна быть введена в формате n/m(пробел)операция(пробел)k/l...: ");
        String str = in.nextLine();
        return FractionStatementParser.parseInputAndReturnComplexStatement(str);
    }

    private static void calculateFractionsInStringForm(FractionStatementParser.ComplexFractionStatement complexFractionStatement) {
        Fraction result = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Результат: " + result);
    }
    public static void main(String args[]) {
        try {
            calculateFractionsInStringForm(getStringFractionFromConsole());
        } catch (Throwable e){
            System.out.println("ОШИБКА!");
            System.out.println("Введите выражение ещё раз. Для этого перезапустите программу)");
        }
        in.close();
    }
}