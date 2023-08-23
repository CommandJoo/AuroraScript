package de.jo.aurora.parser.nodes.impl.statements.logic;

import de.jo.aurora.parser.nodes.*;

import java.util.ArrayList;

/**
 * @author Johannes Hans 23.08.2023
 * @Project AuroraScript
 */
public class NodeIfStatement extends Node {

    public final NodeExpression condition;
    public final ArrayList<Node> body;


    public NodeIfStatement(NodeExpression condition, ArrayList<Node> body) {
        super(NodeType.IF);
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
