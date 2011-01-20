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
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void goHome() {
		MonkeyAction action = new MonkeyAction(ActionType.GO_HOME);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(true)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void moveLeft() {
		MonkeyAction action = new MonkeyAction(ActionType.MOVE_LEFT);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(true)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void moveRight() {
		MonkeyAction action = new MonkeyAction(ActionType.MOVE_RIGHT);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(true)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void pushLeft() {
		MonkeyAction action = new MonkeyAction(ActionType.PUSH_LEFT);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(true)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void pushRight() {
		MonkeyAction action = new MonkeyAction(ActionType.PUSH_RIGHT);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(true)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void pullLeft() {
		MonkeyAction action = new MonkeyAction(ActionType.PULL_LEFT);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(true)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void pullRight() {
		MonkeyAction action = new MonkeyAction(ActionType.PULL_RIGHT);
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(true)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void grab() {
		MonkeyAction action = new MonkeyAction(ActionType.GRAB);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(true)));
		assertThat(action.isNoOp(), is(equalTo(false)));
	}
	
	@Test
	public void noOp() {
		MonkeyAction action = new MonkeyAction(ActionType.NO_OP);	
		assertThat(action.isGoOut(), is(equalTo(false)));
		assertThat(action.isGoHome(), is(equalTo(false)));
		assertThat(action.isMoveLeft(), is(equalTo(false)));
		assertThat(action.isMoveRight(), is(equalTo(false)));
		assertThat(action.isPushLeft(), is(equalTo(false)));
		assertThat(action.isPushRight(), is(equalTo(false)));
		assertThat(action.isPullLeft(), is(equalTo(false)));
		assertThat(action.isPullRight(), is(equalTo(false)));
		assertThat(action.isGrab(), is(equalTo(false)));
		assertThat(action.isNoOp(), is(equalTo(true)));
	}
}
