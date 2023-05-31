package de.jo.aurora.lexer.tokens.impl.grouping;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenParenClose extends Token {
    public TokenParenClose(String value) {
        super(value);
    }

    @Override
    public TokenType type() {
        return TokenType.PAREN_CLOSE;
    }

    @Override
    public boolean match(String val) {
        return val.equals(")");
    }
}
