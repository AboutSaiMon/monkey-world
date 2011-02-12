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
package monkeyworld.core;

import java.util.LinkedList;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Deep Blue Team
 */
public class PlannerTest {

	@Test
	public void untilBox1() {
		LinkedList<String> plan = Planner.getPlanUntilBox(3, 0);
		assertThat(plan, everyItem(containsString("go")));
	}
	
	@Test
	public void untilBox2() {
		LinkedList<String> plan = Planner.getPlanUntilBox(3, 0);
		assertThat(plan, hasItems(equalTo("go(2)"), equalTo("go(1)"), equalTo("go(0)")));
	}
	
	@Test(expected=NullPointerException.class)
	public void untilBox3() {
		LinkedList<String> plan = Planner.getPlanUntilBox(3, -1);
	}
	
	@Test
	public void untilBanana1() {
		LinkedList<String> plan = Planner.getPlanUntilBanana(6, 9);
		assertThat(plan, everyItem(containsString("moveBox")));
	}
	
	@Test
	public void untilBanana2() {
		LinkedList<String> plan = Planner.getPlanUntilBanana(3, 0);
		assertThat(plan, hasItems(equalTo("moveBox(2)"), equalTo("moveBox(1)"), equalTo("moveBox(0)")));
	}
	
	@Test(expected=NullPointerException.class)
	public void untilBanana3() {
		LinkedList<String> plan = Planner.getPlanUntilBanana(3, 10);
	}
	
}