package de.jo.aurora.parser.nodes;

import de.jo.util.StringUtil;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public abstract class Node {

    private final NodeType type;

    protected Node(NodeType type) {
        this.type = type;
    }

    public NodeType type() {return this.type;}

    @Override
    public String toString() {
        return StringUtil.jsonify(this);
    }
}
