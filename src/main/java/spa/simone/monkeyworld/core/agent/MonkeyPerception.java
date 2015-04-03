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
package spa.simone.monkeyworld.core.agent;

import spa.simone.monkeyworld.core.environment.EnvStatus;

import org.oreilly.is.Percept;

/**
 * This is the perception that the monkey receives from the environment.
 * 
 * @author Deep Blue Team
 */
public class MonkeyPerception implements Percept {

	private int bananasBunch;
	private int box;
	private int home;
	private int monkey;
	private boolean grabbed;
	private boolean onTheBox;
	private boolean atHome;

	/**
	 * Creates a new perception.
	 */
	public MonkeyPerception() {
		bananasBunch = 0;
		box = 0;
		home = 0;
		monkey = home;
		atHome = true;
		onTheBox = false;
		grabbed = false;
	}

	/**
	 * Creates a new perception, setting the values retrieved from the internal
	 * status <code>status</code>.
	 * 
	 * @param status the internal status of the environment
	 */
	public MonkeyPerception(EnvStatus status) {
		bananasBunch = status.getBananasBunch();
		box = status.getBox();
		home = status.getHome();
		monkey = status.getMonkey();
		atHome = status.isAtHome();
		onTheBox = status.isOnTheBox();
		grabbed = status.isGrabbed();
	}

	/**
	 * Gets the position of the bananas bunch.
	 * 
	 * @return the bananas bunch position
	 */
	public int getBananasBunch() {
		return bananasBunch;
	}

	/**
	 * Sets the position of the bananas bunch.
	 * 
	 * @param position
	 *            the bananas bunch position to set
	 */
	public void setBananasBunch(int position) {
		bananasBunch = position;
	}

	/**
	 * Gets the position of the box.
	 * 
	 * @return the box position
	 */
	public int getBox() {
		return box;
	}

	/**
	 * Sets the position of the box.
	 * 
	 * @param position
	 *            the box position to set
	 */
	public void setBox(int position) {
		box = position;
	}

	/**
	 * Gets the position of the home.
	 * 
	 * @return the home position.
	 */
	public int getHome() {
		return home;
	}

	/**
	 * Sets the position of the home.
	 * 
	 * @param position
	 *            the home position to set
	 */
	public void setHome(int position) {
		home = position;
	}

	/**
	 * Gets the position of the monkey.
	 * 
	 * @return the monkey position
	 */
	public int getMonkey() {
		return monkey;
	}

	/**
	 * Sets the position of the monkey.
	 * 
	 * @param position
	 *            the monkey position to set
	 */
	public void setMonkey(int position) {
		monkey = position;
	}

	/**
	 * @return true if the bananas bunch is grabbed by the monkey
	 */
	public boolean isGrabbed() {
		return grabbed;
	}

	/**
	 * Sets the <code>grabbed</code> status of the bananas bunch. If
	 * <code>true</code>, the monkey grabbed the bananas bunch.
	 * 
	 * @param grabbed
	 *            true or false
	 */
	public void setGrabbed(boolean grabbed) {
		this.grabbed = grabbed;
	}

	/**
	 * @return the atHome
	 */
	public boolean isAtHome() {
		return atHome;
	}

	/**
	 * @param atHome
	 *            the atHome to set
	 */
	public void setAtHome(boolean atHome) {
		this.atHome = atHome;
	}

	/**
	 * @return the onTheBox
	 */
	public boolean isOnTheBox() {
		return onTheBox;
	}

	/**
	 * @param onTheBox
	 *            the onTheBox to set
	 */
	public void setOnTheBox(boolean onTheBox) {
		this.onTheBox = onTheBox;
	}

}