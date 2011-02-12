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

import monkeyworld.core.environment.EnvStatus;

import org.junit.Test;

/**
 *
 * @author Deep Blue Team
 */
public class MonkeyTest {

	// [go(1), go(2), go(3), go(4), go(5), go(6), go(7), go(8), moveBox(7),
	// moveBox(6), moveBox(5), moveBox(4), climb, grab, grab, climb, moveBox(4),
	// moveBox(5), moveBox(6), moveBox(7), go(8), go(7), go(6), go(5), go(4), go(3),
	// go(2), go(1)]
	@Test
	public void test() {
		Monkey m = new Monkey();
		EnvStatus status = new EnvStatus();
		status.setHome(3);
		status.setBox(5);
		status.setBananasBunch(2);
		MonkeyPerception percept = new MonkeyPerception();
		percept.setBananasBunch(status.getBananasBunch());
		percept.setBox(status.getBox());
		percept.setHome(status.getHome());
		percept.setMonkey(status.getMonkey());
		System.out.println("Home: " + percept.getHome());
		System.out.println("Monkey: " + percept.getMonkey());
	}
	
}