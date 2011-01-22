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

import java.util.Random;

/**
 * This is the thread that change the position of the bananas bunch every
 * <code>time</code> secs.
 * 
 * @author Deep Blue Team
 */
public class ThreadBanana extends Thread {

	private Laboratory lab;
	private int intervalTime;
	private Random random;

	/**
	 * Creates a new thread, accepting a reference to the environment, as input
	 * parameter.
	 */
	public ThreadBanana(Laboratory laboratory) {
		lab = laboratory;
		random = new Random(intervalTime);
	}

	/**
	 * Sets the interval time. Every <code>time</code> times, this thread has to
	 * change the bananas bunch position.
	 * 
	 * @param time
	 *            the interval time
	 */
	public void setIntervalTime(int time) {
		this.intervalTime = time;
	}

	@Override
	public void run() {
		while (!lab.isGrabbed()) {
			try {
				sleep(intervalTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lab.setBananasBunch(getPosition());
		}
	}

	private int getPosition() {
		return random.nextInt(lab.getLength());
	}

}
