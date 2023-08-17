package de.jo.aurora.lexer.tokens.impl.objects;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 17.08.2023
 * @Project AuroraScript
 */
public class TokenFunction extends Token {
    public TokenFunction(String value, TokenPosition position) {
        super(value, position);
    }

    @Override
    public TokenType type() {
        return TokenType.FUNCTION;
    }

    @Override
    public boolean match(String val) {
        return val.equals("fun");
    }
}
