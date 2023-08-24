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
import de.jo.aurora.parser.nodes.impl.statements.loops.NodeForLoop;
import de.jo.aurora.parser.nodes.impl.statements.loops.NodeWhileLoop;

import java.util.ArrayList;

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
        scope.addFunction(new Function(node.identifier().symbol(), node.body(), node.parameters(), Function.FunctionType.CODE) {
            @Override
            public Object call(ArrayList<Object> args) {
                //NO ADDED CALL CODE, ONLY FOR NATIVE FUNCTIONS
                return null;
            }
        });
        return node.identifier().symbol();
    }

    public static Object evalReturnStatement(NodeReturn node, Scope scope) {
        return eval(node.value(), scope);
    }

    public static Object evalLogicStatement(NodeLogicStatement node, Scope scope) {
        if (isEvalStatement(node.ifStatement().condition(), scope)) {
            return evalIfStatement(node.ifStatement(), scope);
        } else {
            for (NodeIfStatement ifStatement : node.elseIfStatements()) {
                if (isEvalStatement(ifStatement.condition(), scope)) {
                    return evalIfStatement(ifStatement, scope);
                }
            }
        }
        return evalElseStatement(node.elseStatement(), scope);
    }

    public static boolean isEvalStatement(Node node, Scope scope) {
        Object condition = eval(node, scope);
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

    public static Object evalWhileStatement(NodeWhileLoop whileLoop, Scope scope) {
        while((eval(whileLoop.condition(), scope) instanceof Boolean) && ((boolean) eval(whileLoop.condition(), scope))) {
            Scope bodyScope = new Scope(scope);
            for (Node node : whileLoop.body()) {
                Object obj = eval(node, bodyScope);
                if(node.type() == NodeType.RETURN) return new ReturnValue(obj);
                if(obj instanceof ReturnValue) return new ReturnValue(((ReturnValue) obj).returnValue);
            }
        }
        return null;
    }

    public static Object evalForStatement(NodeForLoop forLoop, Scope scope) {
        Scope argEnv = new Scope(scope);
        eval(forLoop.variableDeclaration(), argEnv);

        while(isEvalStatement(forLoop.condition(), argEnv)) {
            Scope bodyScope = new Scope(argEnv);
            for (Node node : forLoop.body()) {
                Object obj = eval(node, bodyScope);
                if(node.type() == NodeType.RETURN) return new ReturnValue(obj);
                if(obj instanceof ReturnValue) return new ReturnValue(((ReturnValue) obj).returnValue);
            }
            eval(forLoop.variableAssignment(), argEnv);
        }
        return null;
    }

}
