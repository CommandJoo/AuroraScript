package de.jo.aurora.lexer.tokens.impl;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class TokenEndOfFile extends Token {
    public TokenEndOfFile(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.END_OF_FILE;
    }

    @Override
    public boolean match(String val) {
        return false;
    }
}
