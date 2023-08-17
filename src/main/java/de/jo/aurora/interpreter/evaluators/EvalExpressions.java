package de.jo.aurora.interpreter.evaluators;

import de.jo.aurora.interpreter.Scope;
import de.jo.aurora.parser.nodes.impl.expressions.NodeBinaryExpression;

import static de.jo.aurora.interpreter.Interpreter.eval;
import static de.jo.aurora.interpreter.evaluators.BinaryExpressions.*;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class EvalExpressions {

    public static Object evalBinExp(NodeBinaryExpression binexp, Scope env) {
        Object left = eval(binexp.left(), env);
        Object right = eval(binexp.right(), env);

        if(left instanceof Number && right instanceof Number) {
            return evalNumericBinaryExpression((Number) left, (Number) right, binexp.operator());
        }

        return null;
    }

}
