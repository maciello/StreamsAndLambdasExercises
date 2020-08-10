package streamsandlambdas;

public class RandomPersonData {
    public RandomPersonData() {
    }

    int getRandomAgeBetween(int from, int to) {
        return from + Person.R.nextInt(to-from + 1);
    }

    Gender getRandomGender() {
        return Gender.values()[Person.R.nextInt(2)];
    }

    String getRandomFirstName(Gender gender) {
        return NamesData.names.getRandomFirstName(gender);
    }

    String getRandomLastName() {
        return NamesData.names.getRandomLastName();
    }

    FavouriteFood getRandomFavouriteFood() {
        return FavouriteFood.values()[Person.R.nextInt(FavouriteFood.values().length)];
    }
}