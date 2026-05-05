package com.example.fileprocessor.modules;

import java.io.File;

public interface FileModule {
    boolean supports(File file);
    String getDescription();
    void process(File file) throws Exception;
}