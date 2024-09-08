

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author first
 */
package p;

import java.util.*;

class RentalRecord {
    int quantity;
    double feePerDay;
    int daysRented;

    RentalRecord(int quantity, double feePerDay, int daysRented) {
        this.quantity = quantity;
        this.feePerDay = feePerDay;
        this.daysRented = daysRented;
    }

    double calculateFee(int daysRented) {
        double fee = this.feePerDay * daysRented;
        if (daysRented < this.daysRented) {
            fee += 0.3 * this.feePerDay * (this.daysRented - daysRented);
        } else if (daysRented > this.daysRented) {
            fee += 0.5 * this.feePerDay * (daysRented - this.daysRented);
        }
        return fee;
    }
}

public class Tool {
    String name;
    double acquisitionCost;
    int quantity;
    LinkedList<RentalRecord> rentals;

    Tool(String name, double acquisitionCost) {
        this.name = name;
        this.acquisitionCost = acquisitionCost;
        this.quantity = 0;
        this.rentals = new LinkedList<>();
    }

    void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    String rent(int quantity, double feePerDay, int daysRented) {
        if (quantity > this.quantity) {
            return "NA";
        }
        this.quantity -= quantity;
        rentals.add(new RentalRecord(quantity, feePerDay, daysRented));
        return null;
    }

    double returnTool(int quantity, int daysRented) {
        if (quantity > getTotalRentedQuantity()) {
            return Double.NaN; // Not Available
        }
        double totalFee = 0;
        double totalSurcharge = 0;
        while (quantity > 0) {
            RentalRecord record = rentals.poll();
            if (quantity <= record.quantity) {
                totalFee += record.calculateFee(daysRented);
                totalFee = totalFee * quantity; 
                quantity = 0;
                record.quantity -= quantity;
                if (record.quantity > 0) {
                    rentals.addFirst(record);
                }
            } else {
                totalFee += record.calculateFee(daysRented);
                quantity -= record.quantity;
                
            }
        }
        return totalFee + totalSurcharge;
    }

    double returnDamaged(int quantity, double replacementCost) {
        
        if (quantity > this.quantity) {
            System.out.println("check on this file" + this.quantity);
            return Double.NaN; // Not Available
        }
//        this.quantity -= quantity;
        return quantity * (replacementCost - this.acquisitionCost);
    }

    double discard(int quantity) {
        if (quantity > this.quantity) {
            return Double.NaN; // Not Available
        }
        this.quantity -= quantity;
        
        return quantity * this.acquisitionCost;
    }

    int getTotalRentedQuantity() {
        int totalRented = 0;
        for (RentalRecord record : rentals) {
            totalRented += record.quantity;
        }
        return totalRented;
    }
}
