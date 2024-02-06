import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library libraryex = new Library("Libraryexample");
      mainmenu();
    }
    public static void mainmenu(){
        try {
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("""
                        1)Sign Up
                        2)Reader Login
                        3)Librarian Login
                        4)Admin Login
                        5)Exit
                        """);
                System.out.print("Enter the number of action:");
                int choice = scan.nextInt(6);
                switch (choice) {
                    case 1 -> Librarian.registerreader();
                    case 2 -> Reader.readerLogin();
                    case 3 -> Librarian.login();
                    case 4 -> Admin.adminLogin();
                    case 5 -> {
                        System.out.println("Shutting down...");
                        System.exit(1);
                    }
                    default -> System.out.println("Main Menu default case");
                }
            }
        } catch (InputMismatchException e){
            System.out.println("Error! Enter the number from menu.");
        }
    }
}
