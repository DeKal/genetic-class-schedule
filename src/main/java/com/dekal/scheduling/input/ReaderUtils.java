package com.dekal.scheduling.input;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderUtils {
    private static final String COMMA_DELIMITER = ",";

    protected File getFileFromResources(String fileName) throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    List<List<String>> read(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try {
            File file = getFileFromResources(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
