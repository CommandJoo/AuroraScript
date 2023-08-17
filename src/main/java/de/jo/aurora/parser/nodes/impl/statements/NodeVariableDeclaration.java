package de.jo.aurora.parser.nodes.impl.statements;

import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.NodeType;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class NodeVariableDeclaration extends Node {

    private final boolean constant;
    private final NodeIdentifier identifier;
    private final NodeExpression value;

    public NodeVariableDeclaration(boolean constant, NodeIdentifier identifier, NodeExpression value) {
        super(NodeType.VARIABLE_DECLARATION);
        this.constant = constant;
        this.identifier = identifier;
        this.value = value;
    }

    public boolean constant() {
        return constant;
    }

    public NodeIdentifier identifier() {
        return identifier;
    }

    public NodeExpression value() {
        return value;
    }
}
