import fraction.FractionStatementParser;
import fraction.FractionStatementResolver;
import fraction.Fraction;
import org.junit.Assert;
import org.junit.Test;

public class FractionStatementTest {
    @Test
    public void testStatementResolving1(){
        String input = "1/2 + 1/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(1, 2);
        Fraction fraction1 = new Fraction(1, 3);
        Fraction expectedFraction1 = Fraction.sum(fraction0, fraction1);
        System.out.println("Expected result: " + expectedFraction1);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction1,resultFraction);
    }
    @Test
    public void testStatementResolving2() {
        String input = "1/5 - 2/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(1, 5);
        Fraction fraction1 = new Fraction(2, 3);
        Fraction expectedFraction2 = Fraction.dif(fraction0, fraction1);
        System.out.println("Expected result: " + expectedFraction2);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction2,resultFraction);
    }
    @Test
    public void testStatementResolving3() {
        String input = "2/5 * 2/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(2, 5);
        Fraction fraction1 = new Fraction(2, 3);
        Fraction expectedFraction3 = Fraction.multiplic(fraction0, fraction1);
        System.out.println("Expected result: " + expectedFraction3);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction3,resultFraction);
    }
    @Test
    public void testStatementResolving4() {
        String input = "2/7 : 5/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(2, 7);
        Fraction fraction1 = new Fraction(5, 3);
        Fraction expectedFraction4 = Fraction.div(fraction0, fraction1);
        System.out.println("Expected result: " + expectedFraction4);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction4,resultFraction);
    }
    @Test
    public void testComplexStatementResolving1() {
        String input = "1/3 + 2/3 - 1/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(1, 3);
        Fraction fraction1 = new Fraction(2, 3);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction expectedFraction5 = Fraction.dif(Fraction.sum(fraction0, fraction1), fraction2);
        System.out.println("Expected result: " + expectedFraction5);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction5,resultFraction);
    }
    @Test
    public void testComplexStatementResolving2() {
        String input = "1/3 + 2/3 * 1/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(1, 3);
        Fraction fraction1 = new Fraction(2, 3);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction expectedFraction6 = Fraction.sum(Fraction.multiplic(fraction1, fraction2), fraction0);
        System.out.println("Expected result: " + expectedFraction6);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction6,resultFraction);
    }
    @Test
    public void testComplexStatementResolving3() {
        String input = "1/3 + 2/3 * 1/3 - 1/3";
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        Fraction resultFraction = FractionStatementResolver.resolveComplexFractionStatement(complexFractionStatement);
        System.out.println("Actual result: " + resultFraction);

        Fraction fraction0 = new Fraction(1, 3);
        Fraction fraction1 = new Fraction(2, 3);
        Fraction fraction2 = new Fraction(1, 3);
        Fraction fraction3 = new Fraction(1, 3);

        Fraction multiplicFraction = Fraction.multiplic(fraction1, fraction2);
        Fraction sumFraction = Fraction.sum(fraction0, multiplicFraction);
        Fraction difFraction = Fraction.dif(sumFraction, fraction3);
        Fraction expectedFraction7 = difFraction;
        System.out.println("Expected result: " + expectedFraction7);
        Assert.assertEquals("Дроби должны быть одинаковыми",expectedFraction7,resultFraction);
    }
    @Test
    public void testComplexStatementParsing1() {
        String input = "1/3 + 2/3 - 1/3";
        Fraction fraction0 = new Fraction(1, 3);
        Fraction fraction1 = new Fraction(2, 3);
        Fraction fraction2 = new Fraction(1, 3);
        FractionStatementParser.ComplexFractionStatement complexFractionStatement = FractionStatementParser.parseInputAndReturnComplexStatement(input);

        System.out.println("Operation test:");
        System.out.println(complexFractionStatement.getOperations().size() == 2);
        System.out.println(complexFractionStatement.getOperations().get(0) == FractionStatementParser.Operations.SUM);
        System.out.println(complexFractionStatement.getOperations().get(1) == FractionStatementParser.Operations.DIF);

        System.out.println("\nFractions test:");
        System.out.println(complexFractionStatement.getFractions().size() == 3);
        System.out.println(complexFractionStatement.getFractions().get(0).equals(fraction0));
        System.out.println(complexFractionStatement.getFractions().get(1).equals(fraction1));
        System.out.println(complexFractionStatement.getFractions().get(2).equals(fraction2));

        System.out.println("\nOrder test");
        System.out.println(complexFractionStatement.getOriginalOrderList().size() == 5);
        System.out.println(complexFractionStatement.getOriginalOrderList().get(0).equals(fraction0));
        System.out.println(complexFractionStatement.getOriginalOrderList().get(1).equals(FractionStatementParser.Operations.SUM));
        System.out.println(complexFractionStatement.getOriginalOrderList().get(2).equals(fraction1));
        System.out.println(complexFractionStatement.getOriginalOrderList().get(3).equals(FractionStatementParser.Operations.DIF));
        System.out.println(complexFractionStatement.getOriginalOrderList().get(4).equals(fraction2));
    }
    @Test
    public void testIncorrectComplexStatement(){
        String input1 = "abc";
        Assert.assertFalse(input1.matches(FractionStatementParser.COMPLEX_FRACTION_STATEMENT));
        String input2 = "1/2 abc 1/4";
        Assert.assertFalse(input2.matches(FractionStatementParser.COMPLEX_FRACTION_STATEMENT));
        String input3 = "1/2 + abc 1/4";
        Assert.assertFalse(input3.matches(FractionStatementParser.COMPLEX_FRACTION_STATEMENT));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectOrderElement() {
        String input = "1/3 2/3 *";
        FractionStatementParser.parseInputAndReturnComplexStatement(input);
    }
    @Test(expected = ArithmeticException.class)
    public void testCheckNaNole() {
        String input = "1/2 + 2/0";
        FractionStatementParser.parseInputAndReturnComplexStatement(input);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNaCorrectStringFraction() {
        String input = "1\2";
        FractionStatementParser.getFractionFromString(input);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNaCorrectComplexFraction() {
        String input = "1\2 + 2/5";
        FractionStatementParser.parseInputAndReturnComplexStatement(input);
    }
}