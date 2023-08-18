package de.jo.aurora.interpreter.evaluators;

import de.jo.util.Error;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class EvalComparisons {


    public static Object evalNumericBinaryComparison(Number left, Number right, String operator) {
        boolean result = false;
        switch (operator) {
            case "==":
                result = (left.floatValue() == right.floatValue());
                break;
            case "!=":
                result = (left.floatValue() != right.floatValue());
                break;
            case ">":
                result = (left.floatValue() > right.floatValue());
                break;
            case ">=":
                result = (left.floatValue() >= right.floatValue());
                break;
            case "<":
                result = (left.floatValue() < right.floatValue());
                break;
            case "<=":
                result = (left.floatValue() <= right.floatValue());
                break;
            default:
                Error.call("Invalid operator for numeric binary comparison", new IllegalArgumentException());
                break;
        }
        return result;
    }
    public static Object evalBinaryComparison(Object left, Object right, String operator) {
        boolean result = false;
        switch (operator) {
            case "==":
                return left.equals(right);
            case "!=":
                return !left.equals(right);
            default:
                Error.call("Invalid operator for binary comparison", new IllegalArgumentException("Operator of type: "+operator));
                break;
        }
        return result;
    }

}
