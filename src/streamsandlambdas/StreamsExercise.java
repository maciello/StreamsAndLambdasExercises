package streamsandlambdas;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ideas:
 * ExtractInformation
 * List of all Males, sorted by firstName then lastname
 * List of all Females, sorted by firstName then lastname
 * List of all Burger-Eaters, sorted by age from lowest to highest
 * List of all Students, sorted by id
 * List of all Persons with the name "BOB", sorted by lastName
 * List of all Students under 20 years old, sorted by age
 * List of all Teachers over 50 years old, sorted by age
 * List of all Female Students that like Pizza and are between 18 and 30 years old with the name "MARY", sort by age
 * List of all Persons with firstname "Jacob" and lastName "Smith"
 * List of Oldest Students
 * (List of Top 10 avg grade of Students)
 * Number of People per FavouriteFood as Map
 * Number of People per firstName
 * Number of People per Age
 * Partition People by Gender
 * Number of Teachers over 70 --> 0 hehe
 */
public class StreamsExercise {

    // List of all Males, sorted by firstName then lastname
    public List<Person> listOfAllMalesSortedByFirstNameAndLastName(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Females, sorted by firstName then lastname
    public List<Person> listOfAllFemalesSortedByFirstNameAndLastName(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Burger-Eaters, sorted by age from lowest to highest
    public List<Person> listOfAllBurgerEatersSortedByAgeFromLowToHigh(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Students, sorted by id
    public List<Person> listOfAllStudentsSortedById(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Persons with the name "BOB", sorted by id
    public List<Person> listOfAllPersonsWithNameBOBsortedById(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Students under 20 years old, sorted by id
    public List<Person> listOfAllStudentsUnder20YearsOldSortedById(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Teachers over 50 years old, sorted by id
    public List<Person> listOfAllTeachersOver50YearsOldSortedById(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Female Students that like Pizza and are between 18 and 30 years old with the name "MARY", sort by id
    public List<Person> listOfAllFemaleStudentsThatLikePizzaAndAreBetween18And30YearsOldWithNameMARYsortById(
            List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of all Persons with firstname "Jacob" and lastName "Smith", sort by id
    public List<Person> listOfAllPersonsWithFirstNameJACOBandLastNameSMITH(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // List of Oldest Students, sort by id
    public List<Person> listOfOldestStudents(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // Number of People per FavouriteFood as Map
    public Map<FavouriteFood, Long> numberOfPeoplePerFavouriteFood(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // Number of People per firstName
    public Map<String, Long> numberOfPeoplePerFirstName(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // Number of People per Age
    public Map<Integer, Long> numberOfPeoplePerAge(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // Partition People by Gender, sort by id
    public Map<Gender, List<Person>> groupPeopleByGender(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // Number of Teachers over 70
    public int numberOfTeachersOver70(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // average age of students
    public double averageAgeOfStudents(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // min age of teachers
    public double minAgeOfTeachers(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // number of students aged 27
    public double numberOfStudentsAged27(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // partition Teachers and non-teachers
    public Map<Boolean, List<Person>> partitionByIsTeacher(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // partition by age > 50 and count in int
    public Map<Boolean, Long> partitionByAgeOver50AndCount(List<Person> personList) {
        Map<Boolean, Long> collect = personList.stream().collect(
                Collectors.partitioningBy(person -> ((Person) person).age > 50, Collectors.counting()));
        throw new RuntimeException("not implemented.");
    }

    // partition by "First Letter of First Name Starts with B"
    public Map<Boolean, List<Person>> partitionByFirstLetterOfFirstNameStartsWithB(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // count "First Letter of First Name Starts with B"
    public long countAllFirstLetterOfFirstNameStartsWithB(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }

    // Group by Length of FullName
    public Map<Integer, List<Person>> groupByLengthOfFullName(List<Person> personList) {
        throw new RuntimeException("not implemented.");
    }
}
