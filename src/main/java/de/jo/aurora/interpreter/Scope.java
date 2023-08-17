package de.jo.aurora.interpreter;

import java.util.ArrayList;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class Scope {

    private final Scope parent;
    private final ArrayList<Variable> variables;

    public Scope(Scope parent) {
        this.parent = parent;
        this.variables = new ArrayList<>();
    }

    public static Scope globalScope() {

        //register global variables

        return new Scope(null);
    }

    public int level() {
        //if the parent is null we are on global level
        if(parent == null) return -1;
        return parent.level()+1;
    }

    public void add(Variable variable) {
        this.variables.add(variable);
    }

    public Variable find(String identifier) {
        Variable result = null;
        for(Variable var : this.variables) {
            if(var.identifier().equals(identifier)) result = var;
        }
        if(result == null && this.parent != null) result = this.parent.find(identifier);
        return result;
    }

}
