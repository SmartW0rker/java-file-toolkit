package fileAndPathDemo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileAndPath2 {
    public static void main(String[] args) {
        useFile("files/textfile.txt");
        usePath("files/pathfile.txt");
    }

    private static void useFile(String fileName) {
        File file=new File(fileName);
        boolean doesExist=file.exists();
        System.out.println("File "+fileName+ (doesExist? " exist" : " does not exist"));
        if (doesExist) {
            doesExist = !file.delete();
            System.out.println("Deleting File: " + fileName);
        }

        if (!doesExist){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong");
                throw new RuntimeException(e);
            }
            System.out.println("Created File: " + fileName);
            if (file.canWrite()) {
                System.out.println("Would write to file here");
            }

        }
    }

    private static void usePath(String fileName) {
        Path path= Path.of(fileName);
        boolean fileExists = Files.exists(path);

        System.out.printf("File '%s' %s%n", fileName,
                fileExists ? "exists." : "does not exist.");

        if (fileExists) {
            System.out.println("Deleting File: " + fileName);
            try {
                Files.delete(path);
                fileExists= false;
            } catch (IOException e) {
                System.out.println("Something went wrong");
                throw new RuntimeException(e);
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Something went wrong");
                throw new RuntimeException(e);
            }
            System.out.println("Created File: " + fileName);
            if (Files.isWritable(path)) {
                try {
                    System.out.println("Would write to file here");
                    Files.writeString(path, """
                                Here is some data,
                                For my file,
                                just to prove,
                                Using the Files class and path are better!
                                """);
                    System.out.println("Reading from file");
                    Files.readAllLines(path).forEach(System.out::println);
                } catch (IOException e) {
                    System.out.println("Something went wrong");
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
