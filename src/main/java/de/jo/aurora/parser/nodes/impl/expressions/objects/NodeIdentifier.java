package de.jo.aurora.parser.nodes.impl.expressions.objects;

import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class NodeIdentifier extends NodeExpression {

    private final String symbol;
    
    public NodeIdentifier(String symbol) {
        super(NodeType.IDENTIFIER);
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

}
