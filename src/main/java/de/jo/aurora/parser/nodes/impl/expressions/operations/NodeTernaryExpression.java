package de.jo.aurora.parser.nodes.impl.expressions.operations;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class NodeTernaryExpression extends NodeExpression {

    private final NodeExpression condition;
    private final NodeExpression left;
    private final NodeExpression right;

    public NodeTernaryExpression(NodeExpression condition, NodeExpression left, NodeExpression right) {
        super(NodeType.TERNARY_EXPRESSION);
        this.condition = condition;
        this.left = left;
        this.right = right;
    }

    public NodeExpression left() {
        return left;
    }

    public NodeExpression right() {
        return right;
    }

    public NodeExpression condition() {
        return condition;
    }

}
