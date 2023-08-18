package de.jo.aurora.parser.nodes;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public enum NodeType {

    PROGRAM,

    //EXPRESSION
    IDENTIFIER, NUMERIC_LITERAL, BOOLEAN_LITERAL, STRING_LITERAL, CHAR_LITERAL,

    TERNARY_EXPRESSION, BINARY_EXPRESSION, UNARY_EXPRESSION,
    BINARY_COMPARISON,

    FUNCTION_CALL, VARIABLE_ASSIGNMENT,

    //STATEMENT
    VARIABLE_DECLARATION, FUNCTION_DECLARATION, RETURN

}
