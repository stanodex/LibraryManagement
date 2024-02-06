import java.time.LocalDateTime;
import java.util.*;

public class Librarian extends Reader {
      String data;
    public Librarian(String nick,String password){
        super(nick,password);
        this.nick=nick;
        this.password=password;
         this.data=nick+password;
         Library.librariandata.add(data);
         Library.librarianList.add(this);
    }
    protected static void registerreader(){ // add unique username control
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Set your Reader username: ");
            String nick = scan.next();
            System.out.print("Set your password:");
            String password=scan.next();
            Reader nickk = new Reader(nick,password);
            System.out.println("Registered successfully.");
        }catch (Exception e){
            System.out.println("Error in registerreader()");
            e.printStackTrace();
        }
    }
    protected static void registerbook(){
        try {
            Scanner scan =new Scanner(System.in);
            System.out.print("Enter the name of book which you want to register:");
            String bookname=scan.next();
            System.out.println("Enter the author of the book you want to register:");
            String bookauthor = scan.next();
            Book book=new Book(bookname, bookauthor);
            System.out.println("Book registered.");
        }catch (Exception e){
            System.out.println("Error in registerbook()");
            e.printStackTrace();
        }
        }

    protected static void extendDeadline(Reader reader) {
        try {
            Scanner scanner = new Scanner(System.in);
            Book book=Library.checkBook();
            if (book !=null) {
                if ( book.isExtended != true && reader.borrowedBooks.contains(book)) {
                    int now = LocalDateTime.now().getDayOfYear();
                    System.out.print("Enter the duration you want to extend (max 3 days):");
                    int extendTime = scanner.nextInt(4);
                    if (now - book.borrowdate <= 15) {
                        book.borrowdate += extendTime;
                        book.isExtended = true;
                        System.out.println("Reading duration has been extended " + extendTime + " days.");
                    } else {
                        System.out.println("We cannot extend the duration of your book because you have exceeded the 15-day." +
                                "We have to receive the book.");
                        reader.returnBook(reader);
                    }
                } else if (!book.isAvailable && !reader.borrowedBooks.contains(book)) {
                    System.out.println("You cannot extend the duration of a book borrowed by someone else.");
                } else if (book.isAvailable) {
                    System.out.println("This book has not borrowed. You cannot extend the duration.");
                } else {
                    System.out.println("The duration of book can be extended only once by one reader.");
                }
            }else
                System.out.println("You cannot extend the duration of a book that does not exist.");

        }catch (Exception e){
            System.out.println("error in extendDeadline()");
            e.printStackTrace();
        }
    }

    public static void login() {
        try {
            Scanner scan2=new Scanner(System.in);
            System.out.print("Enter your username:");
            String nick = scan2.next();
            System.out.print("Enter your password:");
            String password = scan2.next();
            String data = nick + password;
            if (Library.librariandata.contains(data)) {
                System.out.println("\n" + " logged in as a Librarian");
                menu();
            } else {
                System.out.println("Username or password is wrong.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error in Librarian's login()");
        }
    }
    private static void menu() {
        try {
            while (true) {
                Scanner scan = new Scanner(System.in);
                System.out.println("""
                        1)Register Book
                        2)Get Book Info
                        3)Change Book Info
                        4)Back
                        5)Exit
                        """);
                System.out.print("Enter the number of the action:");
                int choice = scan.nextInt(6);
                switch (choice) {
                    case 1 -> registerbook();
                    case 2 -> {
                        Book book = Library.checkBook();
                        if (book == null) System.out.println("There is no book with this name.");
                        else book.getInfo();
                    }
                    case 3 -> {
                        Book book = Library.checkBook();
                        if (book == null) System.out.println("There is no book with this name.");
                        else book.changeInfo();
                    }
                    case 4 -> Main.mainmenu();
                    case 5 -> {
                        System.out.println("Shutting down...");
                        System.exit(1);
                    }
                    default -> System.out.println("Librarian menu default case");
                }
            }
            } catch(InputMismatchException a){
                System.out.println("Error! Please enter a number from menu.");

            }catch(Exception e){
                System.out.println("error in menu()");
                e.printStackTrace();
            }
        }
    public void getInfo(Librarian librarian){ //getinfo duzenle
        System.out.println("Name: " + nick );
    }
}

