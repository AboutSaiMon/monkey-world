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
 * 
 * @author Deep Blue Team
 */
public class ThreadBanana extends Thread {

	private Laboratory lab;
	private int time;
	private Random random;

	/**
	 * 
	 */
	public ThreadBanana(Laboratory laboratory, int time) {
		lab = laboratory;
		this.time = time;
		random = new Random(time);
	}

	@Override
	public void run() {
		while (!lab.isGrabbed()) {
			try {
				sleep(time);
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
