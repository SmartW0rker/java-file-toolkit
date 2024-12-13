package io;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class ReadingFromFile {
    enum ReaderType{
        FILE_READER,
        BUFFERED_READER,
        SCANNER
    }
    public static void main(String[] args) {
        ReaderType readerType=ReaderType.SCANNER;
        switch (readerType){
            case FILE_READER ->{
                System.out.println("Reading with FIleReader...");
                readWithFileReader();
            }
            case BUFFERED_READER -> {
                System.out.println("Reading with BufferedReader");
                readWithBufferedReader();
            }
            case SCANNER -> {
                System.out.println("Reading with Scanner");
                readWithScanner();
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
    private static void readWithScanner() {
        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader("files/readingFile.txt")))) {
             final int CASE = 3;
            switch (CASE){
                case 1 -> readInWhileLoop(scanner);
                case 2 -> readWithStream(scanner);
                case 3 -> readWithFindAll(scanner);
                default -> System.out.println("Unknown type of reader.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInWhileLoop(Scanner scanner) {
        while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
           }
    }

    private static void readWithStream(Scanner scanner) {
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("$");
        scanner.tokens().forEach(System.out::println);
    }

    private static void readWithFindAll(Scanner scanner) {
        scanner.findAll("[A-Za-z]{10,}")
                .map(MatchResult::group)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
