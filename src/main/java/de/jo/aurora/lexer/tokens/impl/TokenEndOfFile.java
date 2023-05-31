package de.jo.aurora.lexer.tokens.impl;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenEndOfFile extends Token {
    public TokenEndOfFile(String value) {
        super(value);
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
