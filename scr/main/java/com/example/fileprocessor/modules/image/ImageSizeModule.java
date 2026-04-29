package com.example.fileprocessor.modules.image;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class ImageSizeModule implements FileModule {

    @Override
    public boolean supports(File file) {
        String name = file.getName().toLowerCase();
        return file.isFile() && (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
    }

    @Override
    public String getDescription() {
        return "Вывод размера изображения (ширина x высота)";
    }

    @Override
    public void process(File file) throws Exception {
        BufferedImage img = ImageIO.read(file);
        if (img == null) {
            System.out.println("Не удалось прочитать изображение.");
            return;
        }
        System.out.printf("Размер изображения: %d x %d\n", img.getWidth(), img.getHeight());
    }
}