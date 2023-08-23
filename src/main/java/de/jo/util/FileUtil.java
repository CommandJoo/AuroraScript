package de.jo.util;

import java.io.*;
import java.nio.file.Files;

/**
 * @author Johannes Hans 19.05.2023
 * @Project AuroraScript
 */
public class FileUtil {

    /**
     * This function reads an InputStream and returns its contents as a String.
     *
     * @param stream The parameter "stream" is an InputStream object, which represents a stream of bytes that can be read
     * from. The method "read" takes this InputStream as input and reads its contents using a BufferedReader and returns
     * the contents as a String.
     * @return The method is returning a String that contains the contents of the InputStream passed as a parameter.
     */
    public static String read(InputStream stream) {
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder sb = new StringBuilder();

            while((line = buff.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            return sb.toString();
        } catch(Exception ex) {
            throw new NullPointerException("Could not read InputStream '"+stream+"'!");
        }
    }

    public static String readFile(File file) {
        try {
            return read(Files.newInputStream(file.toPath()));
        } catch(Exception ex) {
            Error.softCall("Could not read File '"+file+"'!", ex);
            return "";
        }
    }




    /**
     * The function reads the contents of a resource file located at a given path and returns it as a string.
     *
     * @param path The path parameter is a string that represents the location of a resource file. This method reads the
     * contents of the resource file and returns it as a string.
     * @return The `readResource` method is returning a `String` value.
     */
    public static String readResource(String path) {
        return read(getResource(path));
    }

    /**
     * This function returns an input stream for a resource located at a given path using the system class loader.
     *
     * @param path The path parameter is a string that represents the location of a resource file that needs to be loaded.
     * This method uses the ClassLoader to get the resource as an InputStream.
     * @return The method is returning an InputStream object.
     */
    public static InputStream getResource(String path) {
        try {
            return ClassLoader.getSystemResourceAsStream(path);
        } catch(Exception ex) {
            Error.softCall("Could not find Resource '"+path+"'!", ex);
            return null;
        }
    }

}
