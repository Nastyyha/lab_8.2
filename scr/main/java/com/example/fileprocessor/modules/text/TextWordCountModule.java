package com.example.fileprocessor.modules.text;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;

@Component
public class TextWordCountModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчет количества слов в текстовом файле";
    }

    @Override
    public void process(File file) throws Exception {
        String content = Files.readString(file.toPath());
        String[] words = content.split("\\W+");
        System.out.println("Количество слов: " + words.length);
    }
}