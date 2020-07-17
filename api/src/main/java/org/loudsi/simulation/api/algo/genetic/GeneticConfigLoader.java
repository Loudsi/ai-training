package org.loudsi.simulation.api.algo.genetic;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.util.Objects;

public class GeneticConfigLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T loadFromResource(String resourcePath, Class<T> clazz) {
        try (FileReader fileReader = new FileReader(Objects.requireNonNull(GeneticConfigLoader.class.getClassLoader().getResource(resourcePath)).getFile())) {
            return objectMapper.readValue(fileReader, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load config from " + resourcePath, e);
        }
    }
}
