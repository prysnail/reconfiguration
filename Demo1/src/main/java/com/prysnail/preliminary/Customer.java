package com.prysnail.preliminary;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class Customer {
    private String name;
    private double rentalAmount;
    private int renterPoints;
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
        rentalAmount = 0;
        renterPoints = 0;
        Enumeration rentals = retals.elements();
        Vector<Double> amountVector = new Vector<>();

        while (rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental myRental = (Rental) rentals.nextElement();
            thisAmount += calculateRentalFee(myRental);
            renterPoints++;
            if (myRental.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                    myRental.getDaysRented() > 1)
                renterPoints++;
            amountVector.add(thisAmount);
            rentalAmount += thisAmount;
        }
        return htmlState(amountVector);
    }

    private double calculateRentalFee(Rental myRental){
        double amount = 0.0;
        switch (myRental.getMovie().getPriceCode()){
            case Movie.REGULAR:
                amount += 2;
                if (myRental.getDaysRented() >2)
                    amount += (myRental.getDaysRented() -2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += myRental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (myRental.getDaysRented() >3)
                    amount += (myRental.getDaysRented() -3) * 1.5;
                break;
        }
        return amount;
    }

    private String htmlState (Vector amountVector){
        String result = "Rental Record for " + getName() + "\n";

        Enumeration rentals = retals.elements();
        Enumeration amounts = amountVector.elements();
        while (rentals.hasMoreElements()) {
            Rental myRental = (Rental) rentals.nextElement();
            Double amount = (Double) amounts.nextElement();
            result += "\t" + myRental.getMovie().getTitle()+ "\t" + String.valueOf(amount) +"\n";
        }
        result += "Amount owed is "+ String.valueOf(rentalAmount) + "\n";
        result += "You earned " + String.valueOf(renterPoints)+
                " frequent renter points";
        return result;
    }
}
