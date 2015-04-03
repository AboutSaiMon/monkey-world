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

import org.oreilly.is.Agent;
import org.oreilly.is.Percept;

/**
 * Is the agent for the third type of the environment.
 *
 * @author Deep Blue Team
 */
public class ThirdAgent implements Agent {

    // This enum needs to know in which step we are.
    private enum CURRENTSTEP {
        INIT, FIND_BOX, FIND_BANANA, KEEP_BANANA, DESCEND, GO_TO_POSITION, BACK_BOX, BACK_AT_HOME, FINISH
    }

    private boolean alive = true;
    // Current step
    private CURRENTSTEP step;
    private int homePosition;
    private int initialBoxPosition;

    //How often the bananas moving.
    private int changesNumber;
    private int previousBananaPosition;
    private int positionToGo;

    private int MAGICNUMBER = 15;

    private int[] bananasPositionArray = new int[10];

    /**
     * Only invoke the super constructor and set the initial step.
     */
    public ThirdAgent() {
        super();
        step = CURRENTSTEP.INIT;
        changesNumber = 0;
        previousBananaPosition = -1;
    }

    public MonkeyAction execute(Percept percept) {
        MonkeyAction a = new MonkeyAction(ActionType.NO_OP);
        MonkeyPerception monkeyPerception;

        if (percept instanceof MonkeyPerception)
            monkeyPerception = (MonkeyPerception) percept;
        else {
            return a;
        }

        //Set previousBananaPosition if it isn't setted yet.
        if (previousBananaPosition == -1) {
            previousBananaPosition = monkeyPerception.getBananasBunch();
            bananasPositionArray[previousBananaPosition]++;
        }

        if (monkeyPerception.isAtHome() && step != CURRENTSTEP.FINISH) {
            a = new MonkeyAction(ActionType.GO_OUT);
            return a;
        }

        if (step == CURRENTSTEP.INIT) {
            homePosition = monkeyPerception.getMonkey();
            initialBoxPosition = monkeyPerception.getBox();
            step = CURRENTSTEP.FIND_BOX;
        } else if (step == CURRENTSTEP.FIND_BOX) {
            return findBox(monkeyPerception.getBox(), monkeyPerception.getMonkey());
        } else if (step == CURRENTSTEP.FIND_BANANA) {
            return findBanana(monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch());
        } else if (step == CURRENTSTEP.KEEP_BANANA) {
            return keepBanana(monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch());
        } else if (step == CURRENTSTEP.GO_TO_POSITION) {
            return goToPosition(monkeyPerception.getBox());
        } else if (step == CURRENTSTEP.DESCEND) {
            step = CURRENTSTEP.BACK_BOX;
            return new MonkeyAction(ActionType.DESCEND);
        } else if (step == CURRENTSTEP.BACK_BOX) {
            return backBox(monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch(), monkeyPerception.isGrabbed());
        } else if (step == CURRENTSTEP.BACK_AT_HOME) {
            return backHome(monkeyPerception.getMonkey());
        } else if (step == CURRENTSTEP.FINISH) {
            this.setAlive(false);
        }
        return a;
    }

    private MonkeyAction findBox(int boxPosition, int monkeyPosition) {
        MonkeyAction a = new MonkeyAction(ActionType.NO_OP);
        if (boxPosition == monkeyPosition) {
            step = CURRENTSTEP.FIND_BANANA;
            return a;
        }

        if (monkeyPosition < boxPosition) {
            a = new MonkeyAction(ActionType.GO_RIGHT);
        } else {
            a = new MonkeyAction(ActionType.GO_LEFT);
        }
        return a;
    }

    private MonkeyAction findBanana(int boxPosition, int monkeyPosition, int bananaPosition) {
        MonkeyAction a = null;

        if (previousBananaPosition != bananaPosition) {
            changesNumber++;
            bananasPositionArray[bananaPosition]++;
            previousBananaPosition = bananaPosition;
        }

        if (changesNumber == MAGICNUMBER) {
            int max = -1;
            int maxPosition = -1;
            for (int i = 0; i < bananasPositionArray.length; i++) {
                if (bananasPositionArray[i] > max) {
                    max = bananasPositionArray[i];
                    maxPosition = i;
                }
            }
            positionToGo = maxPosition;
            step = CURRENTSTEP.GO_TO_POSITION;
            a = new MonkeyAction(ActionType.NO_OP);
            return a;
        }

        if (boxPosition == bananaPosition) {
            a = new MonkeyAction(ActionType.CLIMB);
            step = CURRENTSTEP.KEEP_BANANA;
        } else {
            if (bananaPosition < boxPosition) {
                a = new MonkeyAction(ActionType.MOVE_BOX_LEFT);
            } else if (boxPosition < bananaPosition) {
                a = new MonkeyAction(ActionType.MOVE_BOX_RIGHT);
            }
        }
        return a;
    }

    private MonkeyAction keepBanana(int boxPosition, int monkeyPosition, int bananaPosition) {
        MonkeyAction a;

        if (boxPosition == monkeyPosition && monkeyPosition == bananaPosition) {
            a = new MonkeyAction(ActionType.GRAB);
            step = CURRENTSTEP.DESCEND;
        } else {
            if (changesNumber == MAGICNUMBER) {
                a = new MonkeyAction(ActionType.NO_OP);
            } else {
                a = new MonkeyAction(ActionType.DESCEND);
                step = CURRENTSTEP.FIND_BANANA;
            }
        }
        return a;
    }

    private MonkeyAction goToPosition(int boxPosition) {
        MonkeyAction a;
        if (boxPosition == positionToGo) {
            a = new MonkeyAction(ActionType.CLIMB);
            step = CURRENTSTEP.KEEP_BANANA;
            return a;
        } else {
            if (boxPosition > positionToGo) {
                a = new MonkeyAction(ActionType.MOVE_BOX_LEFT);
            } else {
                a = new MonkeyAction(ActionType.MOVE_BOX_RIGHT);
            }
        }
        return a;
    }

    private MonkeyAction backBox(int boxPosition, int monkeyPosition, int bananaPosition, boolean isGrabbed) {
        MonkeyAction a;

        if (!isGrabbed) {
            step = CURRENTSTEP.KEEP_BANANA;
        } else {
            if (boxPosition == initialBoxPosition) {
                step = CURRENTSTEP.BACK_AT_HOME;
            } else {
                if (boxPosition > initialBoxPosition) {
                    a = new MonkeyAction(ActionType.MOVE_BOX_LEFT);
                    return a;
                } else {
                    a = new MonkeyAction(ActionType.MOVE_BOX_RIGHT);
                    return a;
                }
            }
        }

        a = new MonkeyAction(ActionType.NO_OP);
        return a;
    }

    private MonkeyAction backHome(int monkeyPosition) {
        MonkeyAction a;

        if (monkeyPosition != homePosition) {
            if (monkeyPosition > homePosition) {
                a = new MonkeyAction(ActionType.GO_LEFT);
                return a;
            } else {
                a = new MonkeyAction(ActionType.GO_RIGHT);
                return a;
            }
        }

        step = CURRENTSTEP.FINISH;
        a = new MonkeyAction(ActionType.GO_HOME);
        return a;
    }

    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}