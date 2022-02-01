/**
 * User
 */
public class User 
{
    //TODO: add necessary fields to your program
	String username;
	String hashedPassword;
	String firstName;
	String lastName;
	boolean isManager;

    public User(String username, String hashedPassword, boolean isManager)
    {        
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager; 
    }
    
    public User(String firstName, String lastName, String username, String hashedPassword, boolean isManager)
    {        
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager; 
    }
    
    public String getFirstName() {
    	return firstName;
    }
    public String getLastName() {
    	return lastName;
    }
    public String getUsername () {
    	return username;
    }
    public String getPassword () {
    	return hashedPassword;
    }
    public boolean getManager () {
    	return isManager;
    }
    public String toString() {
    	return firstName + ", " + lastName + ", " + username + ", " + hashedPassword + ", " + isManager;
    }
}