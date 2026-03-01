public class User {
    private String name;
    private String email;
    private String password;
 // constructor checks also for null error and throws exception, we trim to make sure of no spaces
    public User(String name, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMediaException("User name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidMediaException("User email cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidMediaException("User password cannot be null or empty");
        }

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMediaException("User name cannot be null or empty");
        }
        this.name = name;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidMediaException("User email cannot be null or empty");
        }
        this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidMediaException("User password cannot be null or empty");
        }
        this.password = password;
    }

    public void login() {
        System.out.println(name + " logged in.");
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }
}