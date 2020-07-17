package org.loudsi.visual.implem.ant.trainer;

import org.loudsi.common.tree.Node;
import org.loudsi.simulation.api.training.ITrainerRunnable;
import org.loudsi.simulation.api.training.LearningRunsResult;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.visual.adapter.trainer.AbstractTrainerAdapter;
import org.loudsi.visual.jfx.renderer.IRenderer;
import org.springframework.stereotype.Component;

@Component
public class AntTrainerAdapter extends AbstractTrainerAdapter<AntEngineGeneticConfig, Node<LearningRunsResult<AntEngineGeneticConfig>>> {

    protected AntTrainerAdapter(ITrainerRunnable<AntEngineGeneticConfig, Node<LearningRunsResult<AntEngineGeneticConfig>>> engineTrainer, IRenderer<Node<LearningRunsResult<AntEngineGeneticConfig>>> intermediaryResultRenderer) {
        super(engineTrainer, intermediaryResultRenderer);
    }
}
