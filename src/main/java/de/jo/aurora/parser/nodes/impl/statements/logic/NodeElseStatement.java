package de.jo.aurora.parser.nodes.impl.statements.logic;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeType;

import java.util.ArrayList;

/**
 * @author Johannes Hans 23.08.2023
 * @Project AuroraScript
 */
public class NodeElseStatement extends Node {

    private final ArrayList<Node> body;

    public NodeElseStatement(ArrayList<Node> body) {
        super(NodeType.ELSE);
        this.body = body;
    }

    public ArrayList<Node> body() {
        return body;
    }
}
