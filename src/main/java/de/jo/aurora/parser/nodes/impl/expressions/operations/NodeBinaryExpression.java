package de.jo.aurora.parser.nodes.impl.expressions.operations;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class NodeBinaryExpression extends NodeExpression {

    private final NodeExpression left;
    private final NodeExpression right;
    private final String operator;

    public NodeBinaryExpression(NodeExpression left, String operator, NodeExpression right) {
        super(NodeType.BINARY_EXPRESSION);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public NodeExpression left() {
        return left;
    }

    public NodeExpression right() {
        return right;
    }

    public String operator() {
        return operator;
    }

}
