package de.jo;

import de.jo.aurora.AuroraScript;
import de.jo.util.FileUtil;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream script = FileUtil.getResource("script.aur");
        AuroraScript aurora = new AuroraScript(script);

    }
}