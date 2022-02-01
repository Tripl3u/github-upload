import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * InventoryManagementSecurity
 */
public class InventoryManagementSecurity 
{
	public InventoryManagementSecurity () {
		
	}
    public static User AuthenticateUser(String username, String password) throws IOException
    {
        User authenticatedUser = null;
		File inFile = new File("Users.dat");
        	if(!inFile.exists()) {
        		inFile.createNewFile();
        	}
        if((username.compareToIgnoreCase("admin") == 0) && 
           (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0))
        {
            authenticatedUser = new User(username, GetPasswordHash(password), true); 
        }
        else
        {
            //TODO get the password hash using GetPasswordHash() method then try to find the user in Users.dat file
        	boolean success = false;
        	Scanner scan = new Scanner(inFile);
        	while(scan.hasNext()) {
        		String [] list = scan.nextLine().split(", "); //splitting user data 
        		//TODO set the authenticatedUser object if successfully authenticated
        		if(GetPasswordHash(password).compareToIgnoreCase(list[3]) == 0) {
        			authenticatedUser = new User(list[0], list[1], list[2], GetPasswordHash(password), Boolean.parseBoolean(list[4])); 
        			success = true;
        			}
        	}		
        	if(!success) {
        		System.out.println("Invalid username or password!");
        	}
        }
        	

        return authenticatedUser; //Change the return value based on authentication result
    }

    public static boolean AddNewUser(User newUser) throws IOException
    {
    	boolean bool = true;
    	Scanner scan = new Scanner(new File("Users.dat"));
    	Scanner scnr = new Scanner(System.in);
    	System.out.println("Enter User First Name: ");
    	scnr.nextLine();
    	System.out.println("Enter User Last Name: ");
    	scnr.nextLine();
    	System.out.println("Enter Username: ");
    	scnr.nextLine();
    	System.out.println("Enter Password: ");
    	scnr.nextLine();
    	System.out.println("Is user Manager? (True/False): ");
    	scnr.nextLine();
    	while(scan.hasNext()) {
    		String [] userList = scan.nextLine().split(", ");  
		if(newUser.getUsername().compareToIgnoreCase(userList[2]) == 0) {
    			bool = false;
    		}
    	}	
    	if(bool) {
    		File userFile = new File("Users.dat");
    		FileWriter fWriter = new FileWriter(userFile, true);
    		fWriter.write(newUser.toString()+"\n");
    		fWriter.close();
    	}
    	
		return bool;
        //TODO hash password and save username and hashed password to Users.dat
    }

    public static boolean RemoveUser(String userName) throws IOException
    {
        //TODO remove username from Users.dat
    	
    	boolean bool = true;
    	Scanner scan = new Scanner(new File("Users.dat"));
    	while(scan.hasNext()) {
    		String [] userList = scan.nextLine().split(", "); 
    		
		if(userName.compareToIgnoreCase(userList[2]) == 0) {
    			
    			bool = false;
    		}
    	}	
    	if(bool) {
    		File userFile = new File("Users.dat");
    		File tempFile = new File("TempFile.txt");
	    	Scanner read = new Scanner(userFile);
	    	FileWriter fWriter = new FileWriter(tempFile, true);
	    	while(read.hasNext()) {
	    	String currentLine = read.nextLine();
    	    if(!currentLine.contains(userName)) {
    	    	fWriter.write(currentLine);;
    	    	}
	    	}
	    	fWriter.close(); 
	    	read.close(); 
	    	tempFile.renameTo(userFile);
    	}
    	return bool;
    }

    public static boolean ChangePassword(String username, 
                                      String currentPassword, 
                                      String newPassword) throws IOException
    {
        //TODO change the password only if current password match what is on records
    	boolean bool = false;
    	File f = new File("Users.dat");
    	if(!f.exists()) {
    		f.createNewFile();
    	}
    	Scanner scan = new Scanner(f);
    	while(scan.hasNext()) {
    		String [] userList = scan.nextLine().split(", "); //splitting user data 
    		
    		if(GetPasswordHash(currentPassword).compareToIgnoreCase(userList[3]) == 0) {
    			RemoveUser(username);
    			AddNewUser(new User(username, newPassword, Boolean.parseBoolean(userList[4])));
    			bool = true;
    		}
    	}		
    	return bool;
    }

    public static String GetPasswordHash(String password) 
    {        
        String generatedPassword = null;
        
        try 
        {
            byte[] salt = new byte[] {12, -12, 65, 61, 
                                      2, -6, -90, 12, 
                                      4, -7, -87, 2, 
                                      34, -102, 3, 115};

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 

        return generatedPassword;
    }
}