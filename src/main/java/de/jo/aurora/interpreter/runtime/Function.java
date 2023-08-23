package de.jo.aurora.interpreter.runtime;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;

import java.util.ArrayList;

/**
 * @author Johannes Hans 18.08.2023
 * @Project AuroraScript
 */
public class Function {

    private final String identifier;
    private final ArrayList<Node> body;
    private final ArrayList<NodeIdentifier> parameters;

    public Function(String identifier, ArrayList<Node> body, ArrayList<NodeIdentifier> parameters) {
        this.identifier = identifier;
        this.body = body;
        this.parameters = parameters;
    }

    public String identifier() {
        return identifier;
    }

    public ArrayList<Node> body() {
        return body;
    }

    public ArrayList<NodeIdentifier> parameters() {
        return parameters;
    }
}
