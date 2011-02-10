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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * 
 * @author Deep Blue Team
 */
public class MonkeyActionTest {

	@Test
	public void goOut() {
		MonkeyAction action = new MonkeyAction(ActionType.GO_OUT);
		assertThat(action.isGoOut(), is(equalTo(true)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void goHome() {
		MonkeyAction action = new MonkeyAction(ActionType.GO_HOME);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(true)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void goLeft() {
		MonkeyAction action = new MonkeyAction(ActionType.GO_LEFT);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(true)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void goRight() {
		MonkeyAction action = new MonkeyAction(ActionType.GO_RIGHT);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(true)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void moveBoxLeft() {
		MonkeyAction action = new MonkeyAction(ActionType.MOVE_BOX_LEFT);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(true)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void moveBoxRight() {
		MonkeyAction action = new MonkeyAction(ActionType.MOVE_BOX_RIGHT);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(true)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void climb() {
		MonkeyAction action = new MonkeyAction(ActionType.CLIMB);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(true)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void descend() {
		MonkeyAction action = new MonkeyAction(ActionType.DESCEND);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(true)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void grab() {
		MonkeyAction action = new MonkeyAction(ActionType.GRAB);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(true)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void noOp() {
		MonkeyAction action = new MonkeyAction(ActionType.NO_OP);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isGoLeft(), is(equalTo(false)));
		assertThat(action.isGoRight(), is(equalTo(false)));
		assertThat(action.isMoveBoxLeft(), is(equalTo(false)));
		assertThat(action.isMoveBoxRight(), is(equalTo(false)));
		assertThat(action.isClimb(), is(equalTo(false)));
		assertThat(action.isDescend(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(true)));
	}
}
