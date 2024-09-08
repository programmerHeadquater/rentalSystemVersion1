/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author first
 */
package p;

import java.io.*;
import java.util.*;

public class ToolRentalSystem {

    private static final Map<String, Tool> inventory = new HashMap<>();
//    private static final Map<String,String> toolPrice = new HashMap<>();
    private static double totalIncome = 0;
    private static double totalExpense = 0;

//    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Usage: java ToolRentalSystem <input_file>");
//            return;
//        }
//
//        String filename = args[0];
//        processCommands(filename);
//    }

    void processCommands(String line) {
        
        String[] command = line.trim().split("\\s+");
        String action = command[0];
        switch (action) {
                    case "ADD" -> handleAdd(command);
                    case "RENT" -> handleRent(command);
                    case "RETURN" -> handleReturn(command);
                    case "RETURN_DAMAGED" -> handleReturnDamaged(command);
                    case "DISCARD" -> handleDiscard(command);
                    case "CHECK" -> handleCheck();
                    case "PROFIT" -> handleProfit();
                    default -> System.out.println("Unknown command: " + action);
                }
        
        
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] command = line.trim().split("\\s+");
//                String action = command[0];
//
//                switch (action) {
//                    case "ADD" -> handleAdd(command);
//                    case "RENT" -> handleRent(command);
//                    case "RETURN" -> handleReturn(command);
//                    case "RETURN_DAMAGED" -> handleReturnDamaged(command);
//                    case "DISCARD" -> handleDiscard(command);
//                    case "CHECK" -> handleCheck();
//                    case "PROFIT" -> handleProfit();
//                    default -> System.out.println("Unknown command: " + action);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            
//        }
    }

    private static void handleAdd(String[] command) {
        String toolName = command[1];
        int quantity = Integer.parseInt(command[2]);
        double acquisitionCost = Double.parseDouble(command[3]);
        
        
        
        Tool tool = inventory.getOrDefault(toolName, new Tool(toolName, acquisitionCost));
        System.out.println("tool in inverntory get or default : " + tool.name + " " + tool.acquisitionCost);
        tool.addQuantity(quantity);
        inventory.put(toolName, tool);
    }

    private static void handleRent(String[] command) {
        String toolName = command[1];
        int quantity = Integer.parseInt(command[2]);
        double rentFeePerDay = Double.parseDouble(command[3]);
        int rentDays = Integer.parseInt(command[4]);

        Tool tool = inventory.get(toolName);
        if (tool == null) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
        String result = tool.rent(quantity, rentFeePerDay, rentDays);
        if ("NA".equals(result)) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
    }

    private static void handleReturn(String[] command) {
        String toolName = command[1];
        int quantity = Integer.parseInt(command[2]);
        int daysRented = Integer.parseInt(command[3]);

        Tool tool = inventory.get(toolName);
        if (tool == null) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
        double result = tool.returnTool(quantity, daysRented);
        if (Double.isNaN(result)) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
        totalIncome += result;
        inventory.get(toolName).quantity = inventory.get(toolName).quantity + quantity;
    }

    private static void handleReturnDamaged(String[] command) {
        String toolName = command[1];
        int quantity = Integer.parseInt(command[2]);
        double replacementCost = Double.parseDouble(command[3]);

        Tool tool = inventory.get(toolName);
        
        if (tool == null) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
        double result = tool.returnDamaged(quantity, replacementCost);
        if (Double.isNaN(result)) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
        totalIncome += result;
    }

    private static void handleDiscard(String[] command) {
        String toolName = command[1];
        int quantity = Integer.parseInt(command[2]);

        Tool tool = inventory.get(toolName);
        if (tool == null) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
        double result = tool.discard(quantity);
        System.out.println("this is result : " + result);
        if (Double.isNaN(result)) {
            System.out.println("Profit/Loss: NA");
            System.exit(1);
        }
//        System.out.println("this is totalExpense  : " + totalExpense);

        totalExpense += result;
//        System.out.println("this is totalExpense + result : " + totalExpense);

    }

    private static void handleCheck() {
        for (Map.Entry<String, Tool> entry : inventory.entrySet()) {
            Tool tool = entry.getValue();
            System.out.println(tool.name + ": " + tool.quantity);
        }
    }

    private static void handleProfit() {
        double totalProfit = totalIncome - totalExpense;
        System.out.printf("Profit/Loss: $%.2f%n", totalProfit);
    }
}
