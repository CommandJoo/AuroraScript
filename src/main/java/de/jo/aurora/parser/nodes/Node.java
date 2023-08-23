package de.jo.aurora.parser.nodes;

import de.jo.util.StringUtil;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public abstract class Node {

    private final NodeType type;

    public Node(NodeType type) {
        this.type = type;
    }

    public NodeType type() {return this.type;}

    @Override
    public String toString() {
        return StringUtil.jsonify(this);
    }
}
