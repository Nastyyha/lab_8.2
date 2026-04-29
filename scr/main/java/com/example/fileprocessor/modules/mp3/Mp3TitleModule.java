package com.example.fileprocessor.modules.mp3;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Component
public class Mp3TitleModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Вывод названия трека из тегов mp3";
    }

    @Override
    public void process(File file) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "ffprobe", "-v", "quiet", "-show_entries",
                "format_tags=title", "-of", "default=nw=1:nk=1", file.getAbsolutePath());

        Process process = pb.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String title = br.readLine();
            if (title == null || title.isBlank()) {
                System.out.println("Тег названия трека не найден");
            } else {
                System.out.println("Название трека: " + title);
            }
        }
        process.waitFor();
    }
}