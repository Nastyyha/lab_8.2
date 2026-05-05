package com.example.fileprocessor.modules.text;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class TextLineCountModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчет и вывод количества строк в текстовом файле";
    }

    @Override
    public void process(File file) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            long lines = br.lines().count();
            System.out.println("Количество строк: " + lines);
        }
    }
}