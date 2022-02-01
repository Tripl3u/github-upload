import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MenuList
 */
public class MenuList {
	User loggedUser;
	String menuHeader;
	ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
    public MenuList(String menuHeader) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
    	Command tempCommand = null;
    	File menuFile = new File("MenuList.dat");
    	if(!menuFile.exists()) {
    		menuFile.createNewFile();
    	}
    	Scanner scan = new Scanner(menuFile);
    	int i = 1;
    	while(scan.hasNext()) {
    		String [] menuList = scan.nextLine().split(", ");
    		if(menuList[2].equals("DisplayInventoryCommand")) {
    			tempCommand = new DisplayInventoryCommand(new ProductCatalog(), loggedUser);
    		}
    		if(menuList[2].equals("AddUserCommand")) {
    			tempCommand = new AddUserCommand(new InventoryManagementSecurity(), loggedUser, new User("James", "Bravo", "Jbravo", "a18ebd77e72270090e68fdb93ac1b4bb", false));
    		}
    		if(menuList[2].equals("DeleteUserCommand")) {
    			tempCommand = new DeleteUserCommand(new InventoryManagementSecurity(), loggedUser);
    		}
    		if(menuList[2].equals("ChangePasswordCommand")) {
    			tempCommand = new ChangePasswordCommand(new InventoryManagementSecurity(), loggedUser);
    		}
    		if(menuList[2].equals("AddProductCommand")) {
    			tempCommand = new AddProductCommand(new ProductCatalog(), loggedUser);
    		}
    		if(menuList[2].equals("UpdateProductCommand")) {
    			tempCommand = new UpdateProductCommand(new InventoryManagementSecurity(), loggedUser);
    		}
    		if(menuList[2].equals("RemoveProductCommand")) {
    			tempCommand = new RemoveProductCommand(new InventoryManagementSecurity(), loggedUser);
    		}
    		if(menuList[2].equals("DisplayProdcutCommand")) {
    			tempCommand = new DisplayProductCommand(new ProductCatalog(), loggedUser);
    		}
    		MenuItem temp = new MenuItem(tempCommand,i,menuList[0],Boolean.parseBoolean(menuList[1]));
    		menuItemList.add(temp);
    		i++;
    	}	
    }
    
    public void setUser (User user) {
    	loggedUser = user;
    }

    public void AddMenuItem(MenuItem menuItem) throws IOException
    {
        File mFile = new File("MenuList.dat");
        Scanner scan = new Scanner(mFile);
    }

    public void StartMenu(User user) throws IOException
    {
        //TODO Display menu items based on user type, prompt user for command, execute selected command.
    	loggedUser = user;
    	System.out.println("Inventory Management System Menu");
    	for(int i = 0; i < menuItemList.size(); i++) {
    		if(menuItemList.get(i).isRestricted) {
    			if(loggedUser.getManager()) {
    				System.out.println(menuItemList.get(i).toString());
    			}
    		}
    		else {
    			System.out.println(menuItemList.get(i).toString());
    		}
    		
    	}
    	System.out.println("9- Exit");
    }
    public void startCommand(int num) {
    	if(num == menuItemList.size()+1) {
    		System.exit(0);
    	}
    	menuItemList.get(num-1).menuItemSelected();
    }
}