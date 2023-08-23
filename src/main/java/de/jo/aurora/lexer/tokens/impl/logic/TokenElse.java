package de.jo.aurora.lexer.tokens.impl.logic;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 23.08.2023
 * @Project AuroraScript
 */
public class TokenElse extends Token {
    public TokenElse(String value, TokenPosition position) {
        super(value, position);
    }

    @Override
    public TokenType type() {
        return TokenType.ELSE;
    }

    @Override
    public boolean match(String val) {
        return val.equals("else");
    }
}
