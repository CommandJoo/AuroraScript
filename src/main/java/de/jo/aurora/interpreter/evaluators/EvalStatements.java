package de.jo.aurora.interpreter.evaluators;

import de.jo.aurora.interpreter.Scope;
import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.impl.NodeProgram;

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

}
