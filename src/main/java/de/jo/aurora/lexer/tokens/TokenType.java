package de.jo.aurora.lexer.tokens;

import de.jo.aurora.lexer.tokens.impl.*;
import de.jo.aurora.lexer.tokens.impl.logic.TokenElse;
import de.jo.aurora.lexer.tokens.impl.logic.TokenFor;
import de.jo.aurora.lexer.tokens.impl.logic.TokenIf;
import de.jo.aurora.lexer.tokens.impl.logic.TokenWhile;
import de.jo.aurora.lexer.tokens.impl.operating.*;
import de.jo.aurora.lexer.tokens.impl.syntax.*;
import de.jo.aurora.lexer.tokens.impl.primitives.*;
import de.jo.aurora.lexer.tokens.impl.objects.*;

import de.jo.util.Reflections;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public enum TokenType {

    //OPERATING
    EQUALS(TokenEquals.class, new TokenEquals(null, null)), OPERATOR(TokenOperator.class, new TokenOperator(null, null)),
    NOT(TokenNot.class, new TokenNot(null, null)), OR_AND(TokenOrAnd.class, new TokenOrAnd(null, null)),
    LESS_GREATER_THAN(TokenLessGreater.class, new TokenLessGreater(null, null)),
    TERNARY(TokenTernary.class, new TokenTernary(null, null)),

    //SYNTAX
    PAREN_OPEN(TokenParenOpen.class, new TokenParenOpen(null, null)), PAREN_CLOSE(TokenParenClose.class, new TokenParenClose(null, null)),
    BRACKET_OPEN(TokenBracketOpen.class, new TokenBracketOpen(null, null)), BRACKET_CLOSE(TokenBracketClose.class, new TokenBracketClose(null, null)),
    BRACE_OPEN(TokenBraceOpen.class, new TokenBraceOpen(null, null)), BRACE_CLOSE(TokenBraceClose.class, new TokenBraceClose(null, null)),

    SEMICOLON(TokenSemicolon.class, new TokenSemicolon(null, null)), COLON(TokenColon.class, new TokenColon(null, null)),
    DOT(TokenDot.class, new TokenDot(null, null)), COMMA(TokenComma.class, new TokenComma(null, null)),
    RETURN(TokenReturn.class, new TokenReturn(null, null)),

    //LOGIC
    IF(TokenIf.class, new TokenIf(null, null)), ELSE(TokenElse.class, new TokenElse(null, null)),
    FOR(TokenFor.class, new TokenFor(null, null)), WHILE(TokenWhile.class, new TokenWhile(null, null)),


    //PRIMITIVES
    INT(TokenInt.class, new TokenInt("0", null)), FLOAT(TokenFloat.class, new TokenFloat("0.0", null)),
    BOOLEAN(TokenBoolean.class, new TokenBoolean("false", null)), STRING(TokenString.class, new TokenString("\"\"", null)),
    CHAR(TokenChar.class, new TokenChar("''", null)), NULL(TokenNull.class, new TokenNot(null, null)),

    //OBJECTS
    IDENTIFIER(TokenIdentifier.class, new TokenIdentifier(null, null)),
    CONST(TokenConst.class, new TokenConst(null, null)), LET(TokenLet.class, new TokenLet(null, null)),
    FUNCTION(TokenFunction.class, new TokenFunction(null, null)),

    //EOF
    END_OF_FILE(TokenEndOfFile.class, new TokenEndOfFile(null, null));

    private final Token token;
    private final Class<? extends Token> clazz;

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

    public Token generate(String text, TokenPosition position) {
        return Reflections.construct(clazz, new Class[]{String.class, TokenPosition.class}, new Object[]{text, position});
    }


}
