package de.jo.aurora.lexer.tokens.impl.operating;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenOperator extends Token {

    public TokenOperator(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.OPERATOR;
    }

    @Override
    public boolean match(String val) {
        return val.equals("*") || val.equals("/") || val.equals("^") || val.equals("%") || val.equals("+") ||val.equals("-");
    }
}
