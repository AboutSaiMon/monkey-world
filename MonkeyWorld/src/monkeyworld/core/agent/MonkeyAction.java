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
package monkeyworld.core.agent;

import org.oreilly.is.Action;

/**
 * Defines a specific action the monkey has to perform.
 * 
 * @author Deep Blue Team
 */
public class MonkeyAction implements Action {

	private boolean noOp;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean push;
	private boolean pull;
	private boolean climb;
	private boolean grab;
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
		noOp = false;
		moveLeft = false;
		moveRight = false;
		push = false;
		pull = false;
		climb = false;
		grab = false;
	}

	/*
	 * Sets the specified action to true
	 */
	private void setAction(ActionType type) {
		if (type.equals(ActionType.NO_OP)) {
			noOp = true;
		} else if (type.equals(ActionType.MOVE_LEFT)) {
			moveLeft = true;
		} else if (type.equals(ActionType.MOVE_RIGHT)) {
			moveRight = true;
		} else if (type.equals(ActionType.PUSH)) {
			push = true;
		} else if (type.equals(ActionType.PULL)) {
			pull = true;
		} else if (type.equals(ActionType.CLIMB)) {
			climb = true;
		} else if (type.equals(ActionType.GRAB)) {
			grab = true;
		}
	}
	
	@Override
	public boolean isNoOp() {
		return noOp;
	}

	/**
	 * @return the moveLeft
	 */
	public boolean isMoveLeft() {
		return moveLeft;
	}

	/**
	 * @return the moveRight
	 */
	public boolean isMoveRight() {
		return moveRight;
	}

	/**
	 * @return the push
	 */
	public boolean isPush() {
		return push;
	}

	/**
	 * @return the pull
	 */
	public boolean isPull() {
		return pull;
	}

	/**
	 * @return the climb
	 */
	public boolean isClimb() {
		return climb;
	}

	/**
	 * @return the grab
	 */
	public boolean isGrab() {
		return grab;
	}

}
