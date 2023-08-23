package de.jo.aurora.lexer.tokens.impl.primitives;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 23.08.2023
 * @Project AuroraScript
 */
public class TokenNull extends Token {
    public TokenNull(String value, TokenPosition position) {
        super(value, position);
    }

    @Override
    public TokenType type() {
        return TokenType.NULL;
    }

    @Override
    public boolean match(String val) {
        return val.equals("null");
    }
}
