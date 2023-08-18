package de.jo.aurora.lexer.tokens.impl.syntax;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 18.08.2023
 * @Project AuroraScript
 */
public class TokenComma extends Token {
    public TokenComma(String value, TokenPosition position) {
        super(value, position);
    }

    @Override
    public TokenType type() {
        return TokenType.COMMA;
    }

    @Override
    public boolean match(String val) {
        return val.equals(",");
    }
}
