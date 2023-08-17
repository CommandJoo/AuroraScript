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
        Scope scope = new Scope(null);

        //register global variables

        return scope;
    }

    public int level() {
        //if the parent is null we are on global level
        if(parent == null) return -1;
        return parent.level()+1;
    }

}
