package service;

import model.Librarian;
import model.User;

public class LoginService implements ILoginService {
    private final IUserService userService;

    public LoginService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User authenticate(String username, String password) {
        return userService.login(username, password);
    }

    @Override
    public boolean isLibrarian(User user) {
        return user instanceof Librarian;
    }
}
