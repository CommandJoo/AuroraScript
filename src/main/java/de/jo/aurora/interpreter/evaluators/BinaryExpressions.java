package de.jo.aurora.interpreter.evaluators;

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
                result = 0;
                break;
        }
        //makes the result an integer if there are no decimal places
        if (result % 1 == 0) return Math.round(result);
        else return result;
    }

}
