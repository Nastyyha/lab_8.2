package com.example.fileprocessor.modules.mp3;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Component
public class Mp3DurationModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Вывод длительности mp3 в секундах";
    }

    @Override
    public void process(File file) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "ffprobe", "-v", "error", "-show_entries",
                "format=duration", "-of",
                "default=noprint_wrappers=1:nokey=1", file.getAbsolutePath());

        Process process = pb.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String durationStr = br.readLine();
            if (durationStr == null) {
                System.out.println("Не удалось получить длительность");
                return;
            }
            double duration = Double.parseDouble(durationStr);
            System.out.printf("Длительность: %.2f секунд\n", duration);
        }
        process.waitFor();
    }
}