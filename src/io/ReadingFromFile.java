package io;

import java.io.*;

public class ReadingFromFile {
    enum ReaderType{
        FILE_READER,
        BUFFERED_READER,
        SCANNER
    }
    public static void main(String[] args) {
        ReaderType readerType=ReaderType.FILE_READER;
        switch (readerType){
            case FILE_READER ->{
                System.out.println("Reading with FIleReader...");
                readWithFileReader();
            }
            case BUFFERED_READER -> {
                System.out.println("Reading with BufferedReader");
                readWithBufferedReader();
            }
            default -> System.out.println("Unknown type of reader.");
        }
    }

    private static void readWithFileReader() {
        try (FileReader reader = new FileReader("files/readingFile.txt")) {
            char[] block=new char[1000];
            int data;
            while ((data= reader.read(block))!=-1){
                String content=String.valueOf(block,0,data);
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readWithBufferedReader() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files/readingFile.txt"))) {
            bufferedReader.lines().forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
