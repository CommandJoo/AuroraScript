package de.jo.aurora.parser.nodes.impl.statements;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeType;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;

import java.util.ArrayList;

/**
 * @author Johannes Hans 18.08.2023
 * @Project AuroraScript
 */
public class NodeFunctionDeclaration extends Node {

    private final NodeIdentifier identifier;
    private final ArrayList<Node> body;
    private final ArrayList<NodeIdentifier> parameters;

    public NodeFunctionDeclaration(NodeIdentifier identifier, ArrayList<Node> body, ArrayList<NodeIdentifier> parameters) {
        super(NodeType.FUNCTION_DECLARATION);
        this.identifier = identifier;
        this.body = body;
        this.parameters = parameters;
    }

    public ArrayList<NodeIdentifier> parameters() {
        return parameters;
    }

    public ArrayList<Node> body() {
        return body;
    }

    public NodeIdentifier identifier() {
        return identifier;
    }
}
