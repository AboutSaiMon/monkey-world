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
package spa.simone.monkeyworld.core.environment;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

/**
 *
 * @author Deep Blue Team
 */
public class EnvStatusTest {
	
	@Test
	public void bananasBunchTest() {
		EnvStatus status = new EnvStatus();
		// check the consistency of the getter and setter
		status.setBananasBunch(5);
		assertThat(status.getBananasBunch(), is(equalTo(5)));
		// check if the boolean flag works
		status.setGrabbed(true);
		assertThat(status.isGrabbed(), is(equalTo(true)));
		// if the banana is grabbed, the setter method doesn't work
		status.setBananasBunch(1);
		assertThat(status.getBananasBunch(), is(equalTo(5)));
	}
	
}
