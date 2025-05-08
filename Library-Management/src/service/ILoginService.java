package service;

import model.User;

public interface ILoginService {
    User authenticate(String username, String password);
    boolean isLibrarian(User user);
}
