package com.example.fileprocessor.modules.image;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;

@Component
public class ImageFormatModule implements FileModule {

    @Override
    public boolean supports(File file) {
        String name = file.getName().toLowerCase();
        return file.isFile() && (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
    }

    @Override
    public String getDescription() {
        return "Вывод формата изображения";
    }

    @Override
    public void process(File file) throws Exception {
        String formatName = ImageIO.getImageReadersBySuffix(
                        file.getName().substring(file.getName().lastIndexOf('.') + 1))
                .next().getFormatName();
        System.out.println("Формат изображения: " + formatName);
    }
}