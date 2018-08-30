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

        Enumeration rentals = retals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(each.getCharge()) +"\n";
        }
        //add footer lines
        result += "Amount owed is "+ String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints())+
                " frequent renter points";
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Enumeration rentals = retals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = retals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
