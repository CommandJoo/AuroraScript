package de.jo.aurora.parser.nodes.impl.expressions.objects;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author CommandJoo 31.05.2023
 * @Project AuroraScript
 */
public class NodeStringLiteral extends NodeExpression {

    private final String value;

    public NodeStringLiteral(String value) {
        super(NodeType.STRING_LITERAL);
        this.value = value;
    }

    public String value() {
        return value;
    }
}
