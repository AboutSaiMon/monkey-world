/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.core.environment;

import org.oreilly.is.Agent;
import org.oreilly.is.Environment;
import spa.simone.monkeyworld.core.agent.MonkeyAction;
import spa.simone.monkeyworld.core.agent.MonkeyPerception;
import spa.simone.monkeyworld.gui.Penalty;

/**
 * This class is the environment.
 *
 * @author Deep Blue Team
 */
public class Laboratory implements Environment {

    private Agent monkey;
    private EnvType envType;
    private EnvStatus envStatus;
    private EnvStatusModifier envModifier;
    private boolean staticEnv = false;
    private boolean dynamicEnv = false;
    private boolean userDefinedEnv = false;
    private int intervalTime = 2;
    private boolean invisible = false;
    double goal;

    /**
     * Creates a new environment, which is passed a reference to the agent.
     *
     * @param monkey the agent
     */
    public Laboratory(Agent monkey) {
        this(monkey, EnvType.STATIC);
    }

    /**
     * Creates a new environment, which is passed a reference to the agent and
     * the environment type.
     *
     * @param monkey  the agent.
     * @param envType the environment type. It can be:
     *                <ul>
     *                <li>EnvType.STATIC</li>
     *                <li>EnvType.DYNAMIC</li>
     *                <li>EnvType.USER_DEFINED</li>
     *                <ul>
     */
    public Laboratory(Agent monkey, EnvType envType) {
        this.monkey = monkey;
        this.envType = envType;
        envModifier = new EnvStatusModifier();
        envStatus = envModifier.getStatus();
        goal = 0;
        if (this.envType.equals(EnvType.STATIC)) {
            staticEnv = true;
        } else if (this.envType.equals(EnvType.DYNAMIC)) {
            dynamicEnv = true;
        } else if (this.envType.equals(EnvType.USER_DEFINED)) {
            userDefinedEnv = true;
        }
    }

    /**
     * Set the time interval during which the bunch of bananas will remain
     * motionless. After this time, it will set a new location.
     *
     * @param time the interval time.
     */
    public void setIntervalTime(int time) {
        this.intervalTime = time;
    }

    /**
     * Get the time interval during which the bunch of bananas will remain
     * motionless. After this time, it will set a new location.
     *
     * @return the interval time
     */
    public int getIntervalTime() {
        return this.intervalTime;
    }

    /**
     * Tells if the environment type is dynamic and the bananas bunch position
     * is changed by the user.
     *
     * @return true if the environment type is user defined
     */
    public boolean isUserDefined() {
        return userDefinedEnv;
    }

    /**
     * Tells whether the environment type is static or not.
     *
     * @return true if the environment is static
     */
    public boolean isStatic() {
        return staticEnv;
    }

    /**
     * Tells whether the environment type is dynamic or not.
     *
     * @return true if the environment is dynamic
     */
    public boolean isDynamic() {
        return dynamicEnv;
    }

    /**
     * Gets the environment length.
     *
     * @return the length
     */
    public int getLength() {
        return envStatus.getLength();
    }

    /**
     * Tells if the monkey is at home or not.
     *
     * @return true if the monkey is at home
     */
    public boolean isMonkeyAtHome() {
        return envStatus.isAtHome();
    }

    /**
     * Tells if the monkey is invisible, or not.
     *
     * @return true if the monkey is invisible
     */
    public boolean isInvisible() {
        return invisible;
    }

    /**
     * Sets the invisibility to the monkey.
     *
     * @param invisible true to set the invisibility
     */
    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    /**
     * Sets the bananas bunch position.
     *
     * @param position the position of the bananas bunch
     */
    public void setBananasBunch(int position) {
        envStatus.setBananasBunch(position);
    }

    /**
     * Gets the position of the bananas bunch.
     *
     * @return the bananas bunch position
     */
    public int getBananasBunch() {
        return envStatus.getBananasBunch();
    }

    /**
     * Tells whether the bananas bunch is grabbed or not.
     *
     * @return true if the bananas bunch was grabbed
     */
    public boolean isGrabbed() {
        return envStatus.isGrabbed();
    }

    /**
     * Tells if the monkey is on the box or not.
     *
     * @return true if the monkey is on the box
     */
    public boolean isOnTheBox() {
        return envStatus.isOnTheBox();
    }

    /**
     * Sets the position of the box.
     *
     * @param position the box position
     */
    public void setBox(int position) {
        envStatus.setBox(position);
    }

    /**
     * Gets the position of the box.
     *
     * @return the box position
     */
    public int getBox() {
        return envStatus.getBox();
    }

    /**
     * Sets the position of the home.
     *
     * @param position the home position
     */
    public void setHome(int position) {
        envStatus.setHome(position);
    }

    /**
     * Gets the position of the home.
     *
     * @return the home position
     */
    public int getHome() {
        return envStatus.getHome();
    }

    /**
     * Gets the position of the monkey.
     *
     * @return the monkey position
     */
    public int getMonkey() {
        return envStatus.getMonkey();
    }

    @Override
    public boolean isDone() {
        return !monkey.isAlive();
    }

    /**
     * Adds the score to the total.
     *
     * @param penalty the score tu sum
     */
    public void penalizeWity(int penalty) {
        envStatus.penalizeWith(penalty);
    }

    @Override
    public void step() {
        MonkeyPerception perception = new MonkeyPerception(envStatus);
        MonkeyAction action = (MonkeyAction) monkey.execute(perception);
        if (action.isGoOut()) {
            envModifier.goOut();
        } else if (action.isGoHome()) {
            envModifier.goHome();
        } else if (action.isGoLeft()) {
            envModifier.goLeft();
        } else if (action.isGoRight()) {
            envModifier.goRight();
        } else if (action.isMoveBoxLeft()) {
            envModifier.moveBoxLeft();
        } else if (action.isMoveBoxRight()) {
            envModifier.moveBoxRight();
        } else if (action.isClimb()) {
            envModifier.climb();
        } else if (action.isDescend()) {
            envModifier.descend();
        } else if (action.isGrab()) {
            envModifier.grab();
        }
        envStatus.penalizeWith(Penalty.WEIGHT);
    }

    @Override
    public void step(int n) {
        for (int i = 0; i < n && !isDone(); i++) {
            step();
            sleep();
        }
    }

    @Override
    public void stepUntilDone() {
        while (!isDone()) {
            step();
            sleep();
        }
    }

    @Override
    public double getPerformanceMeasure() {
        if (isGrabbed() && goal == 0) {
            goal = 1;
        }
        int penalty = envStatus.getPenalty();
        if (penalty == 0) {
            return 0;
        } else {
            return goal / envStatus.getPenalty() * 100;
        }
    }

    /*
     * This method may be invocated if
     */
    private MonkeyPerception genPerception() {
        MonkeyPerception perception = new MonkeyPerception();
        perception.setBananasBunch(envStatus.getBananasBunch());
        perception.setBox(envStatus.getBox());
        perception.setHome(envStatus.getHome());
        perception.setMonkey(envStatus.getMonkey());
        perception.setGrabbed(envStatus.isGrabbed());
        perception.setAtHome(envStatus.isAtHome());
        perception.setOnTheBox(envStatus.isOnTheBox());
        return perception;
    }

    private void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}