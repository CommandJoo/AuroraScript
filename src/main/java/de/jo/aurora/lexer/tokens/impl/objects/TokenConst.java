package de.jo.aurora.lexer.tokens.impl.objects;

import de.jo.aurora.lexer.tokens.Token;
import de.jo.aurora.lexer.tokens.TokenPosition;
import de.jo.aurora.lexer.tokens.TokenType;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class TokenConst extends Token {
    public TokenConst(String value, TokenPosition pos) {
        super(value, pos);
    }

    @Override
    public TokenType type() {
        return TokenType.CONST;
    }

    @Override
    public boolean match(String val) {
        return val.equals("const");
    }
}
