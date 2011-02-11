/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package monkeyworld.core.environment;

/**
 * This class modify the internal state of the environment. It check if an
 * action can be performed. If yes, it performs the action.
 * 
 * @author Deep Blue Team
 */
public class EnvStatusModifier {

	private EnvStatus status;
	private int length;

	/**
	 * Creates a new modifier.
	 */
	public EnvStatusModifier() {
		status = new EnvStatus();
		length = status.getLength();
	}

	/**
	 * Create a new modifier, setting the environment length.
	 * 
	 * @param length
	 *            the environment length.
	 */
	public EnvStatusModifier(int length) {
		status = new EnvStatus(length);
		length = status.getLength();
	}

	/**
	 * Gets the environment status.
	 * 
	 * @return the environment status
	 */
	public EnvStatus getStatus() {
		return status;
	}

	/**
	 * Moves the monkey out of the home, after checking if it can do this
	 * action.
	 */
	public void goOut() {
		if (canGoOut()) {
			status.setAtHome(false);
		}
	}

	/**
	 * Moves the monkey toward home, after checking if it can do this action.
	 */
	public void goHome() {
		if (canGoHome()) {
			status.setAtHome(true);
		}
	}

	/**
	 * Moves the monkey one step to the left, after checking if it can do this
	 * action.
	 */
	public void goLeft() {
		if (canGoLeft()) {
			status.setMonkey(status.getMonkey() - 1);
		}
	}

	/**
	 * Moves the monkey one step to the right, after checking if it can do this
	 * action.
	 */
	public void goRight() {
		if (canGoRight()) {
			status.setMonkey(status.getMonkey() + 1);
		}
	}

	/**
	 * Moves the monkey and the box one step to the left, after checking if it
	 * can do this action.
	 */
	public void moveBoxLeft() {
		if (canMoveBoxLeft()) {
			status.setMonkey(status.getMonkey() - 1);
			status.setBox(status.getBox() - 1);
		}
	}

	/**
	 * Moves the monkey and the box one step to the right, after checking if it
	 * can do this action.
	 */
	public void moveBoxRight() {
		if (canMoveBoxRight()) {
			status.setMonkey(status.getMonkey() + 1);
			status.setBox(status.getBox() + 1);
		}
	}

	/**
	 * Climbs on the box, if it's possible.
	 */
	public void climb() {
		if (canClimb()) {
			status.setOnTheBox(true);
		}
	}

	public void descend() {
		if( canDescend() ) {
			status.setOnTheBox(false);
		}
	}

	/**
	 * Checks if the monkey can grab the bunch of bananas and then grab.
	 */
	public void grab() {
		if (canGrab()) {
			status.setGrabbed(true);
		}
	}

	/*
	 * True if the monkey is at home.
	 */
	private boolean canGoOut() {
		return status.isAtHome();
	}

	/*
	 * True if the monkey is adjacent to home.
	 */
	private boolean canGoHome() {
		int monkey = status.getMonkey();
		int home = status.getHome();
		return !status.isAtHome() && monkey == home;
	}

	/*
	 * True if the monkey position is greater than zero.
	 */
	private boolean canGoLeft() {
		int monkey = status.getMonkey();
		return !status.isAtHome() && monkey > 0;
	}

	/*
	 * True if the monkey position is lesser than "length - 1".
	 */
	private boolean canGoRight() {
		int monkey = status.getMonkey();
		return !status.isAtHome() && monkey < length - 1;
	}

	/*
	 * True if the monkey is in the same position of the box
	 * and if the box position is greater than zero.
	 */
	private boolean canMoveBoxLeft() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return !status.isAtHome() && monkey == box && box > 0;
	}

	/*
	 * True if the monkey is in the same position of the box
	 * and if the box position is lesser than "length - 1".
	 */
	private boolean canMoveBoxRight() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return !status.isAtHome() && monkey == box && box < length - 1;
	}

	/*
	 * True if the monkey is in the same position of the box and
	 * is not on top of the box.
	 */
	private boolean canClimb() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return !status.isAtHome() && monkey == box && !status.isOnTheBox();
	}
	
	/*
	 * True if the monkey is upon the box.
	 */
	private boolean canDescend() {
		return status.isOnTheBox();
	}
	
	/*
	 * True if the banana is not grabbed yet, the monkey is in the same
	 * position of the box bananas bunch and is on the box.
	 */
	private boolean canGrab() {
		int monkey = status.getMonkey();
		int bananasBunch = status.getBananasBunch();
		return status.isOnTheBox() && !status.isGrabbed() && monkey == bananasBunch;
	}

}