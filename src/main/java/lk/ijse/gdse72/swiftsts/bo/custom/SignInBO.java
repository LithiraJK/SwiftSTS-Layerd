package lk.ijse.gdse72.swiftsts.bo.custom;

public interface SignInBO {
    public boolean isUsernameValid(String username);
    public boolean validateCredentials(String username, String password);

}
