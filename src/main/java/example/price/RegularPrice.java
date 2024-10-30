package example.price;

public class RegularPrice extends Price {
    @Override
    public double getCharge(int daysRented) {
        if (daysRented > 2) {
            return 2 + (daysRented - 2) * 1.5;
        }
        return 2;
    }
}
