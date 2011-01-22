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
	 * Checks and moves the monkey out of the home.
	 */
	public void goOut() {
		if (canGoOut()) {
			status.setMonkey(status.getMonkey() - length - 1);
		}
	}

	/**
	 * Checks and moves the monkey toward home.
	 */
	public void goHome() {
		if (canGoHome()) {
			status.setMonkey(status.getMonkey() + length + 1);
		}
	}

	/**
	 * Checks and moves the monkey one step to the left.
	 */
	public void moveLeft() {
		if (canMoveLeft()) {
			status.setMonkey(status.getMonkey() - 1);
		}
	}

	/**
	 * Check and moves the monkey one step to the right.
	 */
	public void moveRight() {
		if (canMoveRight()) {
			status.setMonkey(status.getMonkey() + 1);
		}
	}

	/*
	 * Moves the monkey and the box one step to the left.
	 */
	private void left() {
		status.setMonkey(status.getMonkey() - 1);
		status.setBox(status.getBox() - 1);
	}

	/*
	 * Moves the monkey and the box one step to the right. 
	 */
	private void right() {
		status.setMonkey(status.getMonkey() + 1);
		status.setBox(status.getBox() + 1);
	}

	/**
	 * Checks and pushes the box to the left.
	 */
	public void pushLeft() {
		if (canPushLeft()) {
			left();
		}
	}

	/**
	 * Checks and pushes the box to the right.
	 */
	public void pushRight() {
		if (canPushRight()) {
			right();
		}
	}

	/**
	 * Check and pulls the box to the left.
	 */
	public void pullLeft() {
		if (canPullLeft()) {
			left();
		}
	}

	/**
	 * Check and pulls the box to the right.
	 */
	public void pullRight() {
		if (canPullRight()) {
			right();
		}
	}

	/**
	 * Checks if the monkey can grab the bunch of bananas and then grab. 
	 */
	public void grab() {
		if (canGrab()) {
			status.grabBananasBunch();
		}
	}

	/*
	 * True if the monkey can go out.
	 */
	private boolean canGoOut() {
		int monkey = status.getMonkey();
		int home = status.getHome();
		return monkey == home;
	}

	/*
	 * True if the monkey can go home.
	 */
	private boolean canGoHome() {
		int monkey = status.getMonkey();
		int home = status.getHome();
		return monkey + length + 1 == home;
	}

	/*
	 * True if the monkey can go left.
	 */
	private boolean canMoveLeft() {
		int monkey = status.getMonkey();
		return monkey > 0 && monkey < length;
	}

	/*
	 * True if the monkey can go right.
	 */
	private boolean canMoveRight() {
		int monkey = status.getMonkey();
		return monkey >= 0 && monkey < length - 1;
	}

	/*
	 * True if the monkey can push the box to the left.
	 */
	private boolean canPushLeft() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return box == monkey - 1 && box > 0;
	}

	/*
	 * True if the monkey can push the box to the right.
	 */
	private boolean canPushRight() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return box == monkey + 1 && box < length - 1;
	}

	/*
	 * True if the monkey can pull the box to the left.
	 */
	private boolean canPullLeft() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return monkey == box - 1 && monkey > 0;
	}

	/*
	 * True if the monkey can pull the box to the right.
	 */
	private boolean canPullRight() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return monkey == box + 1 && monkey < length - 1;
	}

	/*
	 * True if the monkey can grab the bunch of bananas.
	 */
	private boolean canGrab() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		int bananasBunch = status.getBananasBunch();
		boolean grabbed = status.isGrabbed();
		return !grabbed && monkey == box && monkey == bananasBunch;
	}

}