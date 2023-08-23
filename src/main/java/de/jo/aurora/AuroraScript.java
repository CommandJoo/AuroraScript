package de.jo.aurora;

import de.jo.aurora.interpreter.Interpreter;
import de.jo.aurora.interpreter.Scope;
import de.jo.aurora.lexer.Lexer;
import de.jo.aurora.lexer.tokens.TokenList;
import de.jo.aurora.parser.Parser;
import de.jo.aurora.parser.nodes.impl.NodeProgram;
import de.jo.util.FileUtil;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class AuroraScript {

    public AuroraScript(String code) {
        Lexer lexer = new Lexer(code);
        lexer.lex();
        TokenList tokens = lexer.build();
        System.out.println(tokens);

        Parser parser = new Parser(tokens);
        parser.parse();
        NodeProgram program = parser.build();
//        System.out.println(program);

        Interpreter interpreter = new Interpreter(program, new ArrayList<>());
        Object obj = interpreter.interpret(Scope.globalScope());
        System.out.println(obj);
    }

    public AuroraScript(InputStream code) {
        this(FileUtil.read(code));
    }

    public AuroraScript(File code) {
        this(FileUtil.readFile(code));
    }

}
