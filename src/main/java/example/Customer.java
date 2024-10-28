package example;

import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }


    public String getName() {
        return name;
    }

    public String statement() {

        String result = "Rental Record for " + getName() + "\n";

        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle()+ "\t" + rental.getCharge() + "\n";
        }
        //add footer lines
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    private double getTotalCharge() {
        return rentals.stream()
                .mapToDouble(Rental::getCharge)
                .sum();
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::getFrequentRenterPointsIncrement)
                .sum();
    }


}
