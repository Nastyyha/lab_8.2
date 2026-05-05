package com.example.fileprocessor.modules.mp3;

import com.example.fileprocessor.modules.FileModule;
import com.mpatric.mp3agic.Mp3File;
import org.springframework.stereotype.Component;

import java.io.File;

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
        try {
            Mp3File mp3file = new Mp3File(file.getAbsolutePath());

            // Длительность в секундах
            long durationSeconds = mp3file.getLengthInSeconds();
            int minutes = (int) (durationSeconds / 60);
            int seconds = (int) (durationSeconds % 60);

            System.out.printf("Длительность: %d мин %d сек (%d секунд)\n",
                    minutes, seconds, durationSeconds);
        } catch (Exception e) {
            System.out.println("Ошибка при чтении MP3 файла: " + e.getMessage());
        }
    }
}