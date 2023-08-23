package de.jo.aurora.lexer.tokens.impl.syntax;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 17.08.2023
 * @Project AuroraScript
 */
public class TokenSemicolon extends Token {
    public TokenSemicolon(String value, TokenPosition position) {
        super(value, position);
    }

    @Override
    public TokenType type() {
        return TokenType.SEMICOLON;
    }

    @Override
    public boolean match(String val) {
        return val.equals(";");
    }
}
