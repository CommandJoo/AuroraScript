package de.jo.aurora.lexer.tokens;

import de.jo.util.StringUtil;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class TokenPosition {

    private int line, pos;

    public TokenPosition(int line, int pos) {
        this.line = line;
        this.pos = pos;
    }

    public TokenPosition(int line) {
        this(line, -1);
    }

    public int line() {
        return line;
    }

    public int pos() {
        return pos;
    }

    public TokenPosition setLine(int line) {
        this.line = line;
        return this;
    }

    public TokenPosition setPos(int pos) {
        this.pos = pos;
        return this;
    }

    @Override
    public String toString() {
        return StringUtil.jsonify(this);
    }
}
