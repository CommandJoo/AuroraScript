package de.jo.aurora.lexer.tokens.impl.syntax;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class TokenBraceOpen extends Token {
    public TokenBraceOpen(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.BRACE_OPEN;
    }

    @Override
    public boolean match(String val) {
        return val.equals("{");
    }
}
