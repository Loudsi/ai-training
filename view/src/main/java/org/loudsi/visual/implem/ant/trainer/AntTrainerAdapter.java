package org.loudsi.visual.implem.ant.trainer;

import org.loudsi.common.tree.Node;
import org.loudsi.simulation.api.training.IEngineTrainer;
import org.loudsi.simulation.api.training.runner.LearningRunsResult;
import org.loudsi.simulation.implem.ant.config.AntEngineConfig;
import org.loudsi.visual.adapter.trainer.AbstractTrainerAdapter;
import org.loudsi.visual.jfx.renderer.IRenderer;
import org.springframework.stereotype.Component;

@Component
public class AntTrainerAdapter extends AbstractTrainerAdapter<AntEngineConfig, Node<LearningRunsResult<AntEngineConfig>>> {

    protected AntTrainerAdapter(IEngineTrainer<AntEngineConfig, Node<LearningRunsResult<AntEngineConfig>>> engineTrainer, IRenderer<Node<LearningRunsResult<AntEngineConfig>>> intermediaryResultRenderer) {
        super(engineTrainer, intermediaryResultRenderer);
    }
}
