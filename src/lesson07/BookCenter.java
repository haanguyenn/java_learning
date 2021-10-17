package lesson07;

import java.util.*;

public class BookCenter {
    static List<Book> bookList = new ArrayList<>();

    //    static Map<String, List> bookList = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("1. Add");
            System.out.println("2. Get a book info");
            System.out.println("0. Exit");
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 0:
                    System.out.println("Thank you. See you next time!");
                    isContinue = false;
                case 1:
                    addBook();
                    break;
                case 2:
                    retrieveBook();
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
                    break;

            }
        }
    }

    private static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ISBN: ");
        String bookISBN = scanner.nextLine();
        System.out.print("Title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Author: ");
        String bookAuthor = scanner.nextLine();
        Book newBook = new Book(bookISBN, bookTitle, bookAuthor);
        bookList.add(newBook);
    }


    private static void retrieveBook() {
        if (bookList.isEmpty()) {
            System.out.println("Sorry. The current list is empty!!!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Tell us the ISBN: ");
            String userInput = scanner.nextLine();
            for (Book book : bookList) {
                if (userInput.equals(book.getISBN())) {
                    System.out.printf("The title of %s is %s\n", userInput, book.getTitle());
                    System.out.printf("The author of %s is %s\n", userInput, book.getAuthor());
                }
            }

        }
    }
}
