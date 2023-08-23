package de.jo.aurora.parser.nodes.impl.statements.logic;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeType;

import java.util.ArrayList;

/**
 * @author Johannes Hans 23.08.2023
 * @Project AuroraScript
 */
public class NodeLogicStatement extends Node {

    private final NodeIfStatement ifStatement;
    private final ArrayList<NodeIfStatement> elseIfStatements;
    private final NodeElseStatement elseStatement;

    public NodeLogicStatement(NodeIfStatement ifStatement, ArrayList<NodeIfStatement> elseIfStatements, NodeElseStatement elseStatement) {
        super(NodeType.LOGIC);
        this.ifStatement = ifStatement;
        this.elseIfStatements = elseIfStatements;
        this.elseStatement = elseStatement;
    }

    public NodeIfStatement ifStatement() {
        return ifStatement;
    }

    public NodeElseStatement elseStatement() {
        return elseStatement;
    }

    public ArrayList<NodeIfStatement> elseIfStatements() {
        return elseIfStatements;
    }
}

