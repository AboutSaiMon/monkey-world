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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * 
 * @author Deep Blue Team
 */
public class MonkeyActionTest {

	@Test
	public void testNoOp() {
		MonkeyAction action = new MonkeyAction(ActionType.NO_OP);
		assertThat(action.isNoOp(), is(true));
		assertThat(action.isClimb(), is(false));
		assertThat(action.isGrab(), is(false));
		assertThat(action.isMoveLeft(), is(false));
		assertThat(action.isMoveRight(), is(false));
		assertThat(action.isPull(), is(false));
		assertThat(action.isPush(), is(false));
	}

	@Test
	public void testClimb() {
		MonkeyAction action = new MonkeyAction(ActionType.CLIMB);
		assertThat(action.isNoOp(), is(false));
		assertThat(action.isClimb(), is(true));
		assertThat(action.isGrab(), is(false));
		assertThat(action.isMoveLeft(), is(false));
		assertThat(action.isMoveRight(), is(false));
		assertThat(action.isPull(), is(false));
		assertThat(action.isPush(), is(false));
	}

	public void testGrab() {
		MonkeyAction action = new MonkeyAction(ActionType.GRAB);
		assertThat(action.isNoOp(), is(false));
		assertThat(action.isClimb(), is(false));
		assertThat(action.isGrab(), is(true));
		assertThat(action.isMoveLeft(), is(false));
		assertThat(action.isMoveRight(), is(false));
		assertThat(action.isPull(), is(false));
		assertThat(action.isPush(), is(false));
	}

	@Test
	public void testMoveLeft() {
		MonkeyAction action = new MonkeyAction(ActionType.MOVE_LEFT);
		assertThat(action.isNoOp(), is(false));
		assertThat(action.isClimb(), is(false));
		assertThat(action.isGrab(), is(false));
		assertThat(action.isMoveLeft(), is(true));
		assertThat(action.isMoveRight(), is(false));
		assertThat(action.isPull(), is(false));
		assertThat(action.isPush(), is(false));
	}

	@Test
	public void testMoveRight() {
		MonkeyAction action = new MonkeyAction(ActionType.MOVE_RIGHT);
		assertThat(action.isNoOp(), is(false));
		assertThat(action.isClimb(), is(false));
		assertThat(action.isGrab(), is(false));
		assertThat(action.isMoveLeft(), is(false));
		assertThat(action.isMoveRight(), is(true));
		assertThat(action.isPull(), is(false));
		assertThat(action.isPush(), is(false));
	}

	@Test
	public void testPull() {
		MonkeyAction action = new MonkeyAction(ActionType.PULL);
		assertThat(action.isNoOp(), is(false));
		assertThat(action.isClimb(), is(false));
		assertThat(action.isGrab(), is(false));
		assertThat(action.isMoveLeft(), is(false));
		assertThat(action.isMoveRight(), is(false));
		assertThat(action.isPull(), is(true));
		assertThat(action.isPush(), is(false));
	}

	@Test
	public void testPush() {
		MonkeyAction action = new MonkeyAction(ActionType.PUSH);
		assertThat(action.isNoOp(), is(false));
		assertThat(action.isClimb(), is(false));
		assertThat(action.isGrab(), is(false));
		assertThat(action.isMoveLeft(), is(false));
		assertThat(action.isMoveRight(), is(false));
		assertThat(action.isPull(), is(false));
		assertThat(action.isPush(), is(true));
	}

}
