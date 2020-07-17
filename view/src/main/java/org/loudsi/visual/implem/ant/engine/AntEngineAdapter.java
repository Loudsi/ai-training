package org.loudsi.visual.implem.ant.engine;

import org.loudsi.simulation.api.algo.genetic.IGeneticEngineRunnable;
import org.loudsi.simulation.implem.genetic.ant.config.AntEngineGeneticConfig;
import org.loudsi.simulation.implem.genetic.ant.context.AntContext;
import org.loudsi.visual.adapter.engine.AbstractEngineAdapter;
import org.loudsi.visual.jfx.renderer.IRenderer;
import org.springframework.stereotype.Component;

@Component
public class AntEngineAdapter extends AbstractEngineAdapter<AntContext, AntEngineGeneticConfig> {

    public AntEngineAdapter(IRenderer<AntContext> contextRender, IGeneticEngineRunnable<AntContext, AntEngineGeneticConfig> engine) {
        super(contextRender, engine);
    }
}
