package de.jo.aurora.lexer.tokens.impl.syntax;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenBraceClose extends Token {
    public TokenBraceClose(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.PAREN_CLOSE;
    }

    @Override
    public boolean match(String val) {
        return val.equals("}");
    }
}
