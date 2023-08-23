package de.jo.aurora.parser.nodes.impl.expressions;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;

import java.util.ArrayList;

/**
 * @author Johannes Hans 18.08.2023
 * @Project AuroraScript
 */
public class NodeFunctionCall extends NodeExpression {

    private final NodeIdentifier name;
    private final ArrayList<NodeExpression> arguments;

    public NodeFunctionCall(NodeIdentifier name, ArrayList<NodeExpression> arguments) {
        super(NodeType.FUNCTION_CALL);
        this.name = name;
        this.arguments = arguments;
    }

    public NodeIdentifier name() {
        return name;
    }

    public ArrayList<NodeExpression> arguments() {
        return arguments;
    }
}
