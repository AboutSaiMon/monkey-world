/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.core.environment;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Deep Blue Team
 */
public class EnvStatusModifierTest {

    private EnvStatusModifier modifier = new EnvStatusModifier();
    private EnvStatus status = modifier.getStatus();

    @Test
    public void atHome() {
        status.setHome(3);
        modifier.goLeft();
        assertThat(status.getMonkey(), is(equalTo(3)));
        modifier.goRight();
        assertThat(status.getMonkey(), is(equalTo(3)));
        modifier.moveBoxLeft();
        assertThat(status.getMonkey(), is(equalTo(3)));
        modifier.moveBoxRight();
        assertThat(status.getMonkey(), is(equalTo(3)));
        assertThat(status.isAtHome(), is(equalTo(true)));
        modifier.goHome();
        assertThat(status.getMonkey(), is(equalTo(3)));
        assertThat(status.isAtHome(), is(equalTo(true)));
    }

    @Test
    public void cannotClimbWhenAtHome() {
        status.setHome(4);
        status.setBox(4);
        assertThat(status.isOnTheBox(), is(equalTo(false)));
        modifier.climb();
        assertThat(status.isOnTheBox(), is(equalTo(false)));
    }

    @Test
    public void cannotGoOutOfEnv() {
        status.setMonkey(0);
        modifier.goLeft();
        assertThat(status.getMonkey(), is(equalTo(0)));
        status.setMonkey(9);
        modifier.goRight();
        assertThat(status.getMonkey(), is(equalTo(9)));
    }

    @Test
    public void cannotMoveTheBoxOutOfEnv() {
        status.setBox(0);
        status.setMonkey(0);
        modifier.moveBoxLeft();
        assertThat(status.getBox(), is(equalTo(0)));
        assertThat(status.getMonkey(), is(equalTo(0)));
        status.setBox(9);
        status.setMonkey(9);
        modifier.moveBoxRight();
        assertThat(status.getBox(), is(equalTo(9)));
        assertThat(status.getMonkey(), is(equalTo(9)));
    }

    @Test
    public void cannotMoveTheBoxFromFar() {
        status.setBox(3);
        status.setMonkey(4);
        modifier.moveBoxLeft();
        assertThat(status.getBox(), is(equalTo(3)));
        assertThat(status.getMonkey(), is(equalTo(4)));
        modifier.moveBoxRight();
        assertThat(status.getBox(), is(equalTo(3)));
        assertThat(status.getMonkey(), is(equalTo(4)));
    }

    @Test
    public void cannotBeOnTheBox() {
        status.setBox(3);
        status.setMonkey(4);
        status.setOnTheBox(true);
        // because the monkey can not be on the box if it is not
        // in the same cell of the box
        assertThat(status.isOnTheBox(), is(equalTo(false)));
    }

    public void climb() {

    }

}
