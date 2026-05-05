package com.example.fileprocessor.modules.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageExifModule implements FileModule {

    @Override
    public boolean supports(File file) {
        String name = file.getName().toLowerCase();
        return file.isFile() && (name.endsWith(".jpg") || name.endsWith(".jpeg"));
    }

    @Override
    public String getDescription() {
        return "Вывод EXIF-информации изображения";
    }

    @Override
    public void process(File file) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.printf("%s: %s\n", tag.getTagName(), tag.getDescription());
            }
        }
    }
}