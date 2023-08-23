package de.jo.aurora.lexer.tokens;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public abstract class Token {

    private final String value;
    private final TokenPosition position;

    public Token(String value, TokenPosition position) {
        this.value = value;
        this.position = position;
    }

    public TokenPosition position() {
        return position;
    }

    public String value() {
        return value;
    }

    public abstract TokenType type();

    public abstract boolean match(String val);

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"[type: '"+type()+"', value: '"+value()+"']";
    }
}
