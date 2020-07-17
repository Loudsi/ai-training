package org.loudsi.visual.implem.ant;

import org.loudsi.simulation.api.ai.genetic.ConfigLoader;
import org.loudsi.simulation.api.engine.BaseRunnable;
import org.loudsi.simulation.api.engine.IGeneticAlgoRunnable;
import org.loudsi.simulation.api.training.trainer.tree.FileTreeTrainerRepository;
import org.loudsi.simulation.api.training.trainer.tree.TreeBasedEngineTrainer;
import org.loudsi.simulation.implem.ant.AntContextBuilder;
import org.loudsi.simulation.implem.ant.config.AntConfigGenerator;
import org.loudsi.simulation.implem.ant.config.AntEngineConfig;
import org.loudsi.simulation.implem.ant.context.AntContext;
import org.loudsi.simulation.implem.ant.manager.AntParametrizedGeneticManager;
import org.loudsi.simulation.implem.ant.trainer.AntFoodBasedParallelRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AntBeanConfiguration {


    @Bean
    protected IGeneticAlgoRunnable<AntContext, AntEngineConfig> buildEngine() {
        return new BaseRunnable<>(
                AntContextBuilder.buildAntContext(),
                ConfigLoader.loadFromResource("config/ant/config.json", AntEngineConfig.class),
                new AntParametrizedGeneticManager()
        );
    }

    @Bean
    protected TreeBasedEngineTrainer<AntContext, AntEngineConfig> trainer() {
        return new TreeBasedEngineTrainer<>(
                new AntParametrizedGeneticManager(),
                new AntConfigGenerator(),
                new AntFoodBasedParallelRunner(),
                new FileTreeTrainerRepository<>("C:\\Development\\sources\\simulation\\trainerSave"),
                AntContextBuilder.buildAntContext());
    }


}
