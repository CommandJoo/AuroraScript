package de.jo.aurora.interpreter.evaluators;

import de.jo.aurora.interpreter.Scope;
import de.jo.aurora.interpreter.runtime.Function;
import de.jo.aurora.interpreter.runtime.Variable;
import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.impl.NodeProgram;
import de.jo.aurora.parser.nodes.impl.statements.NodeFunctionDeclaration;
import de.jo.aurora.parser.nodes.impl.statements.NodeReturn;
import de.jo.aurora.parser.nodes.impl.statements.NodeVariableDeclaration;

import java.util.ArrayList;
import static de.jo.aurora.interpreter.Interpreter.*;
/**
 * @author CommandJoo 17.08.2023
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

}
