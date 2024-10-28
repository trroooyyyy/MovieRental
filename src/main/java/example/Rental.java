package example;

import static example.Movie.MovieType.NEW_RELEASE;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    double getCharge() {
        double charge = 0;
        //determine amounts for rental line
        switch (getMovie().getPriceCode()) {
            case REGULAR -> {
                charge += 2;
                if (getDaysRented() > 2)
                    charge += (getDaysRented() - 2) * 1.5;
            }
            case NEW_RELEASE -> charge += getDaysRented() * 3;
            case CHILDRENS -> {
                charge += 1.5;
                if (getDaysRented() > 3)
                    charge += (getDaysRented() - 3) * 1.5;
            }
        }
        return charge;
    }

    int getFrequentRenterPointsIncrement() {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == NEW_RELEASE) && getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
