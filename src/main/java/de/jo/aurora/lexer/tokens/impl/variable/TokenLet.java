package de.jo.aurora.lexer.tokens.impl.variable;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenLet extends Token {
    public TokenLet(String value) {
        super(value);
    }

    @Override
    public TokenType type() {
        return TokenType.TOKEN_LET;
    }

    @Override
    public boolean match(String val) {
        return val.equals("let");
    }
}
