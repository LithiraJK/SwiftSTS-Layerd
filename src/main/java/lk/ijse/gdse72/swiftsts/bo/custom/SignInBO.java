package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;

public interface SignInBO extends SuperBO {
    public boolean isUsernameValid(String username);
    public boolean validateCredentials(String username, String password);

}
