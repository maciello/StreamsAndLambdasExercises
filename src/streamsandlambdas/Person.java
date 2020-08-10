package streamsandlambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person {
    protected static final Random R = new Random(1234);
    private static final RandomPersonData DATA = new RandomPersonData();
    protected static int c = 0;
    protected final int id;
    protected final int age;
    protected final String firstName;
    protected final String lastName;
    protected final Gender gender;
    protected final FavouriteFood favouriteFood;

    public Person(int age, String firstName, String lastName, Gender gender, FavouriteFood favouriteFood) {
        this.id = ++c;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.favouriteFood = favouriteFood;
    }

    public static Person getRandomPerson() {
        Gender gender = DATA.getRandomGender();
        int randomAge = DATA.getRandomAgeBetween(16, 80);
        String randomFirstName = DATA.getRandomFirstName(gender);
        String randomLastName = DATA.getRandomLastName();
        FavouriteFood randomFavouriteFood = DATA.getRandomFavouriteFood();
        int randomPersonType = R.nextInt(3);
        return switch (randomPersonType) {
            case 0 -> new Person(randomAge, randomFirstName, randomLastName, gender, randomFavouriteFood);
            case 1 -> new Student(randomAge, randomFirstName, randomLastName, gender, randomFavouriteFood);
            case 2 -> new Teacher(DATA.getRandomAgeBetween(26, 64), randomFirstName, randomLastName, gender,
                                  randomFavouriteFood);
            default -> null;
        };
    }

    public static void main(String[] args) {
        List<Person> people = generatePeople(1_000_000);
        long count = people.stream().filter(person -> person.firstName.equalsIgnoreCase("Bob")).count();
        System.out.println(count);
        Map<Integer, Long> groupedByNameLength = people.stream().collect(
                Collectors.groupingBy(person -> person.firstName.length(), Collectors.counting()));
        System.out.println(groupedByNameLength);
    }

    public static List<Person> generatePeople(int amount) {
        return Stream.generate(Person::getRandomPerson).limit(amount).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='" +
                lastName + '\'' + ", gender=" + gender + ", favouriteFood=" + favouriteFood + '}';
    }

    public static class Student extends Person {
        public Student(int age, String firstName, String lastName, Gender gender, FavouriteFood favouriteFood) {
            super(age, firstName, lastName, gender, favouriteFood);
        }

        @Override
        public String toString() {
            return "Student{" + "id=" + id + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='" +
                    lastName + '\'' + ", gender=" + gender + ", favouriteFood=" + favouriteFood + '}';
        }
    }

    public static class Teacher extends Person {
        public Teacher(int age, String firstName, String lastName, Gender gender, FavouriteFood favouriteFood) {
            super(age, firstName, lastName, gender, favouriteFood);
        }

        @Override
        public String toString() {
            return "Teacher{" + "id=" + id + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='" +
                    lastName + '\'' + ", gender=" + gender + ", favouriteFood=" + favouriteFood + '}';
        }
    }

    public String getFullName() {
        return "[" + firstName + " " + lastName + "]";
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public FavouriteFood getFavouriteFood() {
        return favouriteFood;
    }
}
