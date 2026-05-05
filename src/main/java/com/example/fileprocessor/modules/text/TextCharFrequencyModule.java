package com.example.fileprocessor.modules.text;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Component
public class TextCharFrequencyModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Вывод частоты вхождения каждого символа в тексте";
    }

    @Override
    public void process(File file) throws Exception {
        String content = Files.readString(file.toPath());
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : content.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }

        System.out.println("Частота символов (не считая пробелы):");
        freq.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(e -> System.out.printf("'%s' : %d\n", e.getKey(), e.getValue()));
    }
}