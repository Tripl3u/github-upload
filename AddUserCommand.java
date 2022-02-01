import java.io.IOException;

public class AddUserCommand extends Command{

	public User user = null;
	public AddUserCommand(InventoryManagementSecurity userList, User loggedOnUser, User newUser) {
		super(userList, loggedOnUser);
		
		user = newUser;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Execute() {
		try {
			InventoryManagementSecurity.AddNewUser(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
