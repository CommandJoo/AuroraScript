package de.jo.aurora.interpreter.runtime;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class Variable {

    private final boolean constant;
    private final String identifier;
    private Object value;

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

    public Variable setValue(Object value) {
        this.value = value;
        return this;
    }
}
