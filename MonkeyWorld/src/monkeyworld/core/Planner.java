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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * @author Deep Blue Team
 */
public class Planner {

	private static final String[] untilBoxArgs = new String[] { "dlv", "-silent", "-FPsec", "k/untilBox.plan", "k/goal.plan", "k/monkey.dl" };
	private static final String[] untilBananaArgs = new String[] { "dlv", "-silent", "-FPsec", "k/untilBanana.plan", "k/goal.plan", "k/monkey.dl" };
	
	public static void main(String[] args) {
		LinkedList<String> plan = Planner.getUntilBananaPlan(3, 7);
		System.out.println(plan);
	}
	
	public static LinkedList<String> getUntilBoxPlan(int monkey, int box) {
		// creates the parameterized temp file
		File goal = getUntilBoxGoal(monkey, box);
		// creates a process builder with the given command
		ProcessBuilder processBuilder = new ProcessBuilder(untilBoxArgs);
		Process process = null;
		BufferedReader input = null;
		LinkedList<String> plan = null;
		try {
			// starts the process
			process = processBuilder.start();
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			plan = untilBoxPlan(input.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close the input stream
			if( input != null ) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// kills the subprocess
			process.destroy();
			// deletes the temp file
			goal.delete();
		}
		return plan;
	}

	/*
	 * Gets the plan from the actual position to the box.
	 */
	private static LinkedList<String> untilBoxPlan(String result) {
		LinkedList<String> plan = new LinkedList<String>();
		String[] tokens = result.split(":|;");
		for( String token : tokens ) {
			if( token.contains("go")) {
				plan.add(token.trim());
			}
		}
		return plan;
	}
	
	/*
	 * Creates the temp file that contains the goal of the "untilBox.plan".
	 */
	private static File getUntilBoxGoal(int monkey, int box) {
		int planLength = Math.abs(monkey - box);
		StringBuilder findBox = new StringBuilder();
		findBox.append("initially: at(monkey, ").append(monkey);
		findBox.append("). at(box, ").append(box);
		findBox.append("). goal: at(monkey, P), at(box, P) ? (");
		findBox.append(planLength).append(")");
		File goal = new File("k/goal.plan");
		PrintWriter out = null;
		try {
			out = new PrintWriter(goal);
			out.println(findBox.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return goal;
	}
	
	public static LinkedList<String> getUntilBananaPlan(int box, int banana) {
		// creates the parameterized temp file
		File goal = getUntilBananaGoal(box, banana);
		// creates a process builder with the given command
		ProcessBuilder processBuilder = new ProcessBuilder(untilBananaArgs);
		Process process = null;
		BufferedReader input = null;
		LinkedList<String> plan = null;
		try {
			// starts the process
			process = processBuilder.start();
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			plan = untilBananaPlan(input.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close the input stream
			if( input != null ) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// kills the subprocess
			process.destroy();
			// deletes the temp file
			goal.delete();
		}
		return plan;
	}
	
	private static File getUntilBananaGoal(int monkeyAndBox, int banana) {
		int planLength = Math.abs(monkeyAndBox - banana);
		StringBuilder findBanana = new StringBuilder();
		findBanana.append("initially:");
		findBanana.append("at(monkey,").append(monkeyAndBox).append(").");
		findBanana.append("at(box,").append(monkeyAndBox).append(").");
		findBanana.append("at(banana,").append(banana).append(").");
		findBanana.append("goal:");
		findBanana.append("at(monkey, P), at(box, P), at(banana, P)?");
		findBanana.append("(").append(planLength).append(")");
		File goal = new File("k/goal.plan");
		PrintWriter out = null;
		try {
			out = new PrintWriter(goal);
			out.println(findBanana.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return goal;
	}
	
	private static LinkedList<String> untilBananaPlan(String result) {
		LinkedList<String> plan = new LinkedList<String>();
		String[] tokens = result.split(":|;");
		for( String token : tokens ) {
			if( token.contains("moveBox")) {
				plan.add(token.trim());
			}
		}
		return plan;
	}

}
