package sistemadevagasdeestacionamento

@Singleton
public final class AuthHelper {
    private String mCurrentUsername

    public String getCurrentUsername() {
        return mCurrentUsername
    }

    public void signup(String username, String sector) {
        def user = new User(username: username, firstName: "Primeiro nome", lastName: "Último nome", preferredSector: sector)
        user.save(flush:true)
    }

    public void login(String username) {
        mCurrentUsername = username
    }

    public void logout() {
        mCurrentUsername = null
    }
}