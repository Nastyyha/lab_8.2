package com.example.fileprocessor.modules.dir;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryListModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Вывод списка файлов в каталоге";
    }

    @Override
    public void process(File file) {
        File[] files = file.listFiles();
        if (files == null) {
            System.out.println("Каталог пуст или недоступен");
            return;
        }
        for (File f : files) {
            System.out.println(f.getName());
        }
    }
}