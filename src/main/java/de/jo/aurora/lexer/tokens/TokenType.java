package de.jo.aurora.lexer.tokens;

import de.jo.aurora.lexer.tokens.impl.TokenEndOfFile;
import de.jo.aurora.lexer.tokens.impl.TokenIdentifier;
import de.jo.aurora.lexer.tokens.impl.objects.TokenFunction;
import de.jo.aurora.lexer.tokens.impl.operating.TokenOrAnd;
import de.jo.aurora.lexer.tokens.impl.syntax.*;
import de.jo.aurora.lexer.tokens.impl.operating.TokenEquals;
import de.jo.aurora.lexer.tokens.impl.operating.TokenNot;
import de.jo.aurora.lexer.tokens.impl.operating.TokenOperator;
import de.jo.aurora.lexer.tokens.impl.primitives.*;
import de.jo.aurora.lexer.tokens.impl.objects.TokenConst;
import de.jo.aurora.lexer.tokens.impl.objects.TokenLet;
import de.jo.util.Reflections;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public enum TokenType {

    //OPERATING
    EQUALS(TokenEquals.class, new TokenEquals(null, null)), OPERATOR(TokenOperator.class, new TokenOperator(null, null)),
    NOT(TokenNot.class, new TokenNot(null, null)), OR_AND(TokenOrAnd.class, new TokenOrAnd(null, null)),

    //SYNTAX
    PAREN_OPEN(TokenParenOpen.class, new TokenParenOpen(null, null)), PAREN_CLOSE(TokenParenClose.class, new TokenParenClose(null, null)),
    BRACKET_OPEN(TokenBracketOpen.class, new TokenBracketOpen(null, null)), BRACKET_CLOSE(TokenBracketClose.class, new TokenBracketClose(null, null)),
    BRACE_OPEN(TokenBraceOpen.class, new TokenBraceOpen(null, null)), BRACE_CLOSE(TokenBraceClose.class, new TokenBraceClose(null, null)),

    SEMICOLON(TokenSemicolon.class, new TokenSemicolon(null, null)),

    //PRIMITIVES
    INT(TokenInt.class, new TokenInt("0", null)), FLOAT(TokenFloat.class, new TokenFloat("0.0", null)),
    BOOLEAN(TokenBoolean.class, new TokenBoolean("false", null)), STRING(TokenString.class, new TokenString("\"\"", null)),
    CHAR(TokenChar.class, new TokenChar("''", null)),

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
