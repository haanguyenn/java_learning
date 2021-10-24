package lesson_lab9_Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    static List<Book> bookList = new ArrayList<>();
    static String bookFilePath = System.getProperty("user.dir") + "/Book.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Select an option: ");
            System.out.println("1. Add a new book");
            System.out.println("2. Get a book info");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("0. Exit");
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 0:
                    System.out.println("Thank you for using our service. Bye!");
                    isContinue = false;
                    break;
                case 1:
                    addBook();
                    break;
                case 2:
                    retrieveBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                default:
                    System.out.println("Sorry. Please input a valid option!");
                    break;
            }
        }
        if (!bookList.isEmpty()) {
            boolean isSavingOK = saveBookList(bookList, bookFilePath);
            if (isSavingOK) {
                System.out.println("Data's all set in: " + bookFilePath);
            } else {
                System.out.println("Saving's Failed!");
            }
        }
    }

    private static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ISBN: ");
        String bookISNB = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        Book newBook = new Book(bookISNB, title, author);
        bookList.add(newBook);
        saveBookList(bookList, bookFilePath);
    }

    private static void retrieveBook() {
        List<Book> books = readBookList(bookFilePath);
        if (books.isEmpty()) {
            System.out.println("The List is Empty.!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input an ISBN: ");
            String userInput = scanner.nextLine();
            for (Book book : books) {
                if (userInput.equals(book.getISBN())) {
                    System.out.printf("Title of %s is %s\n", userInput, book.getTitle());
                    System.out.printf("Author of %s is %s\n", userInput, book.getAuthor());
                }
            }
        }
    }

    private static void updateBook() {
        List<Book> books = readBookList(bookFilePath);
        if (books.isEmpty()) {
            System.out.println("The list is Empty");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input an ISBN: ");
            String userInput = scanner.nextLine();
            for (Book book : books) {
                if (book.getISBN().equals(userInput)) {
                    System.out.print("Update author: ");
                    String updatedAuthor = scanner.nextLine();
                    System.out.print("Update title: ");
                    String updatedTitle = scanner.nextLine();
                    //update new value
                    book.setAuthor(updatedAuthor);
                    book.setTitle(updatedTitle);
                }
                saveBookList(books, bookFilePath);
            }
        }
    }

    private static void deleteBook() {
        List<Book> books = readBookList(bookFilePath);
        if (books.isEmpty()) {
            System.out.println("The list is Empty");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input an ISBN: ");
            String userInput = scanner.nextLine();
            for (Book book : books) {
                if (book.getISBN().equals(userInput)) {
                    books.remove(book);
                    System.out.print(userInput +  "is successfully deleted.");
                }
                saveBookList(books, bookFilePath);
            }
        }
    }

    private static boolean saveBookList(List<Book> bookList, String path) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)
        ) {
            for (Book book : bookList) {
                String bookDataLine = book.getISBN() + ";" + book.getTitle() + ";" + book.getAuthor();
                bufferedWriter.write(bookDataLine);
                bufferedWriter.newLine();
            }
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            // print exception details
        }
        return false;
    }

    private static List<Book> readBookList(String path) {
        List<Book> books = new ArrayList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream(path);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String bookDataLine = bufferedReader.readLine();
            while (bookDataLine != null) {
                String[] bookData = bookDataLine.split(";");
                String ISBN = bookData[0];
                String title = bookData[1];
                String author = bookData[2];
                books.add(new Book(ISBN, title, author));
                bookDataLine = bufferedReader.readLine();// move to the next line
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return books;
    }
}
