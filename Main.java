import java.util.*;
import java.io.*;
public class Main {
	
	public static void main (String [] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException  {
		boolean success = false;
		User lIUser = null;
		Scanner scan = new Scanner(System.in);
		while(!success) {
			System.out.print("Enter username: ");
			String username = scan.nextLine();
			System.out.print("Enter password: ");
			String password = scan.nextLine();
			lIUser = InventoryManagementSecurity.AuthenticateUser(username, password);
			if(lIUser != null) {
				success = true;
			}
		}
		ProductCatalog catalog = new ProductCatalog();
		MenuList menuList = new MenuList("Inventory Managment System Menu");
		menuList.setUser(lIUser);
		System.out.println("Welcome " + lIUser.getFirstName() + " " + lIUser.getLastName() + "!");
		while(true) {
			menuList.StartMenu(lIUser);
			System.out.print("Enter your selection: ");
			int userChoice = Integer.parseInt(scan.nextLine());
			menuList.startCommand(userChoice);
		}
	}

}
