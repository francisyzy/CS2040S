***REMOVED***

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class bestrelayteam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfRunners = sc.nextInt();
        sc.nextLine();
        ArrayList<Runner> runners = new ArrayList<Runner>();
        for (int i = 0; i < noOfRunners; i++) {
            runners.add(new Runner(sc.next(), sc.nextDouble(), sc.nextDouble()));
            sc.nextLine();
        }
        sc.close();

        ArrayList<Team> teams = new ArrayList<Team>();

        runners.sort((r1, r2) -> r1.compareFirst(r1, r2));
        // ArrayList<Runner> supportingRunners = new ArrayList<Runner>();
        // supportingRunners.addAll(runners);
        // supportingRunners.sort((r1, r2) -> r1.compare(r1, r2));

        // for (int i = 0; i < runners.size(); i++) {
        // ArrayList<Runner> bestSupportingRunners = new ArrayList<Runner>();
        // for (int j = 0; bestSupportingRunners.size() < 4; j++) {
        // if (runners.get(i) != supportingRunners.get(j)) {
        // bestSupportingRunners.add(supportingRunners.get(j));
        // }
        // }
        // teams.add(getBestTeam(runners.get(i), bestSupportingRunners));
        // // int noOfSupporting = 0;
        // // int nextSupporting = 0;
        // // while (noOfSupporting != 3) {
        // // if (!runners.get(i).equals(supportingRunners.get(nextSupporting))) {
        // // bestSupportingRunners.add(supportingRunners.get(nextSupporting));
        // // noOfSupporting += 1;
        // // }
        // // nextSupporting++;
        // // }
        // // teams.add(new Team(runners.get(i), bestSupportingRunners.get(0),
        // // bestSupportingRunners.get(1),
        // // bestSupportingRunners.get(2)));

        // }
        for (Runner runner : runners) {
            ArrayList<Runner> supportingRunners = new ArrayList<Runner>();
            supportingRunners.addAll(runners);
            supportingRunners.remove(runner);
            // teams.addAll(getAllTeams(runner, supportingRunners));
            teams.add(getBestTeam(runner, supportingRunners));
        }
        // System.out.println(teams.size());
        teams.sort((t1, t2) -> t1.compare(t1, t2));
        System.out.println(teams.get(0));
    }

    static Team getBestTeam(Runner first, ArrayList<Runner> runners) {
        runners.sort((r1, r2) -> r1.compare(r1, r2));
        return new Team(first, runners.get(0), runners.get(1), runners.get(2));
    }

    static ArrayList<Team> getAllTeams(Runner first, ArrayList<Runner> runners) {
        runners.sort((r1, r2) -> r1.compare(r1, r2));
        ArrayList<Team> teams = new ArrayList<Team>();
        for (int i = 0, j = 1, k = 2; i < runners.size(); i++, j++, k++) {
            teams.add(new Team(
                    first,
                    runners.get(i),
                    runners.get(j % 4),
                    runners.get(k % 4)));
        }
        return teams;
    }
}

class Team implements Comparator<Team> {
    Runner first;
    Runner r2;
    Runner r3;
    Runner r4;

    Team(Runner first, Runner r2, Runner r3, Runner r4) {
        this.first = first;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
    }

    public double getTime() {
        double time = 0;
        time += first.first;
        time += r2.other;
        time += r3.other;
        time += r4.other;
        return time;
    }

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

    Runner(String name, double first, double other) {
        this.name = name;
        this.first = first;
        this.other = other;
    }

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

    public int compareFirst(Runner r1, Runner r2) {
        if (r1.first > r2.first) {
            return 1;
        } else if (r1.first < r2.first) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}