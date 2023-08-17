package de.jo.aurora.interpreter;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.impl.NodeProgram;
import de.jo.aurora.parser.nodes.impl.expressions.NodeBinaryExpression;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeNumericLiteral;

import java.util.ArrayList;

import static de.jo.aurora.interpreter.evaluators.EvalExpressions.*;
import static de.jo.aurora.interpreter.evaluators.EvalStatements.*;

/**
 * @author CommandJoo 17.08.2023
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


        return obj;
    }


    public static Object eval(Node node, Scope env) {
        switch (node.type()) {
            //LITERALS
            case NUMERIC_LITERAL:
                NodeNumericLiteral num = ((NodeNumericLiteral) node);
                return num.number();

            //EXPRESSIONS
            case BINARY_EXPRESSION:
                return evalBinExp((NodeBinaryExpression)node, env);


                //STATEMENTS
            case PROGRAM:
                return evalProgram((NodeProgram) node, env);
            default:
                System.out.println("Unparsed Node of type: " + node.type());
                System.exit(0);
                break;
        }
        return null;
    }

}