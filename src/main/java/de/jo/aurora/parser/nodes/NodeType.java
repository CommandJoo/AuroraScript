package de.jo.aurora.parser.nodes;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public enum NodeType {

    PROGRAM,

    //EXPRESSION
    IDENTIFIER, NUMERIC_LITERAL, BOOLEAN_LITERAL, STRING_LITERAL, CHAR_LITERAL,

    BINARY_EXPRESSION, UNARY_EXPRESSION,

    //STATEMENT
    VARIABLE_DECLARATION,

}
