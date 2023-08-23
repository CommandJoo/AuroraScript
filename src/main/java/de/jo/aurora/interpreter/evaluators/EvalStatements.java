package de.jo.aurora.interpreter.evaluators;

import de.jo.aurora.interpreter.Scope;
import de.jo.aurora.interpreter.runtime.Function;
import de.jo.aurora.interpreter.runtime.ReturnValue;
import de.jo.aurora.interpreter.runtime.Variable;
import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeType;
import de.jo.aurora.parser.nodes.impl.NodeProgram;
import de.jo.aurora.parser.nodes.impl.statements.NodeFunctionDeclaration;
import de.jo.aurora.parser.nodes.impl.statements.NodeReturn;
import de.jo.aurora.parser.nodes.impl.statements.NodeVariableDeclaration;
import de.jo.aurora.parser.nodes.impl.statements.logic.NodeElseStatement;
import de.jo.aurora.parser.nodes.impl.statements.logic.NodeIfStatement;
import de.jo.aurora.parser.nodes.impl.statements.logic.NodeLogicStatement;

import java.util.ArrayList;
import java.util.List;

import static de.jo.aurora.interpreter.Interpreter.eval;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class EvalStatements {

    public static Object evalProgram(NodeProgram program, Scope scope) {
        ArrayList<Object> values = new ArrayList<>();
        for(Node node : program.nodes()) {
            values.add(eval(node, scope));
        }
        return values;
    }

    public static String evalVariableDeclaration(NodeVariableDeclaration node, Scope scope) {
        scope.addVariable(new Variable(node.constant(), node.identifier().symbol(), eval(node.value(), scope)));
        return node.identifier().symbol();
    }

    public static String evalFunctionDeclaration(NodeFunctionDeclaration node, Scope scope) {
        scope.addFunction(new Function(node.identifier().symbol(), node.body(), node.parameters()));
        return node.identifier().symbol();
    }

    public static Object evalReturnStatement(NodeReturn node, Scope scope) {
        return eval(node.value(), scope);
    }

    public static Object evalLogicStatement(NodeLogicStatement node, Scope scope) {
        if (isEvalIfStatement(node.ifStatement(), scope)) {
            return evalIfStatement(node.ifStatement(), scope);
        } else {
            for (NodeIfStatement ifStatement : node.elseIfStatements()) {
                if (isEvalIfStatement(ifStatement, scope)) {
                    return evalIfStatement(ifStatement, scope);
                }
            }
        }
        return evalElseStatement(node.elseStatement(), scope);
    }

    public static boolean isEvalIfStatement(NodeIfStatement ifStatement, Scope scope) {
        Object condition = eval(ifStatement.condition(), scope);
        if (condition instanceof Boolean && ((Boolean) condition)) return true;
        return false;
    }

    public static Object evalIfStatement(NodeIfStatement ifStatement, Scope scope) {
        Scope bodyScope = new Scope(scope);
        for (Node node : ifStatement.body()) {
            Object obj = eval(node, bodyScope);
            if(node.type() == NodeType.RETURN) return new ReturnValue(obj);
            if(obj instanceof ReturnValue) return new ReturnValue(((ReturnValue) obj).returnValue);
        }
        return null;
    }

    public static Object evalElseStatement(NodeElseStatement elseStatement, Scope scope) {
        Scope bodyScope = new Scope(scope);
        for (Node node : elseStatement.body()) {
            Object obj = eval(node, bodyScope);
            if(node.type() == NodeType.RETURN) return new ReturnValue(obj);
            if(obj instanceof ReturnValue) return new ReturnValue(((ReturnValue) obj).returnValue);
        }
        return null;
    }

}
