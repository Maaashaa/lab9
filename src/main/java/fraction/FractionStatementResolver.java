package fraction;
import java.util.List;
import java.util.Stack;

public class FractionStatementResolver {
    // Work for statement without "(" and ")"
    public static Fraction resolveComplexFractionStatement(FractionStatementParser.ComplexFractionStatement complexFractionStatement) {
        Stack<Fraction> fractionStack = new Stack<>();
        Stack<FractionStatementParser.Operations> operationsStack = new Stack<>();

        List<Object> originalOrderList = complexFractionStatement.getOriginalOrderList();
        for (Object element : originalOrderList) {
            if (element instanceof Fraction) {
                fractionStack.push((Fraction) element);
            } else if (element instanceof FractionStatementParser.Operations) {
                FractionStatementParser.Operations operation = (FractionStatementParser.Operations) element;
                if (operationsStack.size() == 0) {
                    operationsStack.push(operation);
                } else if (operationsStack.peek().getPriority() <= operation.getPriority()) {
                    operationsStack.push(operation);
                } else if (operationsStack.peek().getPriority() > operation.getPriority()){
                    do {
                        performOperationFromStacks(fractionStack, operationsStack);
                    } while (operationsStack.size() != 0
                            && fractionStack.size() != 0
                            && operationsStack.peek().getPriority() <= operation.getPriority());
                    operationsStack.push(operation);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (fractionStack.size() != 1 && operationsStack.size() != 0) {
            do {
                performOperationFromStacks(fractionStack, operationsStack);
            } while(operationsStack.size() != 0 && fractionStack.size() != 0);
        }

        return fractionStack.pop();
    }

    public static Fraction performOperationOnFractions(Fraction fraction1, Fraction fraction2, FractionStatementParser.Operations operation) {
        switch (operation) {
            case SUM:
                return Fraction.sum(fraction1, fraction2);
            case DIF:
                return Fraction.dif(fraction1, fraction2);
            case MULTIPLICATION:
                return Fraction.multiplic(fraction1, fraction2);
            case DIVISION:
                return Fraction.div(fraction1, fraction2);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void performOperationFromStacks(Stack<Fraction> fractionStack, Stack<FractionStatementParser.Operations> operationsStack) {
        Fraction fraction1 = fractionStack.pop();
        Fraction fraction2 = fractionStack.pop();
        FractionStatementParser.Operations operationFromStack = operationsStack.pop();

        // the fraction order is important!
        Fraction resultFraction = performOperationOnFractions(fraction2, fraction1, operationFromStack);
        fractionStack.push(resultFraction);
    }

    private FractionStatementResolver() {}
}