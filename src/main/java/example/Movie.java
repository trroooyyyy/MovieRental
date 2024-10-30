package example;

import example.price.Price;


public class Movie {
    private final String title;
    private final Price price;

    public Movie(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public String getTitle (){
        return title;
    }


}