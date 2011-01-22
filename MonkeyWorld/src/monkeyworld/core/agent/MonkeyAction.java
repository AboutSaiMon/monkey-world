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
 * Defines a specific action that the monkey can perform.
 * 
 * @author Deep Blue Team
 */
public class MonkeyAction implements Action {

	private boolean goOut;
	private boolean goHome;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean pushLeft;
	private boolean pushRight;
	private boolean pullLeft;
	private boolean pullRight;
	private boolean grab;
	private boolean noOp;
	private ActionType actionType;

	/**
	 * Creates an action.
	 * 
	 * @param type
	 *            the action type
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
		moveLeft = false;
		moveRight = false;
		pushLeft = false;
		pushRight = false;
		pullLeft = false;
		pullRight = false;
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
		} else if (type.equals(ActionType.MOVE_LEFT)) {
			moveLeft = true;
		} else if (type.equals(ActionType.MOVE_RIGHT)) {
			moveRight = true;
		} else if (type.equals(ActionType.PUSH_LEFT)) {
			pushLeft = true;
		} else if (type.equals(ActionType.PUSH_RIGHT)) {
			pushRight = true;
		} else if( type.equals(ActionType.PULL_LEFT)) {
			pullLeft = true;
		} else if( type.equals(ActionType.PULL_RIGHT)) {
			pullRight = true;
		} else if (type.equals(ActionType.GRAB)) {
			grab = true;
		} else if (type.equals(ActionType.NO_OP)) {
			noOp = true;
		}
	}

	@Override
	public boolean isNoOp() {
		return noOp;
	}

	/**
	 * @return true if this is a <b>GO_OUT</b> Action.
	 */
	public boolean isGoOut() {
		return goOut;
	}

	/**
	 * @return true if this is a <b>GO_HOME</b> Action.
	 */
	public boolean isGoHome() {
		return goHome;
	}

	/**
	 * @return true if this is a <b>MOVE_LEFT</b> Action.
	 */
	public boolean isMoveLeft() {
		return moveLeft;
	}

	/**
	 * @return true if this is a <b>MOVE_RIGHT</b> Action.
	 */
	public boolean isMoveRight() {
		return moveRight;
	}

	/**
	 * @return true if this is a <b>PUSH_LEFT</b> Action.
	 */
	public boolean isPushLeft() {
		return pushLeft;
	}

	/**
	 * @return true if this is a <b>PUSH_RIGHT</b> Action.
	 */
	public boolean isPushRight() {
		return pushRight;
	}

	/**
	 * @return true if this is a <b>PULL_LEFT</b> Action.
	 */
	public boolean isPullLeft() {
		return pullLeft;
	}

	/**
	 * @return true if this is a <b>PULL_RIGHT</b> Action.
	 */
	public boolean isPullRight() {
		return pullRight;
	}

	/**
	 * @return true if this is a <b>GRAB</b> Action.
	 */
	public boolean isGrab() {
		return grab;
	}
	
}
