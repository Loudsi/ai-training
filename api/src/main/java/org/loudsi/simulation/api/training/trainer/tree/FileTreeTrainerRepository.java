package org.loudsi.simulation.api.training.trainer.tree;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.loudsi.common.tree.Node;
import org.loudsi.simulation.api.training.runner.LearningRunsResult;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileTreeTrainerRepository<Config> {

    public static final String BESTS_RESULTS_FOLDER = "\\bests";
    public static final String ALL_RESULTS_JSON_FILE = "\\allResults.json";


    private final File rootSaveFolder;
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).addMixIn(Node.class, NodeMixIn.class);

    public FileTreeTrainerRepository(String saveFolderPath) {
        rootSaveFolder = new File(saveFolderPath);
    }
    ;

    public void saveResults(LearningRunsResult<Config> bestRunResults, Node<LearningRunsResult<Config>> treeResult) {
        rootSaveFolder.mkdirs();
        final File savedBestFolder = new File(rootSaveFolder, BESTS_RESULTS_FOLDER);
        savedBestFolder.mkdirs();

        try (PrintWriter writer = new PrintWriter(savedBestFolder.getPath() + "\\result-" + bestRunResults.getScore() + "-" + System.currentTimeMillis() + ".json", StandardCharsets.UTF_8)) {
            objectMapper.writeValue(writer, bestRunResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(rootSaveFolder.getPath() + ALL_RESULTS_JSON_FILE, StandardCharsets.UTF_8)) {
            objectMapper.writeValue(writer, treeResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Results saved in " + rootSaveFolder.getPath());
    }

    public Node<LearningRunsResult<Config>> loadSavedTree() {
        if (rootSaveFolder.exists()) {
            final File saveFile = new File(rootSaveFolder, ALL_RESULTS_JSON_FILE);
            if (saveFile.exists()) {
                try (FileReader fileReader = new FileReader(saveFile)) {
                    return objectMapper.readValue(fileReader, new TypeReference<>() {
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public interface NodeMixIn {
        @JsonIgnore
        boolean isRoot();

        @JsonIgnore
        boolean isLeaf();
    }
}
