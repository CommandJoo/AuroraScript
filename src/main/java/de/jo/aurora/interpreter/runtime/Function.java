package de.jo.aurora.interpreter.runtime;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;

import java.util.ArrayList;

/**
 * @author Johannes Hans 18.08.2023
 * @Project AuroraScript
 */
public abstract class Function {

    private final String identifier;
    private final ArrayList<Node> body;
    private final ArrayList<NodeIdentifier> parameters;
    private final FunctionType type;

    public Function(String identifier, ArrayList<Node> body, ArrayList<NodeIdentifier> parameters, FunctionType type) {
        this.identifier = identifier;
        this.body = body;
        this.parameters = parameters;
        this.type = type;
    }

    public String identifier() {
        return identifier;
    }

    public ArrayList<Node> body() {
        return body;
    }

    public abstract Object call(ArrayList<Object> args);

    public ArrayList<NodeIdentifier> parameters() {
        return parameters;
    }

    public FunctionType type() {
        return type;
    }

    public enum FunctionType {
        CODE, NATIVE
    }

}
