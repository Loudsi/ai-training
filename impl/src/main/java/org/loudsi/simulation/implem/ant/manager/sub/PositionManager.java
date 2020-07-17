package org.loudsi.simulation.implem.ant.manager.sub;

import org.loudsi.simulation.implem.ant.context.AntContext;
import org.loudsi.simulation.implem.util.Position;
import org.loudsi.simulation.implem.util.Vector;


public abstract class PositionManager {

    public static Position apply(Position initialPosition, Vector direction) {
        return new Position(initialPosition.getX() + direction.getX(), initialPosition.getY() + direction.getY());
    }

    public static boolean isPositionInTheWorld(Position newPosition) {
        return !(newPosition.getX() < 0)
                && !(newPosition.getY() < 0)
                && !(newPosition.getX() > AntContext.WIDTH)
                && !(newPosition.getY() > AntContext.LENGTH);
    }

    public static boolean isInside(Position pointPosition, Position circleCenter, double detectionRadius) {
        return Math.hypot(circleCenter.getX() - pointPosition.getX(), circleCenter.getY() - pointPosition.getY()) <= detectionRadius;
    }
}
