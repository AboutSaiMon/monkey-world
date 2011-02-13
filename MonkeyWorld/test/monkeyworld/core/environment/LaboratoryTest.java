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

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import monkeyworld.core.agent.Monkey;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Deep Blue Team
 */
public class LaboratoryTest {
	
	private static Monkey monkey;
	
	@BeforeClass
	public static void setUp() {
		monkey = new Monkey();
	}

	@Test(expected=IllegalArgumentException.class)
	public void setBananasPosition() {
		Laboratory lab = new Laboratory(monkey);
		lab.setBananasBunch(-30);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setBoxPosition() {
		Laboratory lab = new Laboratory(monkey);
		lab.setBox(87);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setHomePosition() {
		Laboratory lab = new Laboratory(monkey);
		lab.setHome(-1);
	}
	
	@Test
	public void getMethods() {
		Laboratory lab = new Laboratory(monkey);
		lab.setBananasBunch(0);
		lab.setBox(1);
		lab.setHome(2);
		assertThat(lab.getBananasBunch(), is(equalTo(0)));
		assertThat(lab.getBox(), is(equalTo(1)));
		assertThat(lab.getHome(), is(equalTo(2)));
		assertThat(lab.getMonkey(), is(equalTo(2)));
	}
	
	@Test
	public void testMonkeyAliveLabDone() {
		Monkey m = new Monkey();
		Laboratory lab = new Laboratory(m);
		m.setAlive(false);
		assertThat("Monkey is not alive.", m.isAlive(), is(equalTo(false)));
		assertThat("If monkey is not alive, the lab has to be done.", lab.isDone(), is(equalTo(true)));
	}
	
}
