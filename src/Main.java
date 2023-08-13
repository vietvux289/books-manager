import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        BookManager bookManager = new BookManager();
        loadFile(bookManager);
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();

            switch (choice) {
                case 0:
                    saveAndExit(bookManager);
                    break;
                case 1:
                    listAllBooks(bookManager);
                    break;
                case 2:
                    addNewBook(bookManager);
                    break;
                case 3:
                    editBook(bookManager);
                    break;
                case 4:
                    deleteBook(bookManager);
                    break;
                case 5:
                    searchBooksByName(bookManager);
                    break;
                case 6:
                    sortBooksByPrice(bookManager);
                    break;
                default:
                    System.err.println("Invalid option!");
            }
        } while (choice != 0);
    }

    /**
     * Load books from file into the book manager.
     */
    public static void loadFile(BookManager bookManager) throws FileNotFoundException, InterruptedException {
        bookManager.loadFromFile();
    }

    /**
     * Display the main menu.
     */
    public static void displayMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("1. List all books\n2. Add a new book\n3. Edit book\n4. Delete a book\n5. Search books by name\n6. Sort books descending by price\n\n0. Save & exit");
        System.out.println("----------------------------------------");
        System.out.print("Enter your option: ");
    }

    /**
     * Get the user's choice from the menu.
     */
    public static int getUserChoice() {
        String userOption = sc.nextLine();
        return Integer.parseInt(userOption);
    }

    /**
     * List all books stored in the book manager.
     */
    public static void listAllBooks(BookManager bookManager) {
        bookManager.printBooks(bookManager.getBooks());
    }

    /**
     * Add a new book to the book manager.
     */
    public static void addNewBook(BookManager bookManager) {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(sc.nextLine());
        bookManager.add(new Book(id, "", 0));
    }

    /**
     * Edit the details of a book in the book manager.
     */
    public static void editBook(BookManager bookManager) {
        System.out.print("Enter book id: ");
        bookManager.updateBook(Integer.parseInt(sc.nextLine()));
    }

    /**
     * Delete a book from the book manager.
     */
    public static void deleteBook(BookManager bookManager) {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(sc.nextLine());
        bookManager.delete(new Book(id, "", 0));
    }

    /**
     * Search books by name in the book manager.
     */
    public static void searchBooksByName(BookManager bookManager) {
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();
        bookManager.printBooks(bookManager.searchByName(keyword));
    }

    /**
     * Sort books by price in descending order.
     */
    public static void sortBooksByPrice(BookManager bookManager) {
        bookManager.sortDescByPrice();
        listAllBooks(bookManager);
    }

    /**
     * Save book data to file and exit the program.
     */
    public static void saveAndExit(BookManager bookManager) throws FileNotFoundException, InterruptedException {
        bookManager.saveToFile();
        System.out.println("\nBye!");
    }
}
