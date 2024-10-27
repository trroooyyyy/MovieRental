package example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerTest {
    private Customer customer;
    private List<Rental> rentals;

    @Before
    public void setUp() {
        rentals = new ArrayList<>();
        customer = new Customer("Arthur Netrebin", rentals);
    }

    @Test
    public void testRegularMovieOneDayRental() {
        rentals.add(new Rental(new Movie("Rembo", Movie.MovieType.REGULAR), 1));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 2.0"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testRegularMovieMoreThanTwoDaysRental() {
        rentals.add(new Rental(new Movie("Rembo", Movie.MovieType.REGULAR), 5));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 6.5"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testNewReleaseMovieOneDayRental() {
        rentals.add(new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 1));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 3.0"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testNewReleaseMovieMultipleDaysRental() {
        rentals.add(new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 3));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 9.0"));
        assertTrue(statement.contains("You earned 2 frequent renter points"));
    }

    @Test
    public void testChildrenMovieUpToThreeDaysRental() {
        rentals.add(new Rental(new Movie("Harry Potter", Movie.MovieType.CHILDRENS), 3));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testChildrenMovieMoreThanThreeDaysRental() {
        rentals.add(new Rental(new Movie("Harry Potter", Movie.MovieType.CHILDRENS), 5));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 4.5"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testMultipleRentals() {
        rentals.addAll(List.of(
                new Rental(new Movie("Rembo", Movie.MovieType.REGULAR), 1),
                new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 4),
                new Rental(new Movie("Harry Potter", Movie.MovieType.CHILDRENS), 5)
        ));

        String statement = customer.statement();
        assertEquals("""
                Rental Record for Arthur Netrebin
                \tRembo\t2.0
                \tLord of the Rings\t12.0
                \tHarry Potter\t4.5
                Amount owed is 18.5
                You earned 4 frequent renter points""", statement);
    }

    @Test
    public void testNoRentals() {
        String statement = customer.statement();
        assertEquals("""
                Rental Record for Arthur Netrebin
                Amount owed is 0.0
                You earned 0 frequent renter points""", statement);
    }

    @Test
    public void testBonusFrequentRenterPointsForMultipleNewReleases() {
        rentals.addAll(List.of(
                new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 2),
                new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 3)
        ));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 15.0"));
        assertTrue(statement.contains("You earned 4 frequent renter points"));
    }


    @Test
    public void testBonusForTwoNewReleasesSameDay() {
        rentals.addAll(List.of(
                new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 1),
                new Rental(new Movie("Lord of the Rings", Movie.MovieType.NEW_RELEASE), 1)
        ));

        String statement = customer.statement();
        assertTrue(statement.contains("Amount owed is 6.0"));
        assertTrue(statement.contains("You earned 2 frequent renter points"));
    }
}
