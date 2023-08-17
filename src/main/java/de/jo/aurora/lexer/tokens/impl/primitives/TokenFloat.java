package de.jo.aurora.lexer.tokens.impl.primitives;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenFloat extends Token {
    public TokenFloat(String value, TokenPosition pos) {
        super(value, pos);
    }
    @Override
    public TokenType type() {
        return TokenType.FLOAT;
    }

    @Override
    public boolean match(String val) {
        return false;
    }
}
