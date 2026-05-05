package com.example.fileprocessor.modules.mp3;

import com.example.fileprocessor.modules.FileModule;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import org.springframework.stereotype.Component;

import java.io.File;

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
        try {
            Mp3File mp3file = new Mp3File(file.getAbsolutePath());

            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                String title = id3v2Tag.getTitle();

                if (title == null || title.isBlank()) {
                    System.out.println("Тег названия трека не найден");
                } else {
                    System.out.println("Название трека: " + title);
                }
            } else if (mp3file.hasId3v1Tag()) {
                String title = mp3file.getId3v1Tag().getTitle();
                if (title == null || title.isBlank()) {
                    System.out.println("Тег названия трека не найден");
                } else {
                    System.out.println("Название трека: " + title);
                }
            } else {
                System.out.println("MP3 файл не содержит ID3 тегов");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении MP3 файла: " + e.getMessage());
        }
    }
}