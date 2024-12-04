package com.matthewperiut.entris.config;

import com.matthewperiut.entris.Entris;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class EntrisConfig {
    private static Path configPath;
    private static final String CONFIG_FOLDER_NAME = "entris";
    private static final String CONFIG_FILE_NAME = "config.txt";
    private static final String DEFAULT_CONTENTS = "pointsPerEnchant=1000\nsecondsPerLevel=6\nallowNormalEnchanting=true";
    private static final String POINTS_KEY = "pointsPerEnchant";
    private static final String SECONDS_KEY = "secondsPerLevel";
    private static final String ENCHANTING_KEY = "allowNormalEnchanting";

    public static int serverPointsPerEnchant = 1000;
    public static int serverSecondsPerLevel = 6;
    public static boolean serverAllowNormalEnchanting = true;

    public static void init(Path configPath) {
        try {
            EntrisConfig.configPath = configPath;
            Path configFolderPath = EntrisConfig.configPath.resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);

            if (!Files.exists(configFolderPath)) {
                Files.createDirectories(configFolderPath);
            }

            if (!Files.exists(configFilePath)) {
                Files.writeString(configFilePath, DEFAULT_CONTENTS, StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPointsPerEnchant() {
        if (!Entris.localConfig) {
            return EntrisConfig.serverPointsPerEnchant;
        }

        try {
            Path configFolderPath = EntrisConfig.configPath.resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);
            return readIntFromFile(configFilePath, POINTS_KEY, 1000);
        } catch (IOException e) {
            e.printStackTrace();
            return 1000; // Fallback to default
        }
    }

    public static int getSecondsPerLevel() {
        if (!Entris.localConfig) {
            return EntrisConfig.serverSecondsPerLevel;
        }

        try {
            Path configFolderPath = EntrisConfig.configPath.resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);
            return readIntFromFile(configFilePath, SECONDS_KEY, 6);
        } catch (IOException e) {
            e.printStackTrace();
            return 6; // Fallback to default
        }
    }

    public static boolean getAllowNormalEnchanting() {
        if (!Entris.localConfig) {
            return EntrisConfig.serverAllowNormalEnchanting;
        }

        try {
            Path configFolderPath = EntrisConfig.configPath.resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);
            return readBooleanFromFile(configFilePath, ENCHANTING_KEY, true);
        } catch (IOException e) {
            e.printStackTrace();
            return true; // Fallback to default
        }
    }

    public static void setPointsPerEnchant(int points) {
        updateConfig(POINTS_KEY, String.valueOf(points));
    }

    public static void setSecondsPerLevel(int seconds) {
        updateConfig(SECONDS_KEY, String.valueOf(seconds));
    }

    public static void setAllowNormalEnchanting(boolean allow) {
        updateConfig(ENCHANTING_KEY, String.valueOf(allow));
    }

    private static int readIntFromFile(Path configFilePath, String key, int defaultValue) throws IOException {
        Optional<String> line = Files.lines(configFilePath).filter(l -> l.startsWith(key + "=")).findFirst();
        if (line.isPresent()) {
            String value = line.get().split("=")[1].trim();
            return Integer.parseInt(value);
        } else {
            return defaultValue;
        }
    }

    private static boolean readBooleanFromFile(Path configFilePath, String key, boolean defaultValue) throws IOException {
        Optional<String> line = Files.lines(configFilePath).filter(l -> l.startsWith(key + "=")).findFirst();
        if (line.isPresent()) {
            String value = line.get().split("=")[1].trim();
            return Boolean.parseBoolean(value);
        } else {
            return defaultValue;
        }
    }

    private static void updateConfig(String key, String value) {
        try {
            Path configFolderPath = EntrisConfig.configPath.resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);

            if (!Files.exists(configFilePath)) {
                Files.writeString(configFilePath, DEFAULT_CONTENTS, StandardOpenOption.CREATE_NEW);
            }

            StringBuilder updatedContents = new StringBuilder();
            Files.lines(configFilePath).forEach(line -> {
                if (line.startsWith(key + "=")) {
                    updatedContents.append(key).append("=").append(value).append("\n");
                } else {
                    updatedContents.append(line).append("\n");
                }
            });

            if (!updatedContents.toString().contains(key + "=")) {
                updatedContents.append(key).append("=").append(value).append("\n");
            }

            Files.writeString(configFilePath, updatedContents.toString(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
