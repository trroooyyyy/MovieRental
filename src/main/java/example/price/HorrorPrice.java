package example.price;

public class HorrorPrice extends Price {
    @Override
    public double getCharge(int daysRented) {
        if (daysRented > 7) {
            return 3 + (daysRented - 7) * 2;
        }
        return 3;
    }

    @Override
    public int getFrequentRenterPointsIncrement(int daysRented) {
        if (daysRented > 3) {
            return 2;
        }
        return 1;
    }
}
