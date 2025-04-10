package cart.shopping.core;

public final class User {
    private String firstname;
    private String lastname;
    private final ShoppingCart shoppingCart;

    public User(String firstname, String lastname) {
        setFirstname(firstname);
        setLastname(lastname);
        this.shoppingCart = new ShoppingCart();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    private void setFirstname(String firstname) {
        if (firstname == null || firstname.trim().isEmpty()) {
            throw new IllegalArgumentException("Firstname cannot be null or empty");
        }
        this.firstname = firstname;
    }

    private void setLastname(String lastname) {
        if (lastname == null || lastname.trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
        this.lastname = lastname;
    }
}