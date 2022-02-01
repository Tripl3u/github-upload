import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * ProductCatalog
 */
public class ProductCatalog {

    //TODO implement the ProductCatalog methods and fields. Add a collection to hold Product objects
	ArrayList<Product> pList;
    public ProductCatalog() throws IOException 
    {
    	pList = new ArrayList<Product>();
    	File invFile = new File("Inventory.dat");
    	if(!invFile.exists()) {
    		invFile.createNewFile();
    	}
    	Scanner scan = new Scanner(invFile);
    	while(scan.hasNext()) {
    		String [] list = scan.nextLine().split(", ");  
    		Product tempProduct = new Product(Integer.parseInt(list[0]), list[1], Double.parseDouble(list[2]), Integer.parseInt(list[3]), Integer.parseInt(list[4]));
    		pList.add(tempProduct);
    	}	
    	scan.close();
    }
    //Add or update a product if already exists
    public void AddUpdateProduct(Product product) throws IOException
    {
    	//pList = null;
    	File tempFile = new File("TempFile.txt");
    	FileWriter writer = new FileWriter(tempFile, true);
        String tempString = product.getID() + ", " + product.getName() + ", " + product.getCost() + ", " + product.getQuantity() + ", " + product.getMargin();
        File invFile = new File("Inventory.dat");
        Scanner scan = new Scanner(invFile);
     	while(scan.hasNext()) {
     		 String temp = scan.nextLine();
     		 String [] list = temp.split(", ");
    		 if(product.getID() != Integer.parseInt(list[0])) {
    			 	writer.write(temp);
    		 }
    		 
    	}
     	writer.write(tempString);
     	for(int i = 0; i < pList.size(); i++) {
     		if(pList.get(i).getID() == product.getID()) {
     			pList.remove(i);
     		}
     	}
     	pList.add(product);
     	writer.close(); 
    	scan.close(); 
    	tempFile.renameTo(invFile);
    }

    //TODO create an overload of the AddUpdateProduct method
    public void AddUpdateProduct(int id, String name, double cost, int quantity, int margin) throws IOException 
    {
    	Product tempProduct = new Product(id, name, cost, quantity, margin);
    	AddUpdateProduct(tempProduct);
    }

    public boolean RemoveProduct(int productId) throws IOException
    {
        boolean success = false; 
        File tempFile = new File("TempFile.txt");
    	FileWriter writer = new FileWriter(tempFile, true);
        File invFile = new File("Inventory.dat");
        Scanner scan = new Scanner(invFile);
     	while(scan.hasNext()) {
     		 String temp = scan.nextLine();
     		 String [] list = temp.split(", "); 
    		 if(productId != Integer.parseInt(list[0])) {
    			 	writer.write(temp);
    		 }
    	}
     	for(int i = 0; i < pList.size(); i++) {
     		if(pList.get(i).getID() == productId) {
     			pList.remove(i);
     			success = true;
     		}
     	}
     	writer.close(); 
    	scan.close(); 
    	tempFile.renameTo(invFile);
        return success; 
    }

    //TODO create an overload of the RemoveProduct method to remove by product name
    public boolean RemoveProduct(String name) throws IOException
    {
    	boolean success = false; 
        File tempFile = new File("TempFile.txt");
    	FileWriter writer = new FileWriter(tempFile, true);
        File invFile = new File("Inventory.dat");
        Scanner scan = new Scanner(invFile);
     	while(scan.hasNext()) {
     		 String temp = scan.nextLine();
     		 String [] list = temp.split(", "); //splitting user data 
    		 if(!name.equals(list[1])) {
    			 	writer.write(temp);
    		 }
    		 
    	}
     	for(int i = 0; i < pList.size(); i++) {
     		if(pList.get(i).getName().equals(name)) {
     			pList.remove(i);
     			success = true;
     		}
     	}
     	writer.close(); 
    	scan.close(); 
    	tempFile.renameTo(invFile);
        return success; 
    }

    public boolean FindProduct(int productId) throws IOException
    {
    	boolean success = false; 
     	for(int i = 0; i < pList.size(); i++) {
     		if(pList.get(i).getID() == productId) {
     			success = true;
     		}
     	}
        return success; 
    }

    //TODO create an overload of the FindProduct method to find product by product name
    public boolean FindProduct(String name)
    {
        boolean success = false; 
        for(int i = 0; i < pList.size(); i++) {
     		if(pList.get(i).getName().equals(name)) {
     			success = true;
     		}
     	}
        return success; 
    }

    //Print information about a product including retail price (cost + (margin*cost/100))
    public String PrintProductInformation(int productId)
    {
    	String productInformation = null;
        for(int i=0;i<pList.size();i++) {
            if(pList.get(i).getID() == productId) {
                productInformation += pList.get(i).toString();
            }
        }
        return productInformation;
    }

    //Print all product information in the inventory
    public String PrintInventoryList()
    {
        StringBuilder invInformation = new StringBuilder(); 
        invInformation.append(pList);
        return invInformation.toString(); 
    }
}