package Register.actionMode;

public class User {

    private String libraryName;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(String name, String surname, String address, String email, String phoneNumber, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = "Client";
    }

    public User(String libraryName, String address, String email, String phoneNumber, String username, String password) {
        this.libraryName = libraryName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = "Manager";
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getLibraryName() { return libraryName; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLibraryName(String libraryName) { this.libraryName = libraryName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if(libraryName == null)
            return  name.equals(user.name) &&
                    surname.equals(user.surname) &&
                    address.equals(user.address) &&
                    email.equals(user.email) &&
                    phoneNumber.equals(user.phoneNumber) &&
                    username.equals(user.username) &&
                    password.equals(user.password) &&
                    role.equals(user.role);
        else
            return  libraryName.equals(user.name) &&
                    address.equals(user.address) &&
                    email.equals(user.email) &&
                    phoneNumber.equals(user.phoneNumber) &&
                    username.equals(user.username) &&
                    password.equals(user.password) &&
                    role.equals(user.role);
    }

    @Override
    public int hashCode() {
        if(libraryName == null) {
            int result = name.hashCode();
            result = 31 * result + surname.hashCode();
            result = 31 * result + address.hashCode();
            result = 31 * result + email.hashCode();
            result = 31 * result + phoneNumber.hashCode();
            result = 31 * result + username.hashCode();
            result = 31 * result + password.hashCode();
            result = 31 * result + role.hashCode();
            return result;
        }
        else {
            int result = libraryName.hashCode();
            result = 31 * result + address.hashCode();
            result = 31 * result + email.hashCode();
            result = 31 * result + phoneNumber.hashCode();
            result = 31 * result + username.hashCode();
            result = 31 * result + password.hashCode();
            result = 31 * result + role.hashCode();
            return result;
        }
    }

    @Override
    public String toString() {
        if(libraryName == null)
            return "User{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", address='" + address + '\'' +
                    ", email='" + email + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        else
            return "User{" +
                    "libraryName='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", email='" + email + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    '}';
    }

}
