package com.prysnail.reconfiguration;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE =1;

    private String title;
    private int priceCode;
    private Price price;

    public Movie(String title, int priceCode) throws IllegalAccessException {
        title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) throws IllegalAccessException {
        switch (arg){
            case REGULAR:
                price = new RegularPrice();
            case CHILDRENS:
                price = new ChildrenPrice();
            case NEW_RELEASE:
                price = new NewReleasePrice();
            default:
                throw new IllegalAccessException("Incorrect Price Code");
        }
    }
    
    public double getCharge(int daysRented){
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented){
        return price.getFrequentRenterPoints(daysRented);
    }
}
