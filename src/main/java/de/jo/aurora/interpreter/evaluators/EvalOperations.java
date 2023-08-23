package de.jo.aurora.interpreter.evaluators;

import de.jo.util.Error;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class EvalOperations {

    public static Object evalNumericUnaryExpression(Number right, String operator) {
        float result = 0;
        switch (operator) {
            case "+":
                result = right.floatValue()*1;
                break;
            case "-":
                result = right.floatValue()*-1;
                break;
            default:
                Error.call("Invalid operator for numeric unary expression", new IllegalArgumentException(operator));
                break;
        }
        if(result%1==0) return Math.round(result);
        return result;
    }
    public static Object evalBooleanUnaryExpression(Boolean right, String operator) {
        boolean result = false;
        switch (operator) {
            case "!":
                result = !right;
                break;
            default:
                Error.call("Invalid operator for boolean unary expression", new IllegalArgumentException(operator));
                break;
        }
        return (Boolean)result;
    }

    public static Object evalNumericBinaryExpression(Number left, Number right, String operator) {
        float result = 0;
        switch (operator) {
            case "*":
                result = (left.floatValue() * right.floatValue());
                break;
            case "/":
                result = (left.floatValue() / right.floatValue());
                break;
            case "^":
                result = (float) Math.pow(left.floatValue(), right.floatValue());
                break;
            case "%":
                result = (left.floatValue() % right.floatValue());
                break;
            case "+":
                result = (left.floatValue() + right.floatValue());
                break;
            case "-":
                result = (left.floatValue() - right.floatValue());
                break;
            default:
                Error.call("Invalid operator for numeric binary expression", new IllegalArgumentException(operator));
                break;
        }
        //makes the result an integer if there are no decimal places
        if (result % 1 == 0) return Math.round(result);
        return result;
    }
    public static Object evalAlphabeticBinaryExpression(String left, String right, String operator) {
        String result = "";

        switch (operator) {
            case "+":
                result = left + right;
                break;
            case "-":
                if(left.endsWith(right)) {
                    result = left.substring(0, left.length()- right.length());
                }
                else {
                    result = left;
                    Error.softCall("Right side of alphabetic binary expression can not be subtracted from left side", new IllegalStateException("Expected: "+ left.substring(left.length()- right.length())+" but found: "+ right));
                }
                break;
            default:
                Error.call("Invalid operator for alphabetic binary expression", new IllegalArgumentException("Operator of type: "+operator));
                break;
        }
        return result;
    }
    public static Object evalBooleanBinaryExpression(Boolean left, Boolean right, String operator) {
        boolean result = false;
        switch (operator) {
            case "&&":
                return left&&right;
            case "||":
                return left||right;
            default:
                Error.call("Invalid operator for boolean binary expression", new IllegalArgumentException("Operator of type: "+operator));
                break;
        }
        return result;
    }

}
