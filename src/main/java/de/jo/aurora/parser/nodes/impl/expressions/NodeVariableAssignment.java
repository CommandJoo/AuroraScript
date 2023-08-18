package de.jo.aurora.parser.nodes.impl.expressions;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;

/**
 * @author CommandJoo 18.08.2023
 * @Project AuroraScript
 */
public class NodeVariableAssignment extends NodeExpression {

    private final NodeIdentifier identifier;
    private final NodeExpression value;

    public NodeVariableAssignment(NodeIdentifier identifier, NodeExpression value) {
        super(NodeType.VARIABLE_ASSIGNMENT);
        this.identifier = identifier;
        this.value = value;
    }

    public NodeIdentifier identifier() {
        return identifier;
    }

    public NodeExpression value() {
        return value;
    }
}
