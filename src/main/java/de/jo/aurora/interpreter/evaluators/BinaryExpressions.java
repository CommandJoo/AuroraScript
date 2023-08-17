package de.jo.aurora.interpreter.evaluators;

import de.jo.util.Error;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class BinaryExpressions {

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
                Error.call("Invalid operator for numeric binary expression", new IllegalArgumentException());
                break;
        }
        //makes the result an integer if there are no decimal places
        if (result % 1 == 0) return Math.round(result);
        else return result;
    }

    public static Object evalAlphabeticBinaryExpression(Object left, Object right, String operator) {
        String result = "";
        String l = left.toString();
        String r = right.toString();

        switch (operator) {
            case "+":
                result = l+r;
                break;
            case "-":
                if(l.endsWith(r)) {
                    result = l.substring(0, l.length()-r.length());
                }
                else {
                    result = l;
                    Error.softCall("Right side of alphabetic binary expression can not be subtracted from left side", new IllegalStateException("Expected: "+l.substring(l.length()-r.length())+" but found: "+r));
                }
                break;
            default:
                Error.call("Invalid operator for alphabetic binary expression", new IllegalArgumentException("Operator of type: "+operator));
                break;
        }
        return result;
    }

    public static Boolean evalBooleanBinaryExpression(Boolean left, Boolean right, String operator) {
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
