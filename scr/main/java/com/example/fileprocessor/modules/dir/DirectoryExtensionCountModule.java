package com.example.fileprocessor.modules.dir;

import com.example.fileprocessor.modules.FileModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class DirectoryExtensionCountModule implements FileModule {

    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Подсчет количества файлов по расширениям в каталоге";
    }

    @Override
    public void process(File file) {
        Map<String, Integer> extCount = new HashMap<>();
        countExtensions(file, extCount);

        extCount.forEach((ext, count) -> System.out.printf("%s: %d\n", ext, count));
    }

    private void countExtensions(File dir, Map<String, Integer> extCount) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.isFile()) {
                String name = f.getName();
                int dotIndex = name.lastIndexOf('.');
                String ext = (dotIndex >= 0) ? name.substring(dotIndex + 1).toLowerCase() : "no_ext";
                extCount.put(ext, extCount.getOrDefault(ext, 0) + 1);
            } else if (f.isDirectory()) {
                countExtensions(f, extCount);
            }
        }
    }
}