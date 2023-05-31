package de.jo.aurora.lexer;

import de.jo.aurora.lexer.tokens.TokenList;
import de.jo.aurora.lexer.tokens.TokenType;
import de.jo.aurora.lexer.tokens.impl.TokenEndOfFile;
import de.jo.aurora.lexer.tokens.impl.primitives.TokenChar;
import de.jo.aurora.lexer.tokens.impl.primitives.TokenFloat;
import de.jo.aurora.lexer.tokens.impl.primitives.TokenInt;
import de.jo.aurora.lexer.tokens.impl.primitives.TokenString;
import de.jo.util.Error;
import de.jo.util.StringUtil;

import java.util.LinkedList;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class Lexer {

    private LinkedList<String> code;
    private TokenList tokens;

    public Lexer(String code) {
        this.code = StringUtil.chop(code);
        this.tokens = new TokenList();
    }

    public void lex() {
        while (!code.isEmpty()) {
            /**
             * Loops through all TokenType values
             * If one match method returns true it will be added
             * However there are classes like TokenInt that always return false
             * these types have to always be lexed manually
             **/
            for (TokenType tokenType : TokenType.values()) {
                if (tokenType.match(this.current())) {
                    tokens.add(tokenType.generate(code.removeFirst()));
                }
            }

            if(this.current().equals("\"")) {
                this.code.removeFirst();
                StringBuilder ident = new StringBuilder(this.code.removeFirst());

                while(!this.current().equals("\"")) {
                    ident.append(this.code.removeFirst());
                }
                this.code.removeFirst();

                this.tokens.add(new TokenString(ident.toString()));
            }
            if(this.current().equals("'")) {
                this.code.removeFirst();
                StringBuilder ident = new StringBuilder();

                ident.append(this.current());
                this.code.removeFirst();

                if(this.current().equals("'")) {
                    this.code.removeFirst();
                    this.tokens.add(new TokenChar(ident.toString()));
                }else{
                    Error.call("Expected \"'\" for token char but found: '"+this.current()+"'");
                }
            }

            //The text must either be an identifier like 'variableName' or a languagespecific token like 'const'
            if (StringUtil.isIdentifier(this.current()) && !StringUtil.isNumeric(this.current())) {
                StringBuilder ident = new StringBuilder(this.code.removeFirst());
                while(StringUtil.isIdentifier(this.current())) {
                    ident.append(this.code.removeFirst());
                }

                if(TokenType.matches(ident.toString()) != null) {
                    tokens.add(TokenType.matches(ident.toString()).generate(ident.toString()));
                }else {
                    tokens.add(TokenType.IDENTIFIER.generate(ident.toString()));
                }

            } else if(StringUtil.isNumeric(this.current()) || this.current().equals(".")){
                if (current().equals(".")) {
                    //The text is a floating point number
                    StringBuilder sb = new StringBuilder(code.removeFirst());
                    while (StringUtil.isNumeric(this.current())) {
                        sb.append(code.removeFirst());
                    }
                    this.tokens.add(new TokenFloat(sb.toString()));
                } else {
                    //The text is a number
                    StringBuilder sb = new StringBuilder(code.removeFirst());
                    while (StringUtil.isNumeric(this.current())) {
                        sb.append(code.removeFirst());
                    }

                    //The text is a floating point number
                    if (this.current().equals(".")) {
                        sb.append(this.code.removeFirst());
                        while (StringUtil.isNumeric(this.current())) {
                            sb.append(code.removeFirst());
                        }
                        this.tokens.add(new TokenFloat(sb.toString()));
                    } else {
                        this.tokens.add(new TokenInt(sb.toString()));
                    }
                }
            }else if(Character.isWhitespace(this.current().charAt(0))){
                code.removeFirst();
            }
        }
        tokens.add(new TokenEndOfFile(""));
        System.out.println(tokens);
    }

    public String current() {
        return this.code.getFirst();
    }

    public TokenList build() {
        return tokens;
    }

}
