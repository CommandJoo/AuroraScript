package de.jo.aurora.lexer.tokens.impl.variable;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class TokenConst extends Token {
    public TokenConst(String value) {
        super(value);
    }

    @Override
    public TokenType type() {
        return TokenType.TOKEN_CONST;
    }

    @Override
    public boolean match(String val) {
        return val.equals("const");
    }
}
