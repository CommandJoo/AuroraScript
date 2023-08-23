package de.jo.aurora.parser.nodes.impl.expressions.objects;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author Johannes Hans 31.05.2023
 * @Project AuroraScript
 */
public class NodeCharLiteral extends NodeExpression {

    private final Character value;

    public NodeCharLiteral(Character value) {
        super(NodeType.CHAR_LITERAL);
        this.value = value;
    }

    public Character value() {
        return value;
    }
}
