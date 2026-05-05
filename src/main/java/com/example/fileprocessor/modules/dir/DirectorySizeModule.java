package com.example.fileprocessor.modules.dir;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectorySizeModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Подсчет общего размера всех файлов в каталоге";
    }

    @Override
    public void process(File file) {
        long size = getDirectorySize(file);
        System.out.printf("Общий размер файлов: %d байт\n", size);
    }

    private long getDirectorySize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();
        if (files == null) return 0;

        for (File f : files) {
            if (f.isFile()) {
                size += f.length();
            } else if (f.isDirectory()) {
                size += getDirectorySize(f);
            }
        }
        return size;
    }
}