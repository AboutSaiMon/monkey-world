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

import org.oreilly.is.Percept;

/**
 *
 * @author Deep Blue Team
 */
public class MonkeyPerception implements Percept {

	private int bananasPosition;
	private int boxPosition;
	private int homePosition;
	private int monkeyPosition;
	/**
	 * @return the bananasPosition
	 */
	public int getBananasPosition() {
		return bananasPosition;
	}
	/**
	 * @param bananasPosition the bananasPosition to set
	 */
	public void setBananasPosition(int bananasPosition) {
		this.bananasPosition = bananasPosition;
	}
	/**
	 * @return the boxPosition
	 */
	public int getBoxPosition() {
		return boxPosition;
	}
	/**
	 * @param boxPosition the boxPosition to set
	 */
	public void setBoxPosition(int boxPosition) {
		this.boxPosition = boxPosition;
	}
	/**
	 * @return the homePosition
	 */
	public int getHomePosition() {
		return homePosition;
	}
	/**
	 * @param homePosition the homePosition to set
	 */
	public void setHomePosition(int homePosition) {
		this.homePosition = homePosition;
	}
	/**
	 * @return the monkeyPosition
	 */
	public int getMonkeyPosition() {
		return monkeyPosition;
	}
	/**
	 * @param monkeyPosition the monkeyPosition to set
	 */
	public void setMonkeyPosition(int monkeyPosition) {
		this.monkeyPosition = monkeyPosition;
	}
	
}
