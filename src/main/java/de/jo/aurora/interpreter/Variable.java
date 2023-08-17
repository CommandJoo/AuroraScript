package de.jo.aurora.interpreter;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class Variable {

    private final boolean constant;
    private final String identifier;
    private final Object value;

    public Variable(boolean constant, String identifier, Object value) {
        this.constant = constant;
        this.identifier = identifier;
        this.value = value;
    }

    public Variable(String identifier, Object value) {
        this(false, identifier, value);
    }

    public boolean constant() {
        return constant;
    }

    public String identifier() {
        return identifier;
    }

    public Object value() {
        return value;
    }
}
