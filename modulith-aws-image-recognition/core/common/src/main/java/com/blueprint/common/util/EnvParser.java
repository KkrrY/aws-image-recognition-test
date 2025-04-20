package com.blueprint.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvParser {

    public static Map<String, String> parseEnvFile(String filePath) {
        Map<String, String> envMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split the line into key and value
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    envMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return envMap;
    }
}
