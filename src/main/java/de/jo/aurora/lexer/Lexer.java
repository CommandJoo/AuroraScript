package de.jo.aurora.lexer;

import de.jo.aurora.lexer.tokens.TokenList;
import de.jo.aurora.lexer.tokens.TokenPosition;
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

    private final LinkedList<String> code;
    private final TokenList tokens;

    public Lexer(String code) {
        this.code = StringUtil.chop(code);
        this.tokens = new TokenList();
    }

    public void lex() {
        TokenPosition position = new TokenPosition(1, 0);

        while (!code.isEmpty()) {
            if (this.current().equals("/") && this.code.get(1).equals("/")) {
                this.shift();
                update(position);
                this.shift();
                update(position);
                StringBuilder comment = new StringBuilder();
                while(!code.isEmpty()) {
                    if(!this.current().equals("\n")) {
                        comment.append(this.shift());
                    }else {
                        System.out.println("Comment: "+comment+" at: "+position);
                        update(position);
                        break;
                    }
                }
            }
            /*
             * Loops through all TokenType values
             * If one match method returns true it will be added
             * However there are classes like TokenInt that always return false
             * these types have to be tokenized manually
             **/
            for (TokenType tokenType : TokenType.values()) {
                if (tokenType.match(this.current())) {
                    tokens.add(tokenType.generate(shift(), position));
                    position = this.update(position);
                }
            }

            if(this.current().equals("\"")) {
                this.shift();
                position = this.update(position);
                if(this.current().equals("\"")) {
                    tokens.add(new TokenString("", position));
                    this.shift();
                    continue;
                }
                StringBuilder ident = new StringBuilder(this.shift());
                if(this.current().equals("\"")) {
                    this.tokens.add(new TokenString(ident.toString(), position));
                    continue;
                }
                while (!this.current().equals("\"")) {
                    ident.append(this.shift());
                    position = this.update(position);
                }
                this.shift();
                position = this.update(position);

                this.tokens.add(new TokenString(ident.toString(), position));
            }
            if(this.current().equals("'")) {
                this.shift();
                position = this.update(position);

                StringBuilder ident = new StringBuilder();

                ident.append(this.current());
                this.shift();
                position = this.update(position);

                if (this.current().equals("'")) {
                    this.shift();
                    position = this.update(position);
                    this.tokens.add(new TokenChar(ident.toString(), position));
                }else{
                    Error.callToken("Expected \"'\" for token char but found: '" + this.current() + "'", position);
                }
            }

            //The text must either be an identifier like 'variableName' or a language specific token like 'const'
            if (StringUtil.isIdentifier(this.current()) && !StringUtil.isNumeric(this.current())) {
                StringBuilder ident = new StringBuilder(this.shift());
                position = this.update(position);
                while(StringUtil.isIdentifier(this.current())) {
                    ident.append(this.shift());
                    position = this.update(position);
                }

                if(TokenType.matches(ident.toString()) != null) {
                    tokens.add(TokenType.matches(ident.toString()).generate(ident.toString(), position));
                }else {
                    tokens.add(TokenType.IDENTIFIER.generate(ident.toString(), position));
                }

            } else if(StringUtil.isNumeric(this.current()) || this.current().equals(".")){
                if (current().equals(".")) {
                    //The text is a floating point number
                    StringBuilder sb = new StringBuilder(shift());
                    position = this.update(position);
                    while (StringUtil.isNumeric(this.current())) {
                        sb.append(shift());
                        position = this.update(position);
                    }
                    this.tokens.add(new TokenFloat(sb.toString(), position));
                } else {
                    //The text is a number
                    StringBuilder sb = new StringBuilder(shift());
                    position = this.update(position);
                    while (StringUtil.isNumeric(this.current())) {
                        sb.append(shift());
                        position = this.update(position);
                    }

                    //The text is a floating point number
                    if (this.current().equals(".")) {
                        sb.append(this.shift());
                        position = this.update(position);
                        while (StringUtil.isNumeric(this.current())) {
                            sb.append(shift());
                            position = this.update(position);
                        }
                        this.tokens.add(new TokenFloat(sb.toString(), position));
                    } else {
                        this.tokens.add(new TokenInt(sb.toString(), position));
                    }
                }
            } else if (Character.isWhitespace(this.current().charAt(0))) {
                this.shift();
                position = this.update(position);
            }
        }
        tokens.add(new TokenEndOfFile("", position));
    }

    /**
     * The function updates the position of a token, incrementing the position and line number if the current token is a
     * newline character.
     *
     * @param position The parameter "position" is an object of type TokenPosition.
     * @return The method is returning the updated TokenPosition object.
     */
    public TokenPosition update(TokenPosition position) {
        position.setPos(position.pos() + 1);
        if (this.code.isEmpty()) return position;
        if (this.current().equals("\n")) {
            position.setLine(position.line() + 1);
            position.setPos(0);
        }
        return position;
    }

    /**
     * The current() function returns the first element in a linked list called "code".
     *
     * @return The method is returning the first element in the code list.
     */
    public String current() {
        return this.code.getFirst();
    }

    public String shift() {
        return this.code.removeFirst();
    }

    /**
     * The function returns a TokenList object.
     *
     * @return The method is returning a TokenList object.
     */
    public TokenList build() {
        return tokens;
    }

}
