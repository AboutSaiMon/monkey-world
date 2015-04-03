/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.core.agent;

import org.oreilly.is.Action;

/**
 * Defines a specific action that the monkey can perform.
 *
 * @author Deep Blue Team
 */
public class MonkeyAction implements Action {

    private boolean goOut;
    private boolean goHome;
    private boolean goLeft;
    private boolean goRight;
    private boolean moveBoxLeft;
    private boolean moveBoxRight;
    private boolean climb;
    private boolean descend;
    private boolean grab;
    private boolean noOp;
    private ActionType actionType;

    /**
     * Creates an action.
     *
     * @param type the action type
     */
    public MonkeyAction(ActionType type) {
        actionType = type;
        reset();
        setAction(actionType);
    }

    /*
     * Sets all boolean variables to "false".
     */
    private void reset() {
        goOut = false;
        goHome = false;
        goLeft = false;
        goRight = false;
        moveBoxLeft = false;
        moveBoxRight = false;
        climb = false;
        descend = false;
        grab = false;
        noOp = false;
    }

    /*
     * Sets the specified action to true
     */
    private void setAction(ActionType type) {
        if (type.equals(ActionType.GO_OUT)) {
            goOut = true;
        } else if (type.equals(ActionType.GO_HOME)) {
            goHome = true;
        } else if (type.equals(ActionType.GO_LEFT)) {
            goLeft = true;
        } else if (type.equals(ActionType.GO_RIGHT)) {
            goRight = true;
        } else if (type.equals(ActionType.MOVE_BOX_LEFT)) {
            moveBoxLeft = true;
        } else if (type.equals(ActionType.MOVE_BOX_RIGHT)) {
            moveBoxRight = true;
        } else if (type.equals(ActionType.CLIMB)) {
            climb = true;
        } else if (type.equals(ActionType.DESCEND)) {
            descend = true;
        } else if (type.equals(ActionType.GRAB)) {
            grab = true;
        } else if (type.equals(ActionType.NO_OP)) {
            noOp = true;
        }
    }

    public boolean isNoOp() {
        return noOp;
    }

    /**
     * @return true if this is a <b>GO_OUT</b> action
     */
    public boolean isGoOut() {
        return goOut;
    }

    /**
     * @return true if this is a <b>GO_HOME</b> action.
     */
    public boolean isGoHome() {
        return goHome;
    }

    /**
     * @return true if this is a <b>MOVE_LEFT</b> action.
     */
    public boolean isGoLeft() {
        return goLeft;
    }

    /**
     * @return true if this is a <b>MOVE_RIGHT</b> action.
     */
    public boolean isGoRight() {
        return goRight;
    }

    /**
     * @return true if this is a <b>MOVE_BOX_LEFT</b> action.
     */
    public boolean isMoveBoxLeft() {
        return moveBoxLeft;
    }

    /**
     * @return true if this is a <b>MOVE_BOX_RIGHT</b> action.
     */
    public boolean isMoveBoxRight() {
        return moveBoxRight;
    }

    /**
     * @return true if this is a <b>CLIMB</b> action.
     */
    public boolean isClimb() {
        return climb;
    }

    /**
     * @return true if this is a <b>DESCEND</b> action.
     */
    public boolean isDescend() {
        return descend;
    }

    /**
     * @return true if this is a <b>GRAB</b> action.
     */
    public boolean isGrab() {
        return grab;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MonkeyAction other = (MonkeyAction) obj;
        if (actionType != other.actionType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return actionType.toString();
    }

}