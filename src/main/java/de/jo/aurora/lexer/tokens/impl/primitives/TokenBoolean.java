package de.jo.aurora.lexer.tokens.impl.primitives;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 31.05.2023
 * @Project AuroraScript
 */
public class TokenBoolean extends Token {

    public TokenBoolean(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.BOOLEAN;
    }

    @Override
    public boolean match(String val) {
        return val.equals("true") || val.equals("false");
    }
}
