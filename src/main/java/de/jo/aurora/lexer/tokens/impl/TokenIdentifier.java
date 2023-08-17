package de.jo.aurora.lexer.tokens.impl;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenIdentifier extends Token {
    public TokenIdentifier(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.IDENTIFIER;
    }

    @Override
    public boolean match(String val) {
        //must always return false
        return false;
    }
}
