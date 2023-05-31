package de.jo.aurora.parser.nodes.impl;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeType;

import java.util.List;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class NodeProgram extends Node{

    private List<Node> nodes;
    
    public NodeProgram(List<Node> nodes) {
        super(NodeType.PROGRAM);
        this.nodes = nodes;
    }

    public List<Node> nodes() {
        return nodes;
    }
}
