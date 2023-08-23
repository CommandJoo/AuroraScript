package de.jo.aurora.parser;

import de.jo.aurora.lexer.tokens.*;
import de.jo.aurora.parser.nodes.*;
import de.jo.aurora.parser.nodes.impl.NodeProgram;
import de.jo.aurora.parser.nodes.impl.expressions.*;
import de.jo.aurora.parser.nodes.impl.expressions.objects.*;
import de.jo.aurora.parser.nodes.impl.expressions.operations.*;
import de.jo.aurora.parser.nodes.impl.statements.*;
import de.jo.util.Error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class Parser {

    private final TokenList tokens;
    private final List<Node> nodes;

    public Parser(TokenList tokens) {
        this.tokens = tokens;
        this.nodes = new ArrayList<>();
    }

    public void parse() {
        while (this.current().type() != TokenType.END_OF_FILE) {
            nodes.add(parseStatements(ParseType.NORMAL));
        }
    }

    private Node parseStatements(ParseType parseType) {
        switch (this.current().type()) {
            case LET:
            case CONST:
                return this.parseVariableDeclaration();
            case RETURN:
                if(parseType != ParseType.IN_FUNCTION) Error.call("Values can only be returned at level: "+ParseType.IN_FUNCTION+" but found "+parseType);
                return this.parseReturn();
            case FUNCTION:
                if (parseType != ParseType.NORMAL) Error.call("Function can not be declared at level: "+parseType +", only at level: "+ParseType.NORMAL);
                return this.parseFunctionDeclaration();
        }
        return parseExpressions(parseType);
    }

    /*
    #
    #
    #
    #
    #           STATEMENTS
    #
    #
    #
    #
    #
     */
    private NodeVariableDeclaration parseVariableDeclaration() {
        boolean constant = this.current().type() == TokenType.CONST;

        if (this.current().type() != TokenType.LET)
            Error.call("Invalid Token expected const or let but found: " + this.current().type());
        this.tokens.removeFirst();

        NodeIdentifier identifier = new NodeIdentifier(this.expect(TokenType.IDENTIFIER).value());
        this.expect(TokenType.EQUALS);
        NodeExpression value = this.parseExpressions(ParseType.IN_ARGUMENT);
        this.expect(TokenType.SEMICOLON);
        return new NodeVariableDeclaration(constant, identifier, value);
    }

    private NodeFunctionDeclaration parseFunctionDeclaration() {
        this.expect(TokenType.FUNCTION);
        NodeIdentifier identifier = new NodeIdentifier(this.expect(TokenType.IDENTIFIER).value());
        ArrayList<NodeIdentifier> parameters = this.parseParameters();
        ArrayList<Node> body = this.parseBody(ParseType.IN_FUNCTION);

        return new NodeFunctionDeclaration(identifier, body, parameters);
    }

    private NodeReturn parseReturn() {
        this.expect(TokenType.RETURN);
        NodeExpression exp = this.parseExpressions(ParseType.IN_ARGUMENT);
        this.expect(TokenType.SEMICOLON);
        return new NodeReturn(exp);
    }

    private ArrayList<NodeIdentifier> parseParameters() {
        this.expect(TokenType.PAREN_OPEN);
        ArrayList<NodeIdentifier> val = new ArrayList<>();

        //loops through like this (  a, b , c, d  )
        while (this.current().type() == TokenType.IDENTIFIER) {
            val.add(new NodeIdentifier(this.current().value()));
            this.expect(TokenType.IDENTIFIER);

            if (this.current().type() == TokenType.COMMA) this.tokens.removeFirst();
        }
        this.expect(TokenType.PAREN_CLOSE);
        return val;
    }

    private ArrayList<Node> parseBody(ParseType parseType) {
        this.expect(TokenType.BRACE_OPEN);
        ArrayList<Node> val = new ArrayList<>();

        //parses all statements within {} and adds them to val
        while (this.current().type() != TokenType.BRACE_CLOSE) {
            val.add(this.parseStatements(parseType));
        }

        this.expect(TokenType.BRACE_CLOSE);
        return val;
    }


    /*
    #
    #
    #
    #
    #           EXPRESSIONS
    #
    #
    #
    #
    #
    #
     */
























    private NodeExpression parseExpressions(ParseType parseType) {
        return parseVariableExpression(parseType);
    }

    private NodeExpression parseVariableExpression(ParseType parseType) {
        NodeExpression left = this.parseTernaryExpression(parseType);
        if(left.type() == NodeType.IDENTIFIER) {
            while(this.current().type() == TokenType.EQUALS) {
                this.expect(TokenType.EQUALS);
                NodeExpression value = this.parseVariableExpression(ParseType.IN_ARGUMENT);
                left = new NodeVariableAssignment((NodeIdentifier) left, value);
            }
        }
        return left;
    }

    private NodeExpression parseTernaryExpression(ParseType parseType) {
        NodeExpression condition = this.parseBoolean(parseType);
        while (this.current().type() == TokenType.TERNARY) {
            this.expect(TokenType.TERNARY);
            NodeExpression left = this.parseTernaryExpression(parseType);
            this.expect(TokenType.COLON);
            NodeExpression right = this.parseTernaryExpression(parseType);
            condition = new NodeTernaryExpression(condition, left, right);
        }
        return condition;
    }

    //parses boolean && boolean or boolean || boolean
    private NodeExpression parseBoolean(ParseType parseType) {
        NodeExpression left = parseComparative(parseType);
        while (this.current().type() == TokenType.OR_AND && this.tokens.get(1).type() == TokenType.OR_AND) {
            //builds an operator like &| or && or ||
            String operator = this.tokens.removeFirst().value() + this.tokens.removeFirst().value();
            NodeExpression right = this.parseComparative(parseType);
            left = new NodeBinaryExpression(left, operator, right);
        }
        return left;
    }

    //parses comparisons like == >= <= !=
    private NodeExpression parseComparative(ParseType parseType) {
        NodeExpression left = this.parseAdditive(parseType);
        while ((this.current().type() == TokenType.LESS_GREATER_THAN || this.current().type() == TokenType.EQUALS || this.current().type() == TokenType.NOT) && (this.tokens.get(1).type() == TokenType.EQUALS)) {
            String operator = this.tokens.removeFirst().value();
            operator += this.tokens.removeFirst().value();
            NodeExpression right = this.parseAdditive(parseType);
            left = new NodeBinaryComparisonExpression(left, operator, right);
        }
        return left;
    }



    //parses + and -
    private NodeExpression parseAdditive(ParseType parseType) {
        NodeExpression left = parseMultiplicativeExpression(parseType);

        while (this.current().type() == TokenType.OPERATOR && (this.current().value().equals("+") || this.current().value().equals("-"))) {
            String operator = this.tokens.removeFirst().value();
            boolean assignation = false;
            if(this.current().type() == TokenType.EQUALS) {
                this.expect(TokenType.EQUALS);
                if(left.type() == NodeType.IDENTIFIER) assignation = true;
                else Error.call("Can only assign variables not: "+left.type(), new IllegalArgumentException());
            }
            NodeExpression right = parseMultiplicativeExpression(parseType);
            left = assignation ? new NodeVariableAssignment((NodeIdentifier) left, new NodeBinaryExpression(left, operator, right)) : new NodeBinaryExpression(left, operator, right);
            if(assignation) {
                if(this.current().type() != TokenType.EQUALS && this.current().type() != TokenType.OPERATOR) {
                    if (parseType == ParseType.NORMAL) Error.call("Variable may not be reassigned at this position "+this.current().position()+", must be either in statement or in another function", new IllegalStateException());
                    if(parseType == ParseType.IN_FUNCTION || parseType == ParseType.IN_STATEMENT) this.expect(TokenType.SEMICOLON);
                }
            }
        }

        return left;
    }

    //parses *, /, ^, and %
    private NodeExpression parseMultiplicativeExpression(ParseType parseType) {
        NodeExpression left = parseUnaryExpression(parseType);

        while (this.current().type() == TokenType.OPERATOR && (!this.current().value().equals("+") && !this.current().value().equals("-"))) {
            String operator = this.tokens.removeFirst().value();
            boolean assignation = false;
            if(this.current().type() == TokenType.EQUALS) {
                this.expect(TokenType.EQUALS);
                if(left.type() == NodeType.IDENTIFIER) assignation = true;
                else Error.call("Can only assign variables not: "+left.type(), new IllegalArgumentException());
            }
            NodeExpression right = parseUnaryExpression(parseType);
            left = assignation ? new NodeVariableAssignment((NodeIdentifier) left, new NodeBinaryExpression(left, operator, right)) : new NodeBinaryExpression(left, operator, right);
            if(assignation) {
                if(this.current().type() != TokenType.EQUALS && this.current().type() != TokenType.OPERATOR) {
                    if (parseType == ParseType.NORMAL) Error.call("Variable may not be reassigned at this position "+this.current().position()+", must be either in statement or in another function", new IllegalStateException());
                    if(parseType == ParseType.IN_FUNCTION || parseType == ParseType.IN_STATEMENT) this.expect(TokenType.SEMICOLON);
                }
            }
        }

        return left;
    }

    private NodeExpression parseUnaryExpression(ParseType parseType) {
        if (this.current().type() == TokenType.OPERATOR || this.current().type() == TokenType.NOT) {
            String operator = this.tokens.removeFirst().value();
            if (operator.equals("+") || operator.equals("-")) {
                NodeExpression exp = this.parseCall(parseType);
                return new NodeUnaryExpression(operator, exp);
            } else if (operator.equals("!")) {
                //parse unary because it could be !!!true
                NodeExpression exp = this.parseUnaryExpression(parseType);
                return new NodeUnaryExpression(operator, exp);
            } else {
                Error.call("Invalid operator for unary expression: '" + operator + "'");
            }
        }
        return parseCall(parseType);
    }

    private NodeExpression parseCall(ParseType parseType) {
        NodeExpression call = parseFunctionCall(parseType);

        return call;
    }

    private NodeExpression parseFunctionCall(ParseType parseType) {
        NodeExpression identifier = parsePrimary(parseType);
        if (identifier.type() == NodeType.IDENTIFIER && this.current().type() == TokenType.PAREN_OPEN) {
            NodeFunctionCall nfc = new NodeFunctionCall((NodeIdentifier) identifier, this.parseArguments());
            if (parseType == ParseType.NORMAL) Error.call("Function may not be called at this position "+this.current().position()+", must be either in statement or in another function", new IllegalStateException());
            if(parseType == ParseType.IN_FUNCTION || parseType == ParseType.IN_STATEMENT) this.expect(TokenType.SEMICOLON);
            return nfc;
        }
        return identifier;
    }

    private NodeExpression parsePrimary(ParseType parseType) {
        NodeExpression ret = null;
        switch (this.current().type()) {
            case IDENTIFIER:
                ret = new NodeIdentifier(this.tokens.removeFirst().value());
                break;


            case INT:
                ret = new NodeNumericLiteral(Integer.valueOf(this.tokens.removeFirst().value()));
                break;
            case FLOAT:
                ret = new NodeNumericLiteral(Float.valueOf(this.tokens.removeFirst().value()));
                break;

            case BOOLEAN:
                ret = new NodeBooleanLiteral(Boolean.valueOf(this.tokens.removeFirst().value()));
                break;
            case CHAR:
                ret = new NodeCharLiteral(this.tokens.removeFirst().value().charAt(0));
                break;
            case STRING:
                ret = new NodeStringLiteral(this.tokens.removeFirst().value());
                break;

            case PAREN_OPEN:
                this.tokens.removeFirst();
                NodeExpression exp = this.parseExpressions(ParseType.IN_ARGUMENT);
                //token must be a closing paren;
                this.tokens.removeFirst();
                ret = exp;
                break;
            default:
                Error.call("Unexpected Token found: " + this.current()+"\n"+this.current().position());
                break;
        }
        return ret;
    }

    /*
     *
     * Arguments taken
     * instead of (paramA, paramB,...)
     * it is (1+4, value, ....)
     */
    private ArrayList<NodeExpression> parseArguments() {
        this.expect(TokenType.PAREN_OPEN);
        ArrayList<NodeExpression> val = new ArrayList<>();

        while (this.current().type() != TokenType.PAREN_CLOSE) {
            val.add(this.parseExpressions(ParseType.IN_ARGUMENT));
            if (this.current().type() == TokenType.COMMA) {
                this.tokens.removeFirst();
            }
        }

        this.expect(TokenType.PAREN_CLOSE);
        return val;
    }


    public NodeProgram build() {
        return new NodeProgram(nodes);
    }


    private Token current() {
        return tokens.getFirst();
    }

    private Token expect(TokenType type) {
        Token tk = this.tokens.removeFirst();
        if(tk.type() == type) {
            return tk;
        }else {
            Error.call("Invalid token, expected: "+type+" but found: "+tk.type());
            return null;
        }
    }


}
