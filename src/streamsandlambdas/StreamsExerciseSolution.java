package streamsandlambdas;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static streamsandlambdas.FavouriteFood.BURGER;
import static streamsandlambdas.FavouriteFood.PIZZA;
import static streamsandlambdas.Gender.FEMALE;
import static streamsandlambdas.Gender.MALE;

public class StreamsExerciseSolution extends StreamsExercise {
    @Override
    public List<Person> listOfAllMalesSortedByFirstNameAndLastName(List<Person> personList) {
        return personList.stream().filter(person -> person.gender.equals(MALE)).sorted(
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)).collect(
                Collectors.toList());
    }

    @Override
    public List<Person> listOfAllFemalesSortedByFirstNameAndLastName(List<Person> personList) {
        return personList.stream().filter(person -> person.gender.equals(FEMALE)).sorted(
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)).collect(
                Collectors.toList());
    }

    @Override
    public List<Person> listOfAllBurgerEatersSortedByAgeFromLowToHigh(List<Person> personList) {
        return personList.stream().filter(person -> person.favouriteFood.equals(BURGER)).sorted(
                Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfAllStudentsSortedById(List<Person> personList) {
        return personList.stream().filter(person -> person instanceof Person.Student).sorted(
                Comparator.comparingInt(Person::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfAllPersonsWithNameBOBsortedById(List<Person> personList) {
        return personList.stream().filter(person -> person.firstName.equalsIgnoreCase("BOB")).sorted(
                Comparator.comparingInt(Person::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfAllStudentsUnder20YearsOldSortedById(List<Person> personList) {
        return personList.stream().filter(person -> person instanceof Person.Student).filter(
                person -> person.age < 20).sorted(Comparator.comparingInt(Person::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfAllTeachersOver50YearsOldSortedById(List<Person> personList) {
        return personList.stream().filter(person -> person instanceof Person.Teacher).filter(
                person -> person.age > 50).sorted(Comparator.comparingInt(Person::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfAllFemaleStudentsThatLikePizzaAndAreBetween18And30YearsOldWithNameMARYsortById(
            List<Person> personList) {
        return personList.stream().filter(person -> person.gender.equals(FEMALE)).filter(
                person -> person instanceof Person.Student).filter(
                person -> person.favouriteFood.equals(PIZZA)).filter(
                person -> person.age > 18 && person.age < 30).filter(
                person -> person.firstName.equalsIgnoreCase("Mary")).sorted(
                Comparator.comparingInt(Person::getId)).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfAllPersonsWithFirstNameJACOBandLastNameSMITH(List<Person> personList) {
        return personList.stream().filter(person -> person.firstName.equalsIgnoreCase("jacob")).filter(
                person -> person.lastName.equalsIgnoreCase("smith")).collect(Collectors.toList());
    }

    @Override
    public List<Person> listOfOldestStudents(List<Person> personList) {
        int maxAge = personList.stream().filter(person -> person instanceof Person.Student).mapToInt(
                Person::getAge).max().getAsInt();
        return personList.stream().filter(person -> person.age == maxAge).collect(Collectors.toList());
    }

    @Override
    public Map<FavouriteFood, Long> numberOfPeoplePerFavouriteFood(List<Person> personList) {
        return personList.stream().collect(Collectors.groupingBy(Person::getFavouriteFood, Collectors.counting()));
    }

    @Override
    public Map<String, Long> numberOfPeoplePerFirstName(List<Person> personList) {
        return personList.stream().collect(Collectors.groupingBy(Person::getFirstName, Collectors.counting()));
    }

    @Override
    public Map<Integer, Long> numberOfPeoplePerAge(List<Person> personList) {
        return personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
    }

    @Override
    public Map<Gender, List<Person>> groupPeopleByGender(List<Person> personList) {
        return personList.stream().collect(Collectors.groupingBy(Person::getGender));
    }

    @Override
    public int numberOfTeachersOver70(List<Person> personList) {
        return (int) personList.stream().filter(person -> person instanceof Person.Teacher).filter(
                person -> person.age > 70).count();
    }

    @Override
    public double averageAgeOfStudents(List<Person> personList) {
        return personList.stream().filter(person -> person instanceof Person.Student).mapToInt(
                student -> student.age).average().getAsDouble();
    }

    @Override
    public double minAgeOfTeachers(List<Person> personList) {
        return personList.stream().filter(person -> person instanceof Person.Teacher).mapToInt(
                teacher -> teacher.age).min().getAsInt();
    }

    @Override
    public double numberOfStudentsAged27(List<Person> personList) {
        return personList.stream().filter(person -> person instanceof Person.Student).filter(
                person -> person.age == 27).count();
    }

    @Override
    public Map<Boolean, List<Person>> partitionByIsTeacher(List<Person> personList) {
        return personList.stream().collect(Collectors.partitioningBy(person -> person instanceof Person.Teacher));
    }

    @Override
    public Map<Boolean, Long> partitionByAgeOver50AndCount(List<Person> personList) {
        return personList.stream().collect(
                Collectors.partitioningBy(person -> person.age > 50, Collectors.counting()));
    }

    @Override
    public Map<Boolean, List<Person>> partitionByFirstLetterOfFirstNameStartsWithB(List<Person> personList) {
        return personList.stream().collect(
                Collectors.partitioningBy(person -> Character.toLowerCase(person.firstName.charAt(0)) == 'b'));
    }

    @Override
    public long countAllFirstLetterOfFirstNameStartsWithB(List<Person> personList) {
        return partitionByFirstLetterOfFirstNameStartsWithB(personList).get(true).size();
    }

    @Override
    public Map<Integer, List<Person>> groupByLengthOfFullName(List<Person> personList) {
        return personList.stream().collect(Collectors.groupingBy(person -> person.getFullName().length()));
    }
}
