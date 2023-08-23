package de.jo.aurora.parser.nodes.impl.expressions.operations;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class NodeUnaryExpression extends NodeExpression {

    private final NodeExpression right;
    private final String operator;

    public NodeUnaryExpression(String operator, NodeExpression right) {
        super(NodeType.UNARY_EXPRESSION);
        this.right = right;
        this.operator = operator;
    }

    public NodeExpression right() {
        return right;
    }

    public String operator() {
        return operator;
    }

}
