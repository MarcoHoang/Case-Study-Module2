package service;

import model.User;
import model.Librarian;
import util.PasswordUtil;

public class LoginService implements ILoginService {
    private final IUserService userService;

    public LoginService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean isLibrarian(User user) {
        return user instanceof Librarian;
    }
}
