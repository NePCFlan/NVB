package nepcflan.nvb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigReader {
    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    private final File configFile = new File("Config.yaml");
    private Config config;

    public boolean existsConfig(boolean generateFile) {
        boolean result = configFile.exists();
        if (!result && generateFile) {
            try (InputStream original = ClassLoader.getSystemResourceAsStream("Config.yaml")) {
                Files.copy(original, configFile.toPath());
                result = true;
                log.info("The configuration file was not found, so a new file was created.");
                log.debug("Configuration file location: {}", configFile.getPath());
            } catch (IOException e) {
                result = false;
                log.error("The correct configuration file could not be retrieved from the executable.\n" +
                        "If you have a series of problems, please contact the developer.", e);
            }
        }
        return result;
    }

    public void reloadConfig() {
        try (FileInputStream configInput = new FileInputStream(configFile)) {
            config = MAPPER.readValue(configInput, Config.class);
            log.info("The configuration file has been successfully loaded.");
        } catch (FileNotFoundException e) {
            log.error("The configuration file could not be found. Do not delete the configuration file after starting the program.\n" +
                    "If you don't know what it is, please report it to the developer.", e);
        } catch (IOException e) {
            log.error("An error occurred while loading the configuration file.", e);
        }
    }

    public Config getConfig() {
        return config;
    }
}