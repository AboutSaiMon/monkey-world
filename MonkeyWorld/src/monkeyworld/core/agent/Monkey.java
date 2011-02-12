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

import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Pattern;

import monkeyworld.core.Planner;

import org.oreilly.is.Agent;
import org.oreilly.is.Percept;

/**
 * It's the agent for the first type of the environment.
 * 
 * @author Deep Blue Team
 */
public class Monkey implements Agent {

	private boolean alive;
	private boolean firstStep;
	private LinkedList<ActionType> plan;

	/**
	 * Create a new monkey.
	 */
	public Monkey() {
		alive = true;
		firstStep = true;
		plan = new LinkedList<ActionType>();
	}

	@Override
	public MonkeyAction execute(Percept percept) {
		// if this method is called for the first time
		if (firstStep) {
			firstStep = false;
			// builds the plan
			buildPlan(percept);
		}
		MonkeyAction action = null;
		if (!plan.isEmpty()) {
			action = new MonkeyAction(plan.remove());
		} else {
			setAlive(false);
			action = new MonkeyAction(ActionType.NO_OP);
		}
		return action;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/*
	 * Builds the plan, retrieving the information from DLV.
	 */
	private void buildPlan(Percept percept) {
		// adds the first action
		plan.add(ActionType.GO_OUT);
		// create the first half of the plan, until the bananas bunch
		oneWayPlan(percept);
		// gets the first half of the plan reversed
		LinkedList<ActionType> reversedActions = getReversedActions();
		// adds the middle plan
		plan.add(ActionType.CLIMB);
		plan.add(ActionType.GRAB);
		plan.add(ActionType.DESCEND);
		// add to the plan the opposite action of the reversed plan
		for (ActionType action : reversedActions) {
			if (action.equals(ActionType.MOVE_BOX_LEFT)) {
				plan.add(ActionType.MOVE_BOX_RIGHT);
			} else if (action.equals(ActionType.MOVE_BOX_RIGHT)) {
				plan.add(ActionType.MOVE_BOX_LEFT);
			} else if (action.equals(ActionType.GO_LEFT)) {
				plan.add(ActionType.GO_RIGHT);
			} else if (action.equals(ActionType.GO_RIGHT)) {
				plan.add(ActionType.GO_LEFT);
			}
		}
		// adds the last action
		plan.add(ActionType.GO_HOME);
	}

	/*
	 * Creates the plan from home until the bananas bunch.
	 */
	private void oneWayPlan(Percept percept) {
		// retrieves the perception
		MonkeyPerception perception = (MonkeyPerception) percept;
		// gets the objects position from the perception
		int myPosition = perception.getMonkey();
		int boxPosition = perception.getBox();
		int bananasPosition = perception.getBananasBunch();
		// adds the actions until the box
		untilBox(myPosition, boxPosition);
		// adds the actions from the box until the bananas bunch
		untilBanana(boxPosition, bananasPosition);

	}

	/*
	 * Creates a sub plan from home until the box position.
	 */
	private void untilBox(int myPosition, int boxPosition) {
		// gets the first sub goal (from home until the box)
		LinkedList<String> actions = Planner.getPlanUntilBox(myPosition,
				boxPosition);
		int position;
		for (String action : actions) {
			position = getPosition(action);
			if (myPosition < position) {
				plan.add(ActionType.GO_RIGHT);
			} else if (position < myPosition) {
				plan.add(ActionType.GO_LEFT);
			}
		}
	}

	/*
	 * Creates a sub plan from the box until the bananas bunch
	 */
	private void untilBanana(int boxPosition, int bananasPosition) {
		// gets the second sub goal (move the box until the banana)
		LinkedList<String> actions = Planner.getPlanUntilBanana(boxPosition,
				bananasPosition);
		int position;
		for (String action : actions) {
			position = getPosition(action);
			if (boxPosition < position) {
				plan.add(ActionType.MOVE_BOX_RIGHT);
			} else if (position < boxPosition) {
				plan.add(ActionType.MOVE_BOX_LEFT);
			}
		}
	}

	/*
	 * Reverses the list of actions
	 */
	private LinkedList<ActionType> getReversedActions() {
		LinkedList<ActionType> temp = new LinkedList<ActionType>();
		temp.addAll(plan);
		Collections.reverse(temp);
		return temp;
	}

	/*
	 * Splits the string "action", retrieving the number between the brackets.
	 */
	private int getPosition(String action) {
		Pattern pattern = Pattern.compile("\\(|\\)");
		return Integer.parseInt(pattern.split(action)[1]);
	}

}
