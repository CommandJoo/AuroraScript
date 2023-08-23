package de.jo.aurora.parser.nodes.impl.expressions.objects;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author Johannes Hans 23.08.2023
 * @Project AuroraScript
 */
public class NodeNullLiteral extends NodeExpression {
    public NodeNullLiteral() {
        super(NodeType.NULL_LITERAL);
    }
}
