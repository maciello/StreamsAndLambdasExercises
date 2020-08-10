package streamsandlambdas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static streamsandlambdas.Gender.FEMALE;
import static streamsandlambdas.Gender.MALE;

class StreamsExerciseTest {
    private static final int PERSON_AMOUNT = 100;
    private static final StreamsExercise streamsExercise = new StreamsExerciseSolution();
    private static List<Person> personList;

    @BeforeEach
    public void init() {
        personList = Person.generatePeople(PERSON_AMOUNT);
    }

    @AfterEach
    public void tearDown() {
        Person.c = 0;
    }

    @org.junit.jupiter.api.Test
    void listOfAllMalesSortedByFirstNameThenLastName() {
        List<Person> expected = personList.stream().filter(person -> person.gender.equals(MALE)).sorted(
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)).collect(
                Collectors.toList());

        List<Person> actual = streamsExercise.listOfAllMalesSortedByFirstNameAndLastName(personList);

        testListWithPredicateAndComparator(actual, person -> person.gender.equals(MALE),
                                           Comparator.comparing(Person::getLastName).thenComparing(
                                                   Person::getFirstName), "List contains females.");
    }

    @org.junit.jupiter.api.Test
    void listOfAllFemalesSortedByFirstNameThenLastName() {
        List<Person> actual = streamsExercise.listOfAllFemalesSortedByFirstNameAndLastName(personList);

        testListWithPredicateAndComparator(actual, person -> person.gender.equals(FEMALE),
                                           Comparator.comparing(Person::getLastName).thenComparing(
                                                   Person::getFirstName), "List contains males.");
    }

    @org.junit.jupiter.api.Test
    void listOfAllBurgerEatersSortedByAgeFromLowToHigh() {
        List<Person> actual = streamsExercise.listOfAllBurgerEatersSortedByAgeFromLowToHigh(personList);

        testListWithPredicateAndComparator(actual, person -> person.favouriteFood.equals(FavouriteFood.BURGER),
                                           Comparator.comparingInt(Person::getAge), "contains non-burgerEaters");
    }

    @org.junit.jupiter.api.Test
    void listOfAllStudentsSortedById() {
        List<Person> actual = streamsExercise.listOfAllStudentsSortedById(personList);

        testListWithPredicateAndComparator(actual, person -> person instanceof Person.Student,
                                           Comparator.comparingInt(Person::getId), "Contains non-Students");
    }

    @org.junit.jupiter.api.Test
    void listOfAllPersonsWithNameBOBsortedById() {
        List<Person> actual = streamsExercise.listOfAllPersonsWithNameBOBsortedById(personList);
        testListWithPredicateAndComparator(actual, person -> person.firstName.equalsIgnoreCase("BOB"),
                                           Comparator.comparingInt(Person::getId), "Contains a non-'Bob'-person");
    }

    @org.junit.jupiter.api.Test
    void listOfAllStudentsUnder20YearsOldSortedById() {
        List<Person> actual = streamsExercise.listOfAllStudentsUnder20YearsOldSortedById(personList);
        testListWithPredicateAndComparator(actual, person -> person instanceof Person.Student && person.age < 20,
                                           Comparator.comparingInt(Person::getId),
                                           "List contains person over 19 or not a student.");
    }

    @org.junit.jupiter.api.Test
    void listOfAllTeachersOver50YearsOldSortedById() {
        List<Person> actual = streamsExercise.listOfAllTeachersOver50YearsOldSortedById(personList);
        testListWithPredicateAndComparator(actual, person -> person instanceof Person.Teacher && person.age > 50,
                                           Comparator.comparingInt(Person::getId),
                                           "List contains person not over 50 or not a teacher.");
    }

    @org.junit.jupiter.api.Test
    void listOfAllFemaleStudentsThatLikePizzaAndAreBetween18And30YearsOldWithNameMARYsortById() {
        List<Person> actual =
                streamsExercise.listOfAllFemaleStudentsThatLikePizzaAndAreBetween18And30YearsOldWithNameMARYsortById(
                        personList);
        Predicate<Person> isFemale = person -> person.gender.equals(FEMALE);
        Predicate<Person> isStudent = person -> person instanceof Person.Student;
        Predicate<Person> likesPizza = person -> person.favouriteFood.equals(FavouriteFood.PIZZA);
        Predicate<Person> isBetween18and30YearsOld = person -> person.age > 18 && person.age < 30;
        Predicate<Person> nameIsMARY = person -> person.firstName.equalsIgnoreCase("mary");

        failIfContains(actual, isFemale.negate(), "contains Male");
        failIfContains(actual, isStudent.negate(), "contains non-Student");
        failIfContains(actual, likesPizza.negate(), "contains non-Pizza-Lover");
        failIfContains(actual, isBetween18and30YearsOld.negate(), "contains person (18 or younger) OR (30 or older)");
        failIfContains(actual, nameIsMARY.negate(), "contains person whose name is not 'MARY'");

        testListWithPredicateAndComparator(actual,
                                           isFemale.and(isStudent).and(likesPizza).and(isBetween18and30YearsOld).and(
                                                   nameIsMARY), Comparator.comparingInt(Person::getId),
                                           "contains a wrong person");
    }

    @org.junit.jupiter.api.Test
    void listOfAllPersonsWithFirstNameJACOBandLastNameSMITH() {
        List<Person> actual = streamsExercise.listOfAllPersonsWithFirstNameJACOBandLastNameSMITH(personList);
        Predicate<Person> firstNameIsJACOB = person -> person.firstName.equalsIgnoreCase("jacob");
        Predicate<Person> lastNameIsSMITH = person -> person.lastName.equalsIgnoreCase("smith");

        failIfContains(actual, firstNameIsJACOB.negate(), "contains Male");
        failIfContains(actual, lastNameIsSMITH.negate(), "contains non-Student");
        testListWithPredicateAndComparator(actual, firstNameIsJACOB.and(lastNameIsSMITH),
                                           Comparator.comparingInt(Person::getId), "List contains a wrong person");
    }

    @org.junit.jupiter.api.Test
    void listOfOldestStudents() {
        int maxAge = personList.stream().mapToInt(Person::getAge).max().getAsInt();
        List<Person> actual = streamsExercise.listOfOldestStudents(personList);

        // all who don't fulfill predicate
        Predicate<Person> isMaxAge = person -> person.age == maxAge;
        failIfContains(actual, isMaxAge.negate(), "contains Students, that don't have highest age");

        // number of Persons who fulfill predicate
        testSizeOfCollectionWithPredicate(actual, isMaxAge);

        collectionTest(actual);
    }

    @org.junit.jupiter.api.Test
    void numberOfPeoplePerFavouriteFood() {
        Map<FavouriteFood, Long> actual = streamsExercise.numberOfPeoplePerFavouriteFood(personList);

        boolean actualContainsAllFoods =
                personList.stream().map(Person::getFavouriteFood).distinct().allMatch(actual::containsKey);
        assertTrue(actualContainsAllFoods, "your map misses Foods");

        long sum = actual.values().parallelStream().mapToLong(Long::longValue).sum();
        assertEquals(personList.size(), sum, "you missed some people, or added some extra.");

        Map<FavouriteFood, Long> expected =
                personList.stream().collect(Collectors.groupingBy(Person::getFavouriteFood, Collectors.counting()));
        boolean allValuesMatch = expected.entrySet().stream().allMatch(
                stringListEntry -> actual.get(stringListEntry.getKey()).equals(stringListEntry.getValue()));
        assertTrue(allValuesMatch, "Number of people don't match.");
    }

    @org.junit.jupiter.api.Test
    void numberOfPeoplePerFirstName() {
        Map<String, Long> actual = streamsExercise.numberOfPeoplePerFirstName(personList);

        boolean actualContainsAllNames =
                personList.stream().map(Person::getFirstName).distinct().allMatch(actual::containsKey);
        assertTrue(actualContainsAllNames, "your map misses names.");

        long sum = actual.values().parallelStream().mapToLong(Long::longValue).sum();
        assertEquals(personList.size(), sum, "you missed some people, or added some extra.");

        Map<String, Long> expected =
                personList.stream().collect(Collectors.groupingBy(Person::getFirstName, Collectors.counting()));
        boolean allValuesMatch = expected.entrySet().stream().allMatch(
                stringListEntry -> actual.get(stringListEntry.getKey()).equals(stringListEntry.getValue()));
        assertTrue(allValuesMatch, "Number of people don't match.");
    }

    @org.junit.jupiter.api.Test
    void numberOfPeoplePerAge() {
        Map<Integer, Long> actual = streamsExercise.numberOfPeoplePerAge(personList);

        boolean actualContainsAllNames =
                personList.stream().map(Person::getAge).distinct().allMatch(actual::containsKey);
        assertTrue(actualContainsAllNames, "your map misses ages.");

        long sum = actual.values().parallelStream().mapToLong(Long::longValue).sum();
        assertEquals(personList.size(), sum, "you missed some people, or added some extra.");

        Map<Integer, Long> expected =
                personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        boolean allValuesMatch = expected.entrySet().stream().allMatch(
                stringListEntry -> actual.get(stringListEntry.getKey()).equals(stringListEntry.getValue()));
        assertTrue(allValuesMatch, "Number of people don't match.");
    }

    @org.junit.jupiter.api.Test
    void groupPeopleByGender() {
        Map<Gender, List<Person>> actual = streamsExercise.groupPeopleByGender(personList);
        boolean maleListContainsFemale = actual.get(MALE).stream().anyMatch(person -> person.gender.equals(FEMALE));
        boolean femaleListContainsMale = actual.get(FEMALE).stream().anyMatch(person -> person.gender.equals(MALE));

        assertFalse(maleListContainsFemale, "male list contains female");
        assertFalse(femaleListContainsMale, "female list contains male");

        Map<Gender, List<Person>> expected = personList.stream().collect(Collectors.groupingBy(Person::getGender));

        assertEquals(expected.get(MALE).size(), actual.get(MALE).size(), "number of don't match");
        assertEquals(expected.get(FEMALE).size(), actual.get(FEMALE).size(), "number of females don't match");

        List<Person> elements =
                actual.entrySet().stream().flatMap(genderListEntry -> genderListEntry.getValue().stream()).collect(
                        Collectors.toList());
        collectionTest(elements);
    }

    @org.junit.jupiter.api.Test
    void numberOfTeachersOver70() {
        int actual = streamsExercise.numberOfTeachersOver70(personList);
        int expected = personList.stream().filter(person -> person instanceof Person.Teacher).filter(
                teacher -> teacher.age > 70).mapToInt(Person::getAge).sum();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void averageAgeOfStudents() {
        double actual = streamsExercise.averageAgeOfStudents(personList);
        double expected = personList.stream().filter(person -> person instanceof Person.Student).mapToInt(
                Person::getAge).average().getAsDouble();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void minAgeOfTeachers() {
        double actual = streamsExercise.minAgeOfTeachers(personList);
        double expected = personList.stream().filter(person -> person instanceof Person.Teacher).mapToInt(
                Person::getAge).min().getAsInt();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void numberOfStudentsAged27() {
        double actual = streamsExercise.numberOfStudentsAged27(personList);
        double expected = personList.stream().filter(person -> person instanceof Person.Student).filter(
                person -> person.age == 27).mapToInt(Person::getAge).sum();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void partitionByIsTeacher() {
        Map<Boolean, List<Person>> actual = streamsExercise.partitionByIsTeacher(personList);
        boolean teacherListContainsNonTeacher =
                actual.get(true).stream().anyMatch(person -> !(person instanceof Person.Teacher));
        boolean NonTeacherListContainsTeacher =
                actual.get(false).stream().anyMatch(person -> person instanceof Person.Teacher);

        assertFalse(teacherListContainsNonTeacher, "teacher list contains non-teacher");
        assertFalse(NonTeacherListContainsTeacher, "non-teacher list contains teacher");

        Map<Boolean, List<Person>> expected =
                personList.stream().collect(Collectors.partitioningBy(person -> person instanceof Person.Teacher));

        assertEquals(expected.get(true).size(), actual.get(true).size(), "number of teachers don't match");
        assertEquals(expected.get(false).size(), actual.get(false).size(), "number of non-teachers don't match");

        List<Person> elements =
                actual.entrySet().stream().flatMap(booleanListEntry -> booleanListEntry.getValue().stream()).collect(
                        Collectors.toList());
        collectionTest(elements);
    }

    @org.junit.jupiter.api.Test
    void partitionByAgeOver50AndCount() {
        Map<Boolean, Long> actual = streamsExercise.partitionByAgeOver50AndCount(personList);
        Map<Boolean, Long> expected = personList.stream().collect(
                Collectors.partitioningBy(person -> person.getAge() > 50, Collectors.counting()));

        assertEquals(expected.get(true), actual.get(true), "wrong count for number of people over 50");
        assertEquals(expected.get(false), actual.get(false), "wrong count for number of people '50 or below'");
    }

    @org.junit.jupiter.api.Test
    void partitionByFirstLetterOfFirstNameStartsWithB() {
        Map<Boolean, List<Person>> actual = streamsExercise.partitionByFirstLetterOfFirstNameStartsWithB(personList);
        Predicate<Person> firstNameStartsWithB = person -> Character.toLowerCase(person.firstName.charAt(0)) == 'b';

        boolean bListContainsNonB = actual.get(true).stream().anyMatch(firstNameStartsWithB.negate());
        boolean nonBListContainsB = actual.get(false).stream().anyMatch(firstNameStartsWithB);

        System.out.println(actual.get(true).stream().filter(firstNameStartsWithB).collect(Collectors.toList()));

        assertFalse(bListContainsNonB, "B-list contains non-B person");
        assertFalse(nonBListContainsB, "non-B-list contains B person");

        Map<Boolean, List<Person>> expected =
                personList.stream().collect(Collectors.partitioningBy(firstNameStartsWithB));

        assertEquals(expected.get(true).size(), actual.get(true).size(), "number of B persons don't match");
        assertEquals(expected.get(false).size(), actual.get(false).size(), "number of non-B persons don't match");

        List<Person> elements =
                actual.entrySet().stream().flatMap(booleanListEntry -> booleanListEntry.getValue().stream()).collect(
                        Collectors.toList());
        collectionTest(elements);
    }

    @org.junit.jupiter.api.Test
    void countAllFirstLetterOfFirstNameStartsWithB() {
        long actual = streamsExercise.countAllFirstLetterOfFirstNameStartsWithB(personList);
        Predicate<Person> firstNameStartsWithB = person -> Character.toLowerCase(person.firstName.charAt(0)) == 'b';
        long expected = personList.stream().filter(firstNameStartsWithB).count();

        assertEquals(expected, actual, "counts don't match");
    }

    @org.junit.jupiter.api.Test
    void groupByLengthOfFullName() {
        Map<Integer, List<Person>> actual = streamsExercise.groupByLengthOfFullName(personList);
        Consumer<Map.Entry<Integer, List<Person>>> testNameLengthForEachEntry = integerListEntry -> {
            integerListEntry.getValue().stream().forEach(
                    person -> assertEquals(integerListEntry.getKey(), person.getFullName().length(),
                                           "Wrong length of name in list. list number=" + integerListEntry.getKey() +
                                                   ", name=" + person.getFullName()));
        };
        actual.entrySet().stream().forEach(testNameLengthForEachEntry);

        int sumOfPeople = actual.values().stream().mapToInt(Collection::size).sum();
        assertEquals(personList.size(), sumOfPeople, "sum of people don't match");

        // test for duplicates and new elements
        List<Person> elements =
                actual.entrySet().stream().flatMap(integerListEntry -> integerListEntry.getValue().stream()).collect(
                        Collectors.toList());
        collectionTest(elements);
    }

    private void testSizeOfCollectionWithPredicate(List<Person> actual, Predicate<Person> personPredicate) {
        long size = personList.stream().filter(personPredicate).count();
        assertEquals(size, actual.size(), "size is wrong. expected: " + size + ", but was: " + actual.size());
    }

    private void testListWithPredicateAndComparator(List<Person> actual, Predicate<Person> predicate,
                                                    Comparator<Person> comparator, String containsMessage) {
        // all who don't fulfill predicate
        failIfContains(actual, predicate.negate(), containsMessage);

        // number of Persons who fulfill predicate
        testSizeOfCollectionWithPredicate(actual, predicate);

        // sorted
        testSorted(actual, comparator);

        collectionTest(actual);
    }

    private void testSorted(List<Person> actual, Comparator<Person> comparator) {
        List<Person> sorted = actual.stream().sorted(comparator).collect(Collectors.toList());
        assertIterableEquals(sorted, actual, "Collection not sorted correctly.");
    }

    private void failIfContains(List<Person> actual, Predicate<Person> personPredicate, String message) {
        boolean containsNonBurgerEaters = actual.stream().anyMatch(personPredicate);
        assertFalse(containsNonBurgerEaters, message);
    }

    private void collectionTest(List<Person> actual) {
        testNewPerson(actual);
        testDuplicate(actual);
    }

    private void testNewPerson(List<Person> actual) {
        // id
        if (actual.isEmpty()) {
            return;
        }
        int maxId = actual.stream().mapToInt(person -> person.getId()).max().getAsInt();
        boolean maxIdTooHigh = maxId > PERSON_AMOUNT;
        assertFalse(maxIdTooHigh, "Max Id too high. Please don't try to add a new Person to the Collection.");
    }

    private void testDuplicate(List<Person> actual) {
        // duplicate
        long count = actual.stream().count();
        long distinctCount = actual.stream().distinct().count();
        assertEquals(count, distinctCount, "You have duplicates in your Collection.");
    }
}