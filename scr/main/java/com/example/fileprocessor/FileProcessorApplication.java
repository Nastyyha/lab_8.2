package com.example.fileprocessor;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@SpringBootApplication
public class FileProcessorApplication implements CommandLineRunner {

    @Autowired
    private List<FileModule> modules;

    public static void main(String[] args) {
        SpringApplication.run(FileProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java -jar app.jar <filename>");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File not found: " + args[0]);
            return;
        }

        List<FileModule> supportedModules = modules.stream()
                .filter(m -> {
                    try {
                        return m.supports(file);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toList();

        if (supportedModules.isEmpty()) {
            System.out.println("No modules support this file.");
            return;
        }

        System.out.println("Choose a function to apply:");
        IntStream.range(0, supportedModules.size())
                .forEach(i -> System.out.println(i + ": " + supportedModules.get(i).getDescription()));

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice >= supportedModules.size()) {
            System.out.print("Enter choice number: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        System.out.println("Running: " + supportedModules.get(choice).getDescription());
        supportedModules.get(choice).process(file);
    }
}