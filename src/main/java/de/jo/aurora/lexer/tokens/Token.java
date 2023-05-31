package de.jo.aurora.lexer.tokens;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public abstract class Token {

    private String value;
    
    public Token(String value) {
        this.value = value;
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
