package de.jo.aurora.parser.nodes.impl.statements.loops;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;

import java.util.ArrayList;

/**
 * @author Johannes Hans 24.08.2023
 * @Project AuroraScript
 */
public class NodeForLoop extends Node {

    private final Node variableDeclaration;
    private final NodeExpression condition;
    private final NodeExpression variableAssignment;

    private final ArrayList<Node> body;

    public NodeForLoop(Node variableDeclaration, NodeExpression condition, NodeExpression variableAssignment, ArrayList<Node> body) {
        super(NodeType.FOR);
        this.variableDeclaration = variableDeclaration;
        this.condition = condition;
        this.variableAssignment = variableAssignment;
        this.body = body;
    }

    public Node variableDeclaration() {
        return variableDeclaration;
    }

    public NodeExpression condition() {
        return condition;
    }

    public NodeExpression variableAssignment() {
        return variableAssignment;
    }

    public ArrayList<Node> body() {
        return body;
    }
}
