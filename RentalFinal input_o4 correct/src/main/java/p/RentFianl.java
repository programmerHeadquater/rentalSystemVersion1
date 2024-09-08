/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package p;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author first
 */

public class RentFianl {
    
    public static void main(String[] args) {
    ToolRentalSystem toolRentalSystem = new ToolRentalSystem();
    String filePath = "src/file/input_04.txt";
        
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
//            System.out.println("running");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                
                toolRentalSystem.processCommands(line);
                toolRentalSystem.processCommands("CHECK");
                toolRentalSystem.processCommands("PROFIT");
                System.out.println();

                

                
                
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
