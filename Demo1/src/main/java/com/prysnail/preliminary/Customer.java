package com.prysnail.preliminary;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector retals = new Vector();

    public Customer(String _name) {
        this.name = _name;
    }

    public void addRental(Rental arg){
        retals.add(arg);
    }

    public String getName() {
        return name;
    }

    //需要重构方法
    public String statement(){
        double totalAmount = 0;
        int renterPoints = 0;
        Enumeration rentals = retals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental myRental = (Rental) rentals.nextElement();

            switch (myRental.getMovie().getPriceCode()){
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (myRental.getDaysRented() >2)
                        thisAmount += (myRental.getDaysRented() -2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += myRental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (myRental.getDaysRented() >3)
                        thisAmount += (myRental.getDaysRented() -3) * 1.5;
                    break;
            }

            renterPoints++;

            if (myRental.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                    myRental.getDaysRented() > 1) renterPoints++;

            result += "\t" + myRental.getMovie().getTitle()+ "\t" + String.valueOf(thisAmount) +"\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is "+ String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(renterPoints)+
                " frequent renter points";
        return result;
    }
}
