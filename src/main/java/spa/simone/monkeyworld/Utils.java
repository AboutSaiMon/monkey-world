package spa.simone.monkeyworld;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Simone Spaccarotella
 */
public class Utils {

    private static String getFullPath(String fileName) {
        return ClassLoader.getSystemResource(fileName).getFile();
    }

    public static Image getImage(String image) {
        return Toolkit.getDefaultToolkit().getImage(getFullPath(image));
    }

    public static File writeGoalFile(String content) {
        PrintWriter out = null;
        File file = null;
        try {
            file = File.createTempFile("goal", ".plan").getAbsoluteFile();
            System.out.println("Temp File: " + file.getAbsolutePath());
            out = new PrintWriter(file);
            out.println(content);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return file;
    }

}
