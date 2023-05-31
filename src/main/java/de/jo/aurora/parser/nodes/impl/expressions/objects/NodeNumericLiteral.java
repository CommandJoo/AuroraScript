package de.jo.aurora.parser.nodes.impl.expressions.objects;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class NodeNumericLiteral extends NodeExpression {

    private final Number number;

    public NodeNumericLiteral(Number number) {
        super(NodeType.NUMERIC_LITERAL);
        this.number = number;
    }

    public Number number() {
        return number;
    }
}
