package de.jo.aurora.interpreter;

import de.jo.aurora.interpreter.runtime.Function;
import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.impl.*;
import de.jo.aurora.parser.nodes.impl.expressions.*;
import de.jo.aurora.parser.nodes.impl.expressions.operations.*;
import de.jo.aurora.parser.nodes.impl.expressions.objects.*;
import de.jo.aurora.parser.nodes.impl.statements.*;
import de.jo.aurora.parser.nodes.impl.statements.logic.NodeLogicStatement;
import de.jo.aurora.parser.nodes.impl.statements.loops.NodeForLoop;
import de.jo.aurora.parser.nodes.impl.statements.loops.NodeWhileLoop;
import de.jo.util.Error;

import java.util.ArrayList;

import static de.jo.aurora.interpreter.evaluators.EvalExpressions.*;
import static de.jo.aurora.interpreter.evaluators.EvalStatements.*;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class Interpreter {

    private final NodeProgram program;
    private final ArrayList<String> arguments;

    public Interpreter(NodeProgram program, ArrayList<String> arguments) {
        this.program = program;
        this.arguments = arguments;
    }

    public Object interpret(Scope global) {
        //Create an environment to be worked in
        Scope env = new Scope(global);
        Object obj = eval(program, env);

        //--> run main function
        Function main = env.findFunction("main");
        if(main == null) Error.call("Invalid aurora program", new NullPointerException("Could not find main function!"));

        return evalFunctionCallExp(new NodeFunctionCall(new NodeIdentifier("main"), new ArrayList<>()), env);
    }


    public static Object eval(Node node, Scope env) {
        switch (node.type()) {
            //LITERALS
            case BOOLEAN_LITERAL:
                return ((NodeBooleanLiteral) node).value();
            case CHAR_LITERAL:
                return ((NodeCharLiteral) node).value();
            case STRING_LITERAL:
                return ((NodeStringLiteral) node).value();
            case NUMERIC_LITERAL:
                return ((NodeNumericLiteral) node).number();
            case IDENTIFIER:
                return env.findVariable(((NodeIdentifier)node).symbol()).value();
            case NULL_LITERAL:
                return null;
            //EXPRESSIONS
            case UNARY_EXPRESSION:
                return evalUnExp((NodeUnaryExpression)node, env);
            case BINARY_EXPRESSION:
                return evalBinExp((NodeBinaryExpression)node, env);
            case BINARY_COMPARISON:
                return evalBinCompExp((NodeBinaryComparisonExpression)node, env);
            case TERNARY_EXPRESSION:
                return evalTernExp((NodeTernaryExpression)node, env);

            case FUNCTION_CALL:
                return evalFunctionCallExp((NodeFunctionCall) node, env);
            case VARIABLE_ASSIGNMENT:
                return evalVariableAssignmentExp((NodeVariableAssignment)node, env);

                //STATEMENTS
            case PROGRAM:
                return evalProgram((NodeProgram) node, env);
            case VARIABLE_DECLARATION:
                String variableDeclaration = evalVariableDeclaration((NodeVariableDeclaration) node, env);
                return "DECLARED VARIABLE: "+variableDeclaration;
            case FUNCTION_DECLARATION:
                String functionDeclaration = evalFunctionDeclaration((NodeFunctionDeclaration) node, env);
                return "DECLARED FUNCTION: "+functionDeclaration;
            case RETURN:
                return evalReturnStatement((NodeReturn) node, env);
            case LOGIC:
                return evalLogicStatement((NodeLogicStatement)node, env);
            case WHILE:
                return evalWhileStatement((NodeWhileLoop) node, env);
            case FOR:
                return evalForStatement((NodeForLoop)node, env);
            default:
                System.out.println("Unparsed Node of type: " + node.type());
                System.exit(0);
                break;
        }
        return null;
    }

}
