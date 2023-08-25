package de.jo.aurora.interpreter.evaluators;

import de.jo.aurora.interpreter.Scope;
import de.jo.aurora.interpreter.runtime.*;

import de.jo.aurora.parser.nodes.*;

import de.jo.aurora.parser.nodes.impl.expressions.*;
import de.jo.aurora.parser.nodes.impl.expressions.operations.*;
import de.jo.util.Error;
import de.jo.util.Reflections;

import java.util.ArrayList;

import static de.jo.aurora.interpreter.Interpreter.*;
import static de.jo.aurora.interpreter.evaluators.EvalComparisons.*;
import static de.jo.aurora.interpreter.evaluators.EvalOperations.*;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class EvalExpressions {

    public static Object evalUnExp(NodeUnaryExpression unexp, Scope env) {
        Object obj = eval(unexp.right(), env);
        if(obj instanceof Number) {
            System.out.println(obj);
           return  evalNumericUnaryExpression((Number) obj, unexp.operator());
        }
        if(obj instanceof Boolean) {
            return evalBooleanUnaryExpression((Boolean) obj, unexp.operator());
        }
        return 0;
    }
    public static Object evalBinExp(NodeBinaryExpression binexp, Scope env) {
        Object left = eval(binexp.left(), env);
        Object right = eval(binexp.right(), env);

        if (left instanceof Number && right instanceof Number) {
            return evalNumericBinaryExpression((Number) left, (Number) right, binexp.operator());
        }
        if (left instanceof Boolean && right instanceof Boolean) {
            return evalBooleanBinaryExpression((Boolean) left, (Boolean) right, binexp.operator());
        }
        else {
            return evalAlphabeticBinaryExpression(left.toString(), right.toString(), binexp.operator());
        }
    }
    public static Object evalTernExp(NodeTernaryExpression ternexp, Scope env) {
        Object condition = eval(ternexp.condition(), env);
        Reflections.type(Boolean.class, condition, "Condition in ternary operation must be of type Boolean!", new IllegalArgumentException("Expected Boolean but found: "+condition.getClass().getTypeName()));
        Boolean b = (Boolean) condition;
        if(b) {
            return eval(ternexp.left(), env);
        }else {
            return eval(ternexp.right(), env);
        }
    }

    public static Object evalBinCompExp(NodeBinaryComparisonExpression bincompexp, Scope env) {
        Object left = eval(bincompexp.left(), env);
        Object right = eval(bincompexp.right(), env);

        if(left instanceof Number && right instanceof Number) {
            return evalNumericBinaryComparison((Number) left, (Number) right, bincompexp.operator());
        }else {
            return evalBinaryComparison(left, right, bincompexp.operator());
        }
    }

    public static Object evalFunctionCallExp(NodeFunctionCall funcall, Scope env) {
        //result may be null for function without return statement
        Function func = env.findFunction(funcall.name().symbol());
        Scope functionScope = new Scope(env);

        if(func.type() == Function.FunctionType.NATIVE) {
            ArrayList<Object> params = new ArrayList<>();
            for(NodeExpression exp : funcall.arguments()) {
                params.add(eval(exp, env));
            }
            return func.call(params);
        }

        if(func.parameters().size() != funcall.arguments().size())
            Error.call("Invalid amount of arguments for function: "+func.identifier(), new IllegalArgumentException("Expected "+func.parameters().size() +" but found " +funcall.arguments().size()));
        for(int i = 0; i < func.parameters().size(); i++) {
            final String ident = func.parameters().get(i).symbol();
            final Object value = eval(funcall.arguments().get(i), env);
            final boolean constant = false;

            functionScope.addVariable(new Variable(constant, ident, value));
        }

        for(Node node : func.body()) {
            if(node.type() != NodeType.RETURN) {
                Object obj = eval(node, functionScope);
                if(obj instanceof ReturnValue) return ((ReturnValue) obj).returnValue;
            }
            else {
                return eval(node, functionScope);
            }
        }
        return null;
    }

    public static Object evalVariableAssignmentExp(NodeVariableAssignment varass, Scope env) {
        String name = varass.identifier().symbol();
        Variable var = env.findVariable(name);
        Object result = eval(varass.value(), env);
        var.setValue(result);
        return result;
    }

}
