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

	private int bananasBunch;
	private int box;
	private int home;
	private int monkey;
	
	/**
	 * 
	 */
	public MonkeyPerception() {
		bananasBunch = 0;
		box = 0;
		home = 0;
		monkey = home;
	}

	/**
	 * @return the bananasBunch
	 */
	public int getBananasBunch() {
		return bananasBunch;
	}

	/**
	 * @param position the bananasBunch to set
	 */
	public void setBananasBunch(int position) {
		bananasBunch = position;
	}

	/**
	 * @return the box
	 */
	public int getBox() {
		return box;
	}

	/**
	 * @param position the box to set
	 */
	public void setBox(int position) {
		box = position;
	}

	/**
	 * @return the home
	 */
	public int getHome() {
		return home;
	}

	/**
	 * @param position the home to set
	 */
	public void setHome(int position) {
		home = position;
	}

	/**
	 * @return the monkey
	 */
	public int getMonkey() {
		return monkey;
	}

	/**
	 * @param position the monkey to set
	 */
	public void setMonkey(int position) {
		monkey = position;
	}
	
}
