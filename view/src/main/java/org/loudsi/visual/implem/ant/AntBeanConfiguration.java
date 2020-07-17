package org.loudsi.visual.implem.ant;

import org.loudsi.simulation.api.algo.genetic.GeneticConfigLoader;
import org.loudsi.simulation.api.algo.genetic.IGeneticEngineRunnable;
import org.loudsi.simulation.api.runnables.BaseRunnable;
import org.loudsi.simulation.api.training.FileTreeTrainerRepository;
import org.loudsi.simulation.api.algo.genetic.TreeBasedTrainerRunnable;
import org.loudsi.simulation.implem.genetic.ant.AntContextBuilder;
import org.loudsi.simulation.implem.genetic.ant.config.AntConfigGenerator;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.simulation.implem.genetic.ant.context.AntContext;
import org.loudsi.simulation.implem.genetic.ant.manager.AntParametrizedGeneticManager;
import org.loudsi.simulation.implem.genetic.ant.trainer.AntFoodBasedParallelRunnerGenetic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AntBeanConfiguration {


    @Bean
    protected IGeneticEngineRunnable<AntContext, AntEngineGeneticConfig> buildEngine() {
        return new BaseRunnable<>(
                AntContextBuilder.buildAntContext(),
                GeneticConfigLoader.loadFromResource("config/ant/config.json", AntEngineGeneticConfig.class),
                new AntParametrizedGeneticManager()
        );
    }

    @Bean
    protected TreeBasedTrainerRunnable<AntContext, AntEngineGeneticConfig> trainer() {
        return new TreeBasedTrainerRunnable<>(
                new AntParametrizedGeneticManager(),
                new AntConfigGenerator(),
                new AntFoodBasedParallelRunnerGenetic(),
                new FileTreeTrainerRepository<>("C:\\Development\\sources\\simulation\\trainerSave"),
                AntContextBuilder.buildAntContext());
    }


}
