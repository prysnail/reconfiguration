package com.prysnail.reconfiguration;

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
        int frequentRenterPoints = 0;
        Enumeration rentals = retals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()){
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            thisAmount = each.getCharge();

            //add frequent renter points
            frequentRenterPoints += each.getFrequentRenterPoints();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(thisAmount) +"\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Amount owed is "+ String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints)+
                " frequent renter points";
        return result;
    }


}
