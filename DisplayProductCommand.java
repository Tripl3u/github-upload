
public class DisplayProductCommand extends Command
{
    //TODO: add necessary fields to this class

	//public ProductCatalog productCatalog;
	//User loggedOnUser;
    public DisplayProductCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
        
    }

    @Override
    public void Execute() {
        // TODO Add the code that will execute this command
    		productCatalog.PrintInventoryList();
    }
}