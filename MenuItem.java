import java.lang.reflect.Constructor;

/**
 * MenuItem
 */
public class MenuItem 
{
	Command command;
	int optionNumber;
	String description;
	Boolean isRestricted;
    public MenuItem(Command command, 
                    int optionNumber, 
                    String description, 
                    Boolean isRestricted)
    {
        //TODO Finish the implementation of this class
    	this.command = command;
    	this.optionNumber = optionNumber;
    	this.description = description;
    	this.isRestricted = isRestricted;

        //System.out.println("Menu item created with command " + command.getClass().getSimpleName());
    }
    
    public String toString() {
    	return optionNumber+"- "+description;
    }
    
    public void menuItemSelected() {
    	command.Execute();
    }

}