package de.jo.aurora.lexer.tokens.impl.operating;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 31.05.2023
 * @Project AuroraScript
 */
public class TokenNot extends Token {
    public TokenNot(String value) {
        super(value);
    }

    @Override
    public TokenType type() {
        return TokenType.NOT;
    }

    @Override
    public boolean match(String val) {
        return val.equals("!");
    }
}
