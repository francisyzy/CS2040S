

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class bestrelayteam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfRunners = sc.nextInt();
        sc.nextLine(); // move cursor down
        ArrayList<Runner> runners = new ArrayList<Runner>();
        //Add all the runners into a arraylist
        for (int i = 0; i < noOfRunners; i++) {
            runners.add(new Runner(sc.next(), sc.nextDouble(), sc.nextDouble()));
            sc.nextLine();
        }
        sc.close();

        ArrayList<Team> teams = new ArrayList<Team>();

        runners.sort((r1, r2) -> r1.compareFirst(r1, r2));
        for (Runner runner : runners) {
            // Create array of eligible runners to support the first dude
            ArrayList<Runner> supportingRunners = new ArrayList<Runner>();
            supportingRunners.addAll(runners);
            // remove the first dude from consideration
            supportingRunners.remove(runner);
            // add the team into the arraylist
            teams.add(getBestTeam(runner, supportingRunners));
        }
        // Sort the teams by the order of their run timings
        teams.sort((t1, t2) -> t1.compare(t1, t2));
        // Select index 0 because best team
        System.out.println(teams.get(0));
    }

    /**
     * @param first   Front runner of the team
     * @param runners ArrayList of teams that can support the front runner
     * @return Team
     *         Creates the best team using the front runner and the list of
     *         available runners and sort them by their best timings
     */
    static Team getBestTeam(Runner first, ArrayList<Runner> runners) {
        runners.sort((r1, r2) -> r1.compare(r1, r2));
        return new Team(first, runners.get(0), runners.get(1), runners.get(2));
    }
}

class Team implements Comparator<Team> {
    Runner first;
    Runner r2;
    Runner r3;
    Runner r4;

    /**
     * @param first
     * @param r2
     * @param r3
     * @param r4
     * @return Team
     *         Returns the team object created using the first runner and its
     *         supporting running team
     */

    Team(Runner first, Runner r2, Runner r3, Runner r4) {
        this.first = first;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
    }

    /**
     * @return the timing of the team
     *         Gets the timing of the team in the race
     */
    public double getTime() {
        double time = 0;
        time += first.first;
        time += r2.other;
        time += r3.other;
        time += r4.other;
        return time;
    }

    /**
     * @param Team 1
     * @param Team 2
     * @return -1, 1 or 0
     *         If team 1 is better, return 1. if team 2 is better return -1, if both
     *         teams are equal return 0
     */
    @Override
    public int compare(Team t1, Team t2) {
        if (t1.getTime() > t2.getTime()) {
            return 1;
        } else if (t1.getTime() < t2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * @return String
     *         String representation of the team including the timing
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTime() + "\n");
        sb.append(first.toString() + "\n");
        sb.append(r2.toString() + "\n");
        sb.append(r3.toString() + "\n");
        sb.append(r4.toString() + "\n");

        return sb.toString();
    }
}

class Runner implements Comparator<Runner> {
    String name;
    double first; // First lap
    double other; // other laps

    /**
     * Creates the runner object
     * 
     * @param name  of the runner
     * @param first leg timing
     * @param other leg timing
     */
    Runner(String name, double first, double other) {
        this.name = name;
        this.first = first;
        this.other = other;
    }

    /**
     * @return boolean
     *         check if the runner object are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Runner)) {
            return false;
        }

        Runner r = (Runner) o;
        return r.name.equals(this.name);
    }

    /**
     * Comparison of runners in other leg
     * 
     * @return 1 or -1 or 0,
     *         1 if r1 is faster than r2 in other leg
     *         -1 if r1 is slower than r2 in other leg
     *         0 if r1 is same as r2 in other leg
     */
    @Override
    public int compare(Runner r1, Runner r2) {
        if (r1.other > r2.other) {
            return 1;
        } else if (r1.other < r2.other) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Comparison of runners in first leg
     * 
     * @return 1 or -1 or 0,
     *         1 if r1 is faster than r2 in first leg
     *         -1 if r1 is slower than r2 in first leg
     *         0 if r1 is same as r2 in first leg
     */
    public int compareFirst(Runner r1, Runner r2) {
        if (r1.first > r2.first) {
            return 1;
        } else if (r1.first < r2.first) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * @return name of the runner
     */
    @Override
    public String toString() {
        return name;
    }
}