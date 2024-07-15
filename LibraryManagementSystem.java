import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    int id;
    String name, author;
    int quantity;

    public Book(int id, String name, String author, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }
}

class Student {
    int id;
    String name, email;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!\n");
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!\n");
    }

    public void viewBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println("ID: " + book.id + ", Name: " + book.name + ", Author: " + book.author + ", Quantity: " + book.quantity);
        }
        System.out.println(); // Leave a line after listing books
    }

    // Other library methods...
}

class UserAuthentication {
    private Map<String, String> userCredentials = new HashMap<>();

    public boolean createAccount(String loginId, String password) {
        if (userCredentials.containsKey(loginId)) {
            System.out.println("Account already exists with this login ID.\n");
            return false;
        }
        userCredentials.put(loginId, password);
        System.out.println("Account created successfully.\n");
        return true;
    }

    public boolean login(String loginId, String password) {
        if (userCredentials.containsKey(loginId) && userCredentials.get(loginId).equals(password)) {
            System.out.println("Login successful.\n");
            return true;
        }
        System.out.println("Invalid login ID or password.\n");
        return false;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        UserAuthentication userAuth = new UserAuthentication();
        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                System.out.println("\n1. Login");
                System.out.println("2. Create Account");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline

                switch (choice) {
                    case 1:
                        System.out.print("\nEnter login ID: ");
                        String loginId = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        loggedIn = userAuth.login(loginId, password);
                        break;
                    case 2:
                        System.out.print("\nEnter login ID for new account: ");
                        String newLoginId = scanner.nextLine();
                        System.out.print("Enter password for new account: ");
                        String newPassword = scanner.nextLine();
                        userAuth.createAccount(newLoginId, newPassword);
                        break;
                    case 3:
                        System.out.println("Exiting...\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid one.\n");
                }
            } else {
                System.out.println("Library Management System");
                System.out.println("1. Add Book");
                System.out.println("2. Add Student");
                System.out.println("3. View Books");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                
                switch (choice) {
                    case 1:
                        System.out.print("\nEnter Book ID, Name, Author, Quantity (separated by commas): ");
                        String bookInput = scanner.nextLine();
                        String[] bookDetails = bookInput.split(",");
                        Book book = new Book(Integer.parseInt(bookDetails[0].trim()), bookDetails[1].trim(), bookDetails[2].trim(), Integer.parseInt(bookDetails[3].trim()));
                        library.addBook(book);
                        break;
                    case 2:
                        System.out.print("\nEnter Student ID, Name, Email (separated by commas): ");
                        String studentInput = scanner.nextLine();
                        String[] studentDetails = studentInput.split(",");
                        Student student = new Student(Integer.parseInt(studentDetails[0].trim()), studentDetails[1].trim(), studentDetails[2].trim());
                        library.addStudent(student);
                        break;
                    case 3:
                        library.viewBooks();
                        break;
                    case 0:
                        loggedIn = false;
                        System.out.println("Logged out successfully.\n");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid one.\n");
                }
            }
        }
    }
}
