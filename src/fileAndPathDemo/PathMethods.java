package fileAndPathDemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PathMethods {
    public static void main(String[] args) {
        Path path=Path.of("this/is/several/folder/testing/file.txt");

        printPathAtt(path);
        logStatement(path);
        extraInfo(path);
    }

    private static void printPathAtt(Path path) {
        System.out.println("Path : " + path);
        System.out.println("File name : " + path.getFileName());
        System.out.println("File parent is : " + path.getParent().getFileName());
        System.out.println("Path : " + path);
        Path absolutePath=path.toAbsolutePath();
        System.out.println("FIle absolute path is : " + absolutePath);
        System.out.println("Absolute Path Root: = " + absolutePath.getRoot());
        System.out.println("Root = " + path.getRoot());
        System.out.println("isAbsolute = " + path.isAbsolute());

        int folderCount =absolutePath.getNameCount();
        for (int i=0;i<folderCount;i++){
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        System.out.println("-----------------------");
    }
    private static void logStatement(Path path) {
        Path parent=path.getParent();

        if (!Files.exists(parent)){
            try {
                Files.createDirectories(parent);
                Files.writeString(path,"Something in the file.",
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void extraInfo(Path path) {

        try {
            var atts = Files.readAttributes(path, "*");
            atts.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            System.out.println("Problem getting attributes");
        }
    }
}
