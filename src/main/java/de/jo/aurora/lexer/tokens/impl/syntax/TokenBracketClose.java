package de.jo.aurora.lexer.tokens.impl.syntax;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class TokenBracketClose extends Token {
    public TokenBracketClose(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.BRACKET_CLOSE;
    }

    @Override
    public boolean match(String val) {
        return val.equals("]");
    }
}
