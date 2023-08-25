package de.jo.aurora.interpreter;

import de.jo.aurora.interpreter.runtime.*;
import de.jo.aurora.parser.nodes.*;
import de.jo.aurora.parser.nodes.impl.expressions.objects.NodeIdentifier;
import de.jo.util.Reflections;
import de.jo.util.StringUtil;

import java.util.ArrayList;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class Scope {

    private final Scope parent;
    private final ArrayList<Variable> variables;
    private final ArrayList<Function> functions;

    public Scope(Scope parent) {
        this.parent = parent;
        this.variables = new ArrayList<>();
        this.functions = new ArrayList<>();
    }

    public static Scope globalScope() {
        Scope global = new Scope(null);
        //register global variables
        global.addFunction(new Function("print", new ArrayList<>(), new ArrayList<>(), Function.FunctionType.NATIVE) {
            @Override
            public Object call(ArrayList<Object> args) {
                for(Object obj : args) {
                    System.out.println(obj.toString());
                }
                return args.toString();
            }
        });
        return global;
    }

    public int level() {
        //if the parent is null we are on global level
        if(parent == null) return -1;
        return parent.level()+1;
    }

    public void addVariable(Variable variable) {
        this.variables.add(variable);
    }
    public void addFunction(Function function) {this.functions.add(function);}

    public Variable findVariable(String identifier) {
        Variable result = null;
        for(Variable var : this.variables) {
            if(var.identifier().equals(identifier)) result = var;
        }
        if(result == null && this.parent != null) result = this.parent.findVariable(identifier);
        return result;
    }
    public Function findFunction(String identifier) {
        Function result = null;
        for(Function fun : this.functions) {
            if(fun.identifier().equals(identifier)) result = fun;
        }
        if(result == null && this.parent != null) result = this.parent.findFunction(identifier);
        return result;
    }

    @Override
    public String toString() {
        return StringUtil.jsonify(this);
    }
}
