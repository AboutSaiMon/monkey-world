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
package spa.simone.monkeyworld.core;

import spa.simone.monkeyworld.Utils;

import java.io.*;
import java.util.LinkedList;

/**
 * It wraps DLV with the K front-end, and retrieves the plan for the monkey.
 *
 * @author Deep Blue Team
 */
public class Planner {

    private static final String[] UNTIL_BOX_ARGS = new String[]{"dlv",
            "-silent", "-FPsec", "k/untilBox.plan", "temp/goal.plan",
            "k/monkey.dl"};
    private static final String[] UNTIL_BANANA_ARGS = new String[]{"dlv",
            "-silent", "-FPsec", "k/untilBanana.plan", "temp/goal.plan",
            "k/monkey.dl"};

    /**
     * Gets the subgoal until the box.
     *
     * @param monkey the monkey's position
     * @param box    the box's position
     * @return a plan
     */
    public static LinkedList<String> getPlanUntilBox(int monkey, int box) {
        return execute(KPlannerAction.GO, getGoalUntilBox(monkey, box));
    }

    /**
     * Gets the subgoal until the bananas bunch
     *
     * @param monkeyAndBox the monkey/box's position (they have the same position)
     * @param banana       the banana's position
     * @return gets the plan
     */
    public static LinkedList<String> getPlanUntilBanana(int monkeyAndBox,
                                                        int banana) {
        return execute(KPlannerAction.MOVE_BOX, getGoalUntilBanana(monkeyAndBox, banana));
    }

    /*
     * Wraps dlv and retrieves a valid K plan.
     */
    private static LinkedList<String> execute(String action, File goalFile) {
        LinkedList<String> plan = null;
        // creates a process builder with the given command
        ProcessBuilder processBuilder = null;
        if (action.equals(KPlannerAction.GO)) {
            processBuilder = new ProcessBuilder(UNTIL_BOX_ARGS);
        } else if (action.equals(KPlannerAction.MOVE_BOX)) {
            processBuilder = new ProcessBuilder(UNTIL_BANANA_ARGS);
        }
        Process process = null;
        BufferedReader input = null;
        try {
            // starts the process
            process = processBuilder.start();
            input = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            plan = getPlan(input.readLine(), action);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the input stream
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // kills the subprocess
            process.destroy();
            // deletes the temp file
            goalFile.delete();
        }
        return plan;
    }

    /*
     * Gets a list of actions, retrieved from the "result" string.
     */
    private static LinkedList<String> getPlan(String result, String action) {
        LinkedList<String> plan = new LinkedList<String>();
        String[] tokens = result.split(":|;");
        for (String token : tokens) {
            if (token.contains(action)) {
                plan.add(token.trim());
            }
        }
        return plan;
    }

    /*
     * Creates the temp file that contains the goal of the "untilBox.plan".
     */
    private static File getGoalUntilBox(int monkey, int box) {
        int planLength = Math.abs(monkey - box);
        StringBuilder findBox = new StringBuilder();
        findBox.append("initially: at(monkey, ").append(monkey);
        findBox.append("). at(box, ").append(box);
        findBox.append("). goal: at(monkey, P), at(box, P) ? (");
        findBox.append(planLength).append(")");
        return Utils.writeGoalFile(findBox.toString());
    }

    /*
     * Creates the temp file that contains the goal of the "untilBanana.plan".
     */
    private static File getGoalUntilBanana(int monkeyAndBox, int banana) {
        int planLength = Math.abs(monkeyAndBox - banana);
        StringBuilder findBanana = new StringBuilder();
        findBanana.append("initially:");
        findBanana.append("at(monkey,").append(monkeyAndBox).append(").");
        findBanana.append("at(box,").append(monkeyAndBox).append(").");
        findBanana.append("at(banana,").append(banana).append(").");
        findBanana.append("goal:");
        findBanana.append("at(monkey, P), at(box, P), at(banana, P)?");
        findBanana.append("(").append(planLength).append(")");
        return Utils.writeGoalFile(findBanana.toString());
    }

}
