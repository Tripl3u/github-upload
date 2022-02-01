import java.lang.reflect.Constructor;

/**
 * Command
 */
public abstract class Command 
{
    //TODO: add necessary fields to this class
	ProductCatalog productCatalog;
	InventoryManagementSecurity userList;
	User loggedOnUser;
   
    public Command(ProductCatalog productCatalog, User loggedOnUser)
    {
    	this.productCatalog = productCatalog;
    	this.loggedOnUser = loggedOnUser;
    }
    public Command(InventoryManagementSecurity userList, User loggedOnUser)
    {
    	this.userList = userList;
    	this.loggedOnUser = loggedOnUser;
    }
    

    public static Command CreateCommandDynamically(ProductCatalog productCatalog, User user, String commandClassName)
    {
        Class<?>    concreteCommandClass    = null;
        Command     command                 = null;
        String      packageName             = "InventoryManagement"; 

        try 
        {
            concreteCommandClass = Class.forName(packageName + "." + commandClassName);
            Constructor<?> con = concreteCommandClass.getConstructor(ProductCatalog.class, User.class);
            command = (Command)con.newInstance(productCatalog, user);
        } 
        catch (final Exception e) 
        {
            e.printStackTrace();
        }

        return command;
    }

    public abstract void Execute(); 


}