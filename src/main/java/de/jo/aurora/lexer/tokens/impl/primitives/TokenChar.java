package de.jo.aurora.lexer.tokens.impl.primitives;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 31.05.2023
 * @Project AuroraScript
 */
public class TokenChar extends Token {
    public TokenChar(String value) {
        super(value);
    }

    @Override
    public TokenType type() {
        return TokenType.CHAR;
    }

    @Override
    public boolean match(String val) {
        return false;
    }
}
