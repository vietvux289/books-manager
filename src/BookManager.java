import java.io.*;
import java.util.*;

public class BookManager {
    private ArrayList<Book> books;
    private Scanner sc = new Scanner(System.in);

    public BookManager() {
        books = new ArrayList<>();
    }

    /**
     * Returns the list of books.
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Updates this.books by reading books from file books.txt.
     *
     * @throws FileNotFoundException if the file is not found.
     */
    public void loadFromFile() throws FileNotFoundException {
        System.out.print("Loading books...");
        try (Scanner fileScanner = new Scanner(new File("books.txt"))) {
            while (fileScanner.hasNextLine()) {
                String bookLine = fileScanner.nextLine();
                if (!bookLine.isEmpty()) {
                    int id = Integer.parseInt(bookLine.substring(0, 6).trim());
                    String name = bookLine.substring(6, 50).trim();
                    double price = Double.parseDouble(bookLine.substring(50).trim());
                    books.add(new Book(id, name, price));
                }
            }
        }
    }

    /**
     * Prints books (one per line) in the required format.
     */
    public void printBooks(ArrayList<Book> booksToPrint) {
        if (booksToPrint.isEmpty()) {
            System.err.println("(empty)");
            return;
        }
        System.out.printf("%-5s %-45s %-10s\n", "ID", "Name", "Price");
        for (Book book : booksToPrint) {
            System.out.println(book);
        }
    }

    /**
     * Adds a book to this.books if book.id is not duplicated.
     * Returns whether the addition was successful.
     */
    public boolean add(Book book) {
        if (getBookById(book.id) != null) {
            System.err.println("Duplicated id!");
            return false;
        }

        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        System.out.print("Enter book price: ");
        double price = Double.parseDouble(sc.nextLine());
        books.add(new Book(book.id, name, price));
        System.out.println("Added successfully.");
        return true;
    }

    /**
     * Returns the book specified by id, or null if not found.
     */
    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.id == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * Deletes a book from this.books.
     */
    public void delete(Book book) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().id == book.id) {
                iterator.remove();
                System.out.println("Deleted successfully!");
                return;
            }
        }
        System.err.println("Invalid ID!");
    }

    /**
     * Updates the book's information based on the provided id.
     */
    public void updateBook(int id) {
        Book bookToUpdate = getBookById(id);
        if (bookToUpdate != null) {
            System.out.print("Enter book name: ");
            String name = sc.nextLine();
            System.out.print("Enter book price: ");
            double price = Double.parseDouble(sc.nextLine());
            bookToUpdate.setName(name);
            bookToUpdate.setPrice(price);
            System.out.println("Updated successfully.");
        } else {
            System.err.println("Invalid ID!");
        }
    }

    /**
     * Returns a list of books whose names contain the specified keyword (case insensitive).
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        for (Book book : books) {
            if (book.name.toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(book);
            }
        }
        return matches;
    }

    /**
     * Updates this.books to be sorted by price from high to low.
     */
    public void sortDescByPrice() {
        books.sort(Comparator.comparingDouble(Book::getPrice).reversed());
    }

    /**
     * Writes this.books to the file books.txt in the required format.
     *
     * @throws FileNotFoundException if the file cannot be created or written to.
     */
    public void saveToFile() throws FileNotFoundException {
        System.out.print("Saving to file...");
        try (PrintWriter writer = new PrintWriter("books.txt")) {
            for (Book book : books) {
                writer.printf("%5d %-45s %10.2f%n", book.id, book.name, book.price);
            }
        }
    }
}
