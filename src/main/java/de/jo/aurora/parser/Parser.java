package de.jo.aurora.parser;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenList;
import de.jo.aurora.lexer.tokens.TokenType;
import de.jo.aurora.lexer.tokens.impl.TokenIdentifier;
import de.jo.aurora.parser.nodes.Node;
import de.jo.aurora.parser.nodes.NodeExpression;
import de.jo.aurora.parser.nodes.impl.NodeProgram;
import de.jo.aurora.parser.nodes.impl.expressions.NodeBinaryExpression;
import de.jo.aurora.parser.nodes.impl.expressions.NodeUnaryExpression;
import de.jo.aurora.parser.nodes.impl.expressions.objects.*;
import de.jo.aurora.parser.nodes.impl.statements.NodeVariableDeclaration;
import de.jo.util.Error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class Parser {

    private TokenList tokens;
    private List<Node> nodes;

    public Parser(TokenList tokens) {
        this.tokens = tokens;
        this.nodes = new ArrayList<>();
    }

    public void parse() {
        while (this.current().type() != TokenType.END_OF_FILE) {
            nodes.add(parseStatements());
        }
    }

    private Node parseStatements() {
        switch (this.current().type()) {
            case LET:
            case CONST:
                return this.parseVariableDeclaration();
        }
        return parseExpressions();
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
        boolean constant = false;

        if(this.current().type() == TokenType.CONST) constant = true;
        if(this.current().type() != TokenType.LET) Error.call("Invalid Token expected const or let but found: "+this.current().type());
        this.tokens.removeFirst();

        NodeIdentifier identifier = new NodeIdentifier(this.expect(TokenType.IDENTIFIER).value());
        this.expect(TokenType.EQUALS);
        NodeExpression value = this.parseExpressions();
        this.expect(TokenType.SEMICOLON);
        return new NodeVariableDeclaration(constant, identifier, value);
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










    private NodeExpression parseExpressions() {
        return parseComparative();
    }

    //parses boolean && boolean or boolean || boolean
    private NodeExpression parseComparative() {
        NodeExpression left = parseAdditive();
        while(this.current().type() == TokenType.OR_AND && this.tokens.get(1).type() == TokenType.OR_AND) {
            //builds an operator like &| or && or ||
            String operator = this.tokens.removeFirst().value()+this.tokens.removeFirst().value();
            NodeExpression right = this.parseAdditive();
            left = new NodeBinaryExpression(left, operator, right);
        }
        return left;
    }

    //parses + and -
    private NodeExpression parseAdditive() {
        NodeExpression left = parseMultiplicativeExpression();

        while (this.current().type() == TokenType.OPERATOR && (this.current().value().equals("+") || this.current().value().equals("-"))) {
            String operator = this.tokens.removeFirst().value();
            NodeExpression right = parseMultiplicativeExpression();
            left = new NodeBinaryExpression(left, operator, right);
        }

        return left;
    }

    //parses *, /, ^, and %
    private NodeExpression parseMultiplicativeExpression() {
        NodeExpression left = parseUnaryExpression();

        while (this.current().type() == TokenType.OPERATOR && (!this.current().value().equals("+") && !this.current().value().equals("-"))) {
            String operator = this.tokens.removeFirst().value();
            NodeExpression right = parseUnaryExpression();
            left = new NodeBinaryExpression(left, operator, right);
        }

        return left;
    }

    private NodeExpression parseUnaryExpression() {
        if(this.current().type() == TokenType.OPERATOR || this.current().type() == TokenType.NOT) {
            String operator = this.tokens.removeFirst().value();
            if(operator.equals("+") || operator.equals("-")) {
                NodeExpression exp = this.parsePrimary();
                return new NodeUnaryExpression(operator, exp);
            }
            else if(operator.equals("!")) {
                NodeExpression exp = this.parsePrimary();
                return new NodeUnaryExpression(operator, exp);
            }
            else{
                Error.call("Invalid operator for unary expression: '"+operator+"'");
            }
        }
        return parsePrimary();
    }

    private NodeExpression parsePrimary() {
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
                NodeExpression exp = this.parseExpressions();
                //token must be a closing paren;
                this.tokens.removeFirst();
                ret = exp;
                break;
            default:
                Error.call("Unexpected Token found: "+this.current());
                break;
        }
        return ret;
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
