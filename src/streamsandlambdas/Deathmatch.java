package streamsandlambdas;

import java.util.*;

import static java.lang.Math.sqrt;

/**
 * A person can die from natural causes (thirst, starvation, falling, drowning)
 * A person can kill Multiple persons (e.g. bomb, trap) with a certain chance
 * A person can kill another person
 * A person gets points for every kill
 * Stats: ATTACK (+ chance to kill), DEFENSE (- chance to dieFromAttack), SURVIVAL (- die from natural causes)
 * A person skills his stats randomly (but has skillChances from the start. e.g. 40% ATT, 40%DEF, 20%SURV)
 * Killing has a chance for multikill (after kill, chance to multikill inc by ATT)
 */
public class Deathmatch {
    private static final double KILL_CHANCE = 0.01;
    private static final int MAX_MULTI_KILL = 1000000;
    private final List<Person> allPeople;
    private final List<Person> remainingPeople;
    private final List<Person> deadPeople;
    private List<Person> deathFromNaturalCauses;
    private final Map<Person, List<Person>> kills;
    private final Random random;

    public Deathmatch(List<Person> people) {
        this.allPeople = people;
        this.remainingPeople = new ArrayList<>(people);
        this.deadPeople = new ArrayList<>();
        this.kills = new HashMap<>();
        this.random = new Random();
    }

    public Deathmatch(int numberOfPeople) {
        List<Person> people = Person.generatePeople(numberOfPeople);
        this.allPeople = people;
        this.remainingPeople = new ArrayList<>(people);
        this.deadPeople = new ArrayList<>();
        this.kills = new HashMap<>();
        this.random = new Random();
    }

    public static void main(String[] args) {
        Deathmatch deathmatch = new Deathmatch(100_000);
        deathmatch.start();
    }

    public void start() {
        while (remainingPeople.size() > 1) {
            randomKilling();
        }
        getWinner();
        displayResults();
        getWinner();
    }

    public void aKillsB(Person a, Person b) {
        // remove from alivePersons
        remainingPeople.remove(b);
        deadPeople.add(b);

        // if no kills yet, create new list
        if (!kills.containsKey(a)) {
            kills.put(a, new ArrayList<>());
        }

        // add to kills list
        kills.get(a).add(b);

        EventStrings.printKillEvent(a, b);
    }

    public boolean committedSuicide(Person person) {
        return kills.containsKey(person) && kills.get(person).contains(person);
    }

    public void diesFromNaturalCauses(Person person) {
        // remove from alivePersons
        remainingPeople.remove(person);
        deadPeople.add(person);
        EventStrings.printDeathFromNaturalCause(person);
    }

    public Person getWinner() {
        if (remainingPeople.size() == 1) {
            EventStrings.printWinner(remainingPeople.get(0));
            return remainingPeople.get(0);
        } else {
            EventStrings.printNoWinner();
            return null;
        }
    }

    public double calculateRanking(Person a) {
        return sqrt(getNumberOfKills(a));
    }

    public List<Person> getRemainingPeople() {
        return remainingPeople;
    }

    public Map<Person, List<Person>> getKills() {
        return kills;
    }

    public void randomKilling() {
        Person a = remainingPeople.get((int) (Math.random() * remainingPeople.size()));
        Person b = remainingPeople.get((int) (Math.random() * remainingPeople.size()));
        int multiKill = MAX_MULTI_KILL;
        while (evaluateCanKill(a, b, random) && remainingPeople.size() > 1 && multiKill-- > 0) {
            aKillsB(a, b);
            if (a.equals(b)) {
                break;
            }
            b = remainingPeople.get((int) (Math.random() * remainingPeople.size()));
        }
    }

    private void printKills(Person a) {
        String format = "%-25s%s%n";
        if (!kills.containsKey(a)) {
            String prefix = a.getFullName();
            String message = " has killed [0] people";
            System.out.printf(format, prefix, message);
            return;
        }
        List<Person> people = kills.get(a);
        //String listOfKills = people.stream().map(Person::getFullName).collect(Collectors.joining(", "));
        String listOfKills = "";
        String prefix = a.getFullName();
        String message = " has killed [" + people.size() + "] people:\t(" + listOfKills + ")";
        System.out.printf(format, prefix, message);
    }

    public void displayResults() {
        allPeople.stream().sorted(Comparator.comparingInt(this::getNumberOfKills)).forEachOrdered(this::printKills);
    }

    public int getNumberOfKills(Person a) {
        if (!kills.containsKey(a) || kills.get(a).isEmpty()) {
            return 0;
        } else {
            return kills.get(a).size();
        }
    }

    public boolean evaluateCanKill(Person a, Person b, Random random) {
        double aKills = calculateRanking(a);
        double bKills = calculateRanking(b);
        double diff = aKills - bKills; // positive when a has higher ranking than b
        double constant = 1;
        double factor = constant / (constant + Math.abs(diff*10));
        if (diff > 0 && factor != 0) { // if b has more kills, the percentage gets lower
            factor = 1 / factor; // if diff > 0, then a has more kills and factor gets flipped > 1
        }
        int bound = 1_000_000;
        int i = (int) ((random.nextInt(bound)) / factor);
        if (i < KILL_CHANCE * bound) {
            return true;
        }
        return false;
    }

    static class EventStrings {
        public static void printKillEvent(Person a, Person b) {
            String message = "";
            if (a == b) {
                message += a.getFullName() + " committed suicide.";
                System.out.println(message);
            } else {
                message += a.getFullName() + " killed " + b.getFullName() + ".";
                System.out.println(message);
            }
        }

        public static void printDeathFromNaturalCause(Person a) {
            String message = a.getFullName() + " died from natural causes.";
            System.out.println(message);
        }

        public static void printWinner(Person a) {
            String message = a.getFullName() + " won the game!";
            System.out.println(message);
        }

        public static void printNoWinner() {
            String message = "there is no winner (yet).";
            System.out.println(message);
        }
    }
}
