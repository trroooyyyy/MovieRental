package example.price;



public abstract class Price {
    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPointsIncrement(int daysRented) {
        return 1;
    }
}