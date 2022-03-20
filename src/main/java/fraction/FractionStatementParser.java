package fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FractionStatementParser {
    public enum Operations {
        SUM(1), DIF(1), MULTIPLICATION(2), DIVISION(2);
        private final int priority;

        Operations(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    /**
     * Пример: " + " " - " " * " " : "
     */
    private static final String OPERATION_FROM_FRACTION_STATEMENT_REGEXP = "\\s[\\+\\-\\*\\:]?\\s";

    /**
     * Пример (возможная размерность): "1" "12" "123" "1234" "12345"
     */
    private static final String NUMERATOR_DENOMINATOR_REGEXP = "-?\\d{1,5}";

    /**
     * Пример:
     *  1) Число пробелов "3/4" or "3 / 4" or "3/ 4" or "3 /4"
     *  2) Возможная размерность числителя и знаменателя - "1" "12" "123" "1234" "12345"
     */
    //private static final String CORRECT_FRACTION_WITH_SPACES_REGEXP = "^\\d{1,5}\\s?/\\s?\\d{1,5}$";

    /**
     * Пример:
     * 1) Число пробелов "3/4"
     * 2) Возможная размерность числителя и знаменателя - "1" "12" "123" "1234" "12345"
     */
    private static final String CORRECT_FRACTION_WITHOUT_SPACES_REGEXP = "-?\\d{1,5}/-?\\d{1,5}";

    /**
     * Пример: "22/4123 + 1/2"
     */
    private static final String CORRECT_FRACTION_STATEMENT = "^-?\\d{1,5}/-?\\d{1,5}\\s[\\+\\-\\*\\:]?\\s-?\\d{1,5}/-?\\d{1,5}$";

    private static List<String> matchInputByUsingRegexp(String input, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(input.substring(matcher.start(), matcher.end()));
        }
        return result;
    }

    private static boolean isInputCorrect(String input) {
        boolean isCorrect = input.matches(COMPLEX_FRACTION_STATEMENT_ELEMENT);
        if (!isCorrect) {
            System.out.println("Формат введенного выражения не соответсвует необходимому!");
            System.out.println("Введите выражнение ещё раз. Для этого перезапустите программу) ");
            System.exit(1);
        }
        return isCorrect;
    }

    private static List<String> parseInputIntoFractions(String input) {
        return matchInputByUsingRegexp(input, CORRECT_FRACTION_WITHOUT_SPACES_REGEXP);
    }

    private static List<String> parseFractionStringIntoNumeratorAndDenominator(String fraction) {
        return matchInputByUsingRegexp(fraction, NUMERATOR_DENOMINATOR_REGEXP);
    }

    private static String parseInputIntoOperation(String input) {
        List<String> operationFromStatement = matchInputByUsingRegexp(input, OPERATION_FROM_FRACTION_STATEMENT_REGEXP);
        return operationFromStatement.get(0).substring(1, 2);
    }

    public static Fraction getFractionFromString(String fraction) {
        if(!fraction.matches(CORRECT_FRACTION_WITHOUT_SPACES_REGEXP)){
            throw new IllegalArgumentException();
        }
        List<String> el = parseFractionStringIntoNumeratorAndDenominator(fraction);
        int numerator = Integer.parseInt(el.get(0)) ;
        int denominator = Integer.parseInt(el.get(1));
            try {
                if(denominator!=0) {
                    return new Fraction(numerator, denominator);
                } else {
                    throw new ArithmeticException();
                }
            } catch (ArithmeticException e){
                System.out.println("На ноль делить нельзя!");
                throw new ArithmeticException();
            }
        }
        
    private static Operations operationsIntoEnum(String operation) {
        switch (operation) {
            case " + ":
                return Operations.SUM;
            case " - ":
                return Operations.DIF;
            case " * ":
                return Operations.MULTIPLICATION;
            case " : ":
                return Operations.DIVISION;
            default:
                throw new IllegalArgumentException("Такой операции нет");
        }
    }

    public static FractionStatement parseInputAndReturnFractions(String input) {
        if (isInputCorrect(input)) {
            List<String> fractions = parseInputIntoFractions(input);
            Fraction fraction1 = getFractionFromString(fractions.get(0));
            Fraction fraction2 = getFractionFromString(fractions.get(1));
            Operations operation = operationsIntoEnum(parseInputIntoOperation(input));
            return new FractionStatement(fraction1, fraction2, operation);
        }
        return null;
    }

    public static class FractionStatement {
        private final Fraction fraction1;
        private final Fraction fraction2;
        private final Operations operation;

        @Override
        public String toString() {
            String numerator = new String();
            String denominator = new String();
            return "fraction=" + numerator +
                    "/" + denominator;
        }

        public FractionStatement(Fraction fraction1, Fraction fraction2, Operations operation) {
            this.fraction1 = fraction1;
            this.fraction2 = fraction2;
            this.operation = operation;
        }

        public Fraction getFraction1() {
            return fraction1;
        }

        public Fraction getFraction2() {
            return fraction2;
        }

        public Operations getOperation() {
            return operation;
        }
    }

    private static final String COMPLEX_FRACTION_STATEMENT_ELEMENT = CORRECT_FRACTION_WITHOUT_SPACES_REGEXP + "|" + OPERATION_FROM_FRACTION_STATEMENT_REGEXP;
    public static final String COMPLEX_FRACTION_STATEMENT = "^(" + COMPLEX_FRACTION_STATEMENT_ELEMENT + ")*$";
    public static ComplexFractionStatement parseInputAndReturnComplexStatement(String input) {
            List<Object> elementListWithOriginalOrder = new ArrayList<>();
            List<Fraction> fractions = new ArrayList<>();
            List<Operations> operations = new ArrayList<>();

            List<String> statementElements = matchInputByUsingRegexp(input, COMPLEX_FRACTION_STATEMENT_ELEMENT);
            for (String element : statementElements) {
                if (element.matches(CORRECT_FRACTION_WITHOUT_SPACES_REGEXP)) {
                    Fraction fractionFromString = getFractionFromString(element);
                    fractions.add(fractionFromString);
                    elementListWithOriginalOrder.add(fractionFromString);
                } else if (element.matches(OPERATION_FROM_FRACTION_STATEMENT_REGEXP)) {
                    Operations operation = operationsIntoEnum(element);
                    operations.add(operation);
                    elementListWithOriginalOrder.add(operation);
                }
            }
        checkCorrectElementOrderInStatement(elementListWithOriginalOrder);
        if(fractions.size()-operations.size() != 1){
            throw new IllegalArgumentException();
        }
        return new ComplexFractionStatement(elementListWithOriginalOrder, fractions, operations);
    }

    private static void checkCorrectElementOrderInStatement(List<Object> elementListWithOriginalOrder) {
        try {
            for (int i = 0; i < elementListWithOriginalOrder.size(); i++) {
                Object element = elementListWithOriginalOrder.get(i);
                if (i % 2 == 0 && element instanceof FractionStatementParser.Operations) {
                    throw new IllegalArgumentException();
                } else if (i % 2 != 0 && element instanceof Fraction) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
    /*public static boolean isNumeric(String string) {
        int intValue;

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }*/

    public static class ComplexFractionStatement {
        private final List<Object> originalOrderList;
        private final List<Fraction> fractions;
        private final List<Operations> operations;

        public ComplexFractionStatement(List<Object> originalOrderList, List<Fraction> fractions, List<Operations> operations) {
            this.originalOrderList = originalOrderList;
            this.fractions = fractions;
            this.operations = operations;
        }
        @Override
        public String toString() {
            String numerator = new String();
            String denominator = new String();
            return "fraction=" + numerator +
                    "/" + denominator;
        }
        public List<Object> getOriginalOrderList() {
            return originalOrderList;
        }

        public List<Fraction> getFractions() {
            return fractions;
        }

        public List<Operations> getOperations() {
            return operations;
        }
    }
}