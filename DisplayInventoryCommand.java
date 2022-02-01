
public class DisplayInventoryCommand extends Command
{
    //TODO: add necessary fields to this class

	//public ProductCatalog productCatalog;
	//User loggedOnUser;
    public DisplayInventoryCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
        
    }

    @Override
    public void Execute() {
        // TODO Add the code that will execute this command
    		System.out.println(productCatalog.PrintInventoryList());
    }
}