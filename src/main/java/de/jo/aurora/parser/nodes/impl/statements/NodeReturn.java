package de.jo.aurora.parser.nodes.impl.statements;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

/**
 * @author CommandJoo 18.08.2023
 * @Project AuroraScript
 */
public class NodeReturn extends Node {

    private final NodeExpression value;

    public NodeReturn(NodeExpression value) {
        super(NodeType.RETURN);
        this.value = value;
    }

    public NodeExpression value() {
        return value;
    }
}
