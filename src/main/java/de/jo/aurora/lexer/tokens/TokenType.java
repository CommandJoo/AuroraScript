package de.jo.aurora.lexer.tokens;

import de.jo.aurora.lexer.tokens.impl.TokenEndOfFile;
import de.jo.aurora.lexer.tokens.impl.TokenIdentifier;
import de.jo.aurora.lexer.tokens.impl.grouping.*;
import de.jo.aurora.lexer.tokens.impl.operating.TokenEquals;
import de.jo.aurora.lexer.tokens.impl.operating.TokenNot;
import de.jo.aurora.lexer.tokens.impl.operating.TokenOperator;
import de.jo.aurora.lexer.tokens.impl.primitives.*;
import de.jo.aurora.lexer.tokens.impl.variable.TokenConst;
import de.jo.aurora.lexer.tokens.impl.variable.TokenLet;
import de.jo.util.Reflections;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public enum TokenType {

    //OPERATING
    EQUALS(TokenEquals.class, new TokenEquals(null)), OPERATOR(TokenOperator.class, new TokenOperator(null)), NOT(TokenNot.class, new TokenNot(null)),

    //
    PAREN_OPEN(TokenParenOpen.class, new TokenParenOpen(null)), PAREN_CLOSE(TokenParenClose.class, new TokenParenClose(null)),
    BRACKET_OPEN(TokenBracketOpen.class, new TokenBracketOpen(null)), BRACKET_CLOSE(TokenBracketClose.class, new TokenBracketClose(null)),
    BRACE_OPEN(TokenBraceOpen.class, new TokenBraceOpen(null)), BRACE_CLOSE(TokenBraceClose.class, new TokenBraceClose(null)),

    //PRIMITIVES
    INT(TokenInt.class, new TokenInt("0")), FLOAT(TokenFloat.class, new TokenFloat("0.0")),
    BOOLEAN(TokenBoolean.class, new TokenBoolean("false")), STRING(TokenString.class, new TokenString("\"\"")),
    CHAR(TokenChar.class, new TokenChar("''")),

    //IDENT
    IDENTIFIER(TokenIdentifier.class, new TokenIdentifier(null)),

    //ACCESS
    TOKEN_CONST(TokenConst.class, new TokenConst(null)), TOKEN_LET(TokenLet.class, new TokenLet(null)),

    //EOF
    END_OF_FILE(TokenEndOfFile.class, new TokenEndOfFile(null));

    private Token token;
    private Class<? extends Token> clazz;

    public static TokenType matches(String text) {
        for(TokenType type : values()) {
            if(type.match(text)) return type;
        }
        return null;
    }

    TokenType(Class<? extends Token> clazz, Token token) {
        this.token = token;
        this.clazz = clazz;
    }

    public boolean match(String text) {
        return token.match(text);
    }

    public Token generate(String text) {
        return Reflections.construct(clazz, new Class[]{String.class}, new String[]{text});
    }


}
