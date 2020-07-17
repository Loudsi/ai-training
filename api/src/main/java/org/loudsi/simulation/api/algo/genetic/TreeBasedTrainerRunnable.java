package org.loudsi.simulation.api.algo.genetic;

import org.loudsi.common.RandomHelper;
import org.loudsi.common.tree.Node;
import org.loudsi.common.tree.NodeHelper;
import org.loudsi.simulation.api.runnables.AbstractControlledRunnable;
import org.loudsi.simulation.api.training.ITrainerRunnable;
import org.loudsi.simulation.api.training.LearningRunsResult;
import org.loudsi.simulation.api.training.FileTreeTrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeBasedTrainerRunnable<Context, Config> extends AbstractControlledRunnable implements ITrainerRunnable<Config, Node<LearningRunsResult<Config>>> {

    private final Logger logger = LoggerFactory.getLogger(TreeBasedTrainerRunnable.class);

    public static final int VIABLE_SCORE_RESULT_MULTIPLICATION = 3;

    private final IGeneticManager<Context, Config> manager;
    private final IGeneticConfigurationGenerator<Config> configurationGenerator;
    private final IGeneticTrainingRunner<Context, Config> trainingRunner;
    private final FileTreeTrainerRepository<Config> trainerRepository;
    private final Context context;

    private Node<LearningRunsResult<Config>> tree = null;
    private LearningRunsResult<Config> bestRun = null;

    private boolean resumePrevious = false;

    public TreeBasedTrainerRunnable(IGeneticManager<Context, Config> manager,
                                    IGeneticConfigurationGenerator<Config> configurationGenerator,
                                    IGeneticTrainingRunner<Context, Config> trainingRunner,
                                    FileTreeTrainerRepository<Config> trainerRepository,
                                    Context context) {
        this.manager = manager;
        this.configurationGenerator = configurationGenerator;
        this.trainingRunner = trainingRunner;
        this.trainerRepository = trainerRepository;
        this.context = context;
    }


    @Override
    public Node<LearningRunsResult<Config>> getIntermediaryResults() {
        return tree;
    }


    @Override
    protected void beforeLoop() {
        if (resumePrevious && trainerRepository != null) {
            tree = trainerRepository.loadSavedTree();
        }
        if (Objects.isNull(tree)) {
            tree = new Node<>();
        }
    }

    @Override
    protected void doRunnableLogic(int cycle) throws InterruptedException {

        final List<Node<LearningRunsResult<Config>>> nodes = NodeHelper.getAllNodes(tree);

        final List<Node<LearningRunsResult<Config>>> viableNodes = selectViableNodes(nodes, bestRun);

        logger.info(nodes.size() + " nodes founds, " + viableNodes.size() + " reliable");

        //TODO check with real time spend
        //viableNodes.parallelStream().forEach(viableNode -> addChild(viableNode, manager, context));
        viableNodes.forEach(viableNode -> addChild(viableNode, manager, context));

        bestRun = NodeHelper.findMax(tree, Comparator.comparing(LearningRunsResult::getScore));
        logger.info("Best Result is : " + bestRun.getScore());

        if (trainerRepository != null) {
            trainerRepository.saveResults(bestRun, tree);
        }

    }

    @Override
    public Config getBestTrainingResult() {
        if (bestRun != null) {
            return bestRun.getConfig();
        } else {
            return null;
        }
    }

    private List<Node<LearningRunsResult<Config>>> selectViableNodes(List<Node<LearningRunsResult<Config>>> nodes, LearningRunsResult<Config> bestResult) {
        if (bestResult != null) {
            //Handle Root
            final List<Node<LearningRunsResult<Config>>> viable = nodes.stream()
                    .filter(node -> !node.isRoot())
                    .filter(node -> node.getData().getScore() >= bestResult.getScore() / VIABLE_SCORE_RESULT_MULTIPLICATION)
                    .collect(Collectors.toList());

            //Add Root
            viable.addAll(nodes.stream()
                    .filter(Node::isRoot)
                    .filter(root -> 0 >= bestResult.getScore() / VIABLE_SCORE_RESULT_MULTIPLICATION)
                    .collect(Collectors.toList()));

            return viable;
        } else {
            return Collections.unmodifiableList(nodes);
        }

    }


    private void addChild(Node<LearningRunsResult<Config>> parent, IGeneticManager<Context, Config> manager, Context context) {
        if (parent.isRoot()) {
            parent.addChild(trainingRunner.run(context, configurationGenerator.generate(RandomHelper.randomIntInclusive(1, 100)), manager));
        } else {
            parent.addChild(trainingRunner.run(context, configurationGenerator.mutateFrom(parent.getData().getConfig(), RandomHelper.randomIntInclusive(1, 100)), manager));
            parent.addChild(trainingRunner.run(context, configurationGenerator.mutateFrom(parent.getData().getConfig(), RandomHelper.randomIntInclusive(1, 5)), manager));
        }
    }

    public void setResumePrevious(boolean resumePrevious) {
        this.resumePrevious = resumePrevious;
    }

    @Override
    public boolean isGoalReached() {
        return false;
    }
}
