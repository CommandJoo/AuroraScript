package de.jo.aurora.lexer.tokens.impl.primitives;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenInt extends Token {
    public TokenInt(String value) {
        super(value);
    }

    @Override
    public TokenType type() {
        return TokenType.INT;
    }

    @Override
    public boolean match(String val) {
        return false;
    }
}
