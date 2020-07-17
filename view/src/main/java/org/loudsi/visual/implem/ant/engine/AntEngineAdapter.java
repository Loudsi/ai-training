package org.loudsi.visual.implem.ant.engine;

import org.loudsi.simulation.api.engine.IGeneticAlgoRunnable;
import org.loudsi.simulation.implem.ant.config.AntEngineConfig;
import org.loudsi.simulation.implem.ant.context.AntContext;
import org.loudsi.visual.adapter.engine.AbstractEngineAdapter;
import org.loudsi.visual.jfx.renderer.IRenderer;
import org.springframework.stereotype.Component;

@Component
public class AntEngineAdapter extends AbstractEngineAdapter<AntContext, AntEngineConfig> {

    public AntEngineAdapter(IRenderer<AntContext> contextRender, IGeneticAlgoRunnable<AntContext, AntEngineConfig> engine) {
        super(contextRender, engine);
    }
}
