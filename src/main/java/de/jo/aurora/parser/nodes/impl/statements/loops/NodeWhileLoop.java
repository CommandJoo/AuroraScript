package de.jo.aurora.parser.nodes.impl.statements.loops;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

import java.util.ArrayList;

/**
 * @author Johannes Hans 24.08.2023
 * @Project AuroraScript
 */
public class NodeWhileLoop extends Node {

    private final NodeExpression condition;
    private final ArrayList<Node> body;

    public NodeWhileLoop(NodeExpression condition, ArrayList<Node> body) {
        super(NodeType.WHILE);
        this.condition = condition;
        this.body = body;
    }

    public NodeExpression condition() {
        return condition;
    }

    public ArrayList<Node> body() {
        return body;
    }
}
