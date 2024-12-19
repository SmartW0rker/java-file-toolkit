package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WritingToFile {
    public static void main(String[] args) {
        String codeBlock= """
                Here is some data,
                For my file,
                just to show,
                how to write in file!
                """;

        List<String> lines=codeBlock.lines().toList();

        Path path= Path.of("files/writingFile");
        try {

            Files.write(path,lines,StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String newCodeBlock= """
                This is additional code block,
                For my file,
                just to show,
                how to write in file with BufferedWriter!
                """;

        lines=newCodeBlock.lines().toList();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,StandardOpenOption.APPEND)) {

            for (String string : lines) {
                writer.write(string);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
