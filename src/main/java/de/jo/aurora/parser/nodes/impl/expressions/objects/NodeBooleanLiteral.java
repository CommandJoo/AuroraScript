package de.jo.aurora.parser.nodes.impl.expressions.objects;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author CommandJoo 31.05.2023
 * @Project AuroraScript
 */
public class NodeBooleanLiteral extends NodeExpression {

    private final Boolean value;

    public NodeBooleanLiteral(Boolean value) {
        super(NodeType.BOOLEAN_LITERAL);
        this.value = value;
    }

    public Boolean value() {
        return value;
    }
}
