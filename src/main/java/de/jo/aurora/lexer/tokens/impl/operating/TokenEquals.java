package de.jo.aurora.lexer.tokens.impl.operating;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class TokenEquals extends Token {

    public TokenEquals(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.EQUALS;
    }

    @Override
    public boolean match(String val) {
        return val.equals("=");
    }
}
