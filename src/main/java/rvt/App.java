package rvt;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
            /**
             * Read a CSV file containing order data and print a summary of each order along with the total purchase cost.
             * The CSV file is expected to have the following headers: OrderID, Customer, Product, Quantity, Price.
             * Each line in the CSV file represents an order with the respective details.
             * The program calculates the total cost for each order and accumulates the overall purchase cost.
             * If the file is not found or any other exception occurs, it prints an error message.
             * Example CSV content:
             * OrderID,Customer,Product,Quantity,Price
             * 1,John Doe,Widget,2,19.99
             * 2,Jane Smith,Gadget,1,29.99
             * 3,Bob Johnson,Thingamajig,5,9.99
             * Expected output:
             * Order #1: John Doe ordered 2 x Widget (19.99) -> Together: 39.98 EUR
             * Order #2: Jane Smith ordered 1 x Gadget (29.99) -> Together: 29.99 EUR
             * Order #3: Bob Johnson ordered 5 x Thingamajig (9.99) -> Together: 49.95 EUR
             * Total purchase cost: 119.92 EUR
             */

        String FILENAME = "data\\data.csv";

        try (Scanner scanner = new Scanner(new File(FILENAME));) {
            List<String> headers = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));
            int order_id_i = headers.indexOf("OrderID"); int customer_i = headers.indexOf("Customer"); int product_i = headers.indexOf("Product");
            int quantity_i = headers.indexOf("Quantity"); int price_i = headers.indexOf("Price");
            float purchase_cost = 0.0f;
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                System.out.printf("Order #%s: %s ordered %s x %s (%s) -> Together: %.2f EUR\n", parts[order_id_i], parts[customer_i], parts[quantity_i], parts[product_i], parts[price_i], Integer.valueOf(parts[quantity_i]) * Float.valueOf(parts[price_i]));
                purchase_cost += Integer.valueOf(parts[quantity_i]) * Float.valueOf(parts[price_i]);
            }
            System.out.println("Total purchase cost: " + purchase_cost + " EUR");
        }
        catch (FileNotFoundException e) {
            System.out.println("Ran into a problem! | Error: " + e);
        }
        catch (Exception e) {
            System.out.println();
        }
    }
}
