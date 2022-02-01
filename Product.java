/**
 * This class represent a line in Inventory.dat file
 */
import java.util.*;
public class Product 
{
    //TODO Add the following fields and a constructor to set the fields: 
    //                          Id (int, this is the unique product id)
    //                          Name (string)
    //                          Cost(double)
    //                          Quantity(int)
    //                          Margin(int as a percentage) 
	int id;
	String name;
	double cost;
	int quantity;
	int margin;
	

    public Product(int id, String name, double cost, int quantity, int margin)
    {
    	this.id = id;
    	this.name = name;
    	this.cost = cost;
    	this.quantity = quantity;
    	this.margin = margin;
    }
    public int getID() {
    	return id;
    }
    public String getName() {
    	return name;
    }
    public double getCost() {
    	return cost;
    }
    public int getQuantity() {
    	return quantity;
    }
    public int getMargin() {
    	return margin;
    }
    public String toString() {
    	return id + " " + name + " $" + cost + " " + quantity + " $" + (cost + margin * cost / 100);
    }
    
}