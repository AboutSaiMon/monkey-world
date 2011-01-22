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

import java.util.LinkedList;

import org.oreilly.is.Agent;
import org.oreilly.is.Percept;

/**
 *
 * @author Deep Blue Team
 */
public class Monkey implements Agent {

	private boolean alive;
	private LinkedList<MonkeyAction> actions;
	
	/**
	 * 
	 */
	public Monkey() {
		alive = true;
		actions = new LinkedList<MonkeyAction>();
		actions.add(new MonkeyAction(ActionType.GO_OUT));
		actions.add(new MonkeyAction(ActionType.MOVE_RIGHT));
		actions.add(new MonkeyAction(ActionType.MOVE_RIGHT));
		actions.add(new MonkeyAction(ActionType.GRAB));
		actions.add(new MonkeyAction(ActionType.MOVE_LEFT));
		actions.add(new MonkeyAction(ActionType.MOVE_LEFT));
		actions.add(new MonkeyAction(ActionType.GO_HOME));
	}
	
	@Override
	public MonkeyAction execute(Percept percept) {
		MonkeyAction action = actions.remove();
		if( actions.isEmpty() ) {
			setAlive(false);
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

}
