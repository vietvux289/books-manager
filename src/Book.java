public class Book {
    int id;
    String name;
    double price;

    //Constructor for creating a new Book.
    public Book(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //auto-generated getter for id, name and price of book
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //auto-generated setter for name and price of book
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Generates a formatted string representation of the book.
     * @return A formatted string with book details (ID, name, price).
     */
    @Override
    public String toString() {
        return String.format("%5d %-45s %10.2f", id, name, price);
    }
}
