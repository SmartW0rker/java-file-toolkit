package fileAndPathDemo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAndPath1 {
    public static void main(String[] args) {

        // This is with java.io package and File class
        System.out.println(new File("").getAbsolutePath());

        String fileName="/files/text.txt";

        File file=new File(new File("").getAbsolutePath(),fileName);

        System.out.println(file.getName());
        System.out.println("Parent folder for the text.file is: "+ file.getParentFile().getName());
        if (!file.exists()) {
            System.out.println("1.I can't run unless this file exists");
            return;
        }
        System.out.println("1.I'm good to go.");

        for (File root: File.listRoots()){
            System.out.println(root);
        }

        Path path= Paths.get(fileName);
        if (Files.exists(path)) {
            System.out.println("2.I can't run unless this file exists");
            return;
        }
        System.out.println("2.I'm good to go.");
    }
}