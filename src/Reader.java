import java.time.LocalDateTime;
import java.util.*;

public class Reader {
    protected List<Book> borrowedBooks=new ArrayList<>();
    protected List<String> readedBooks=new ArrayList<>();
    private int readedbookcount;
    private int readerXp;
    static int uniqueIDcount=1;
    protected int uniqueID;
    protected String nick;
    protected String password;
    protected String logindata;
    public Reader(String nick,String password){
        this.nick=nick;
        this.password=password;
        this.logindata=nick+password;
        uniqueID=uniqueIDcount;
        uniqueIDcount++;
        Library.readerlist.add(this);
        Library.readerData.add(logindata);
    }
    public static void readerLogin(){
       try {
           Reader reader;
           reader=Library.checkReader();
           if (reader !=null) {
               System.out.println("Reader successfully logged in.");
              readerMenu(reader);
           } else
               System.out.println("Wrong name or password.");
       }catch (Exception e){
           System.out.println("error at readerLogin()");
           e.printStackTrace();
       }
    }
    private static void readerMenu(Reader reader){
        try {
            while (true){
                Scanner scan=new Scanner(System.in);
                System.out.println("""
                        1)Borrow Book
                        2)Return Book
                        3)Donate Book
                        4)Extend Deadline
                        5)Get Info
                        6)Back
                        7)Exit""");
                System.out.print("Enter the action number from menu:");
                int choice=scan.nextInt(8);
                switch (choice){
                    case 1 -> reader.borrowingBook(reader);
                    case 2 -> reader.returnBook(reader);
                    case 3 ->{
                        Librarian.registerbook();
                        reader.readerXp=+5;
                        System.out.println("Congratulations, you have earned 5 readerXp from your book donation.");
                    }
                    case 4 -> Librarian.extendDeadline(reader);
                    case 5 -> reader.getInfo();
                    case 6 -> Main.mainmenu();
                    case 7 ->{
                        System.out.println("Shutting down...");
                        System.exit(1);
                    }
                    default -> System.out.println("readerMenu default case");
                }
            }
        }catch (Exception e){
            System.out.println("error in readerMenu()");
            e.printStackTrace();
        }
    }
    public void borrowingBook(Reader reader) { //Book nesnesinin ismi ile bookname ayni olmasi zorunludur
        Scanner scan = new Scanner(System.in);
        try {
            Book book = Library.checkBook();
            if (book != null && book.isAvailable) {
                        reader.borrowedBooks.add(book);
                        book.isAvailable = false;
                        book.borrowdate = LocalDateTime.now().getDayOfYear();
                        reader.readerXp += 5;
                        book.currentReader=reader.nick;
                        System.out.println("""
                                You have borrowed the book.
                                You have earned 5 readerXp.
                                You have 15 days to read.
                                If you exceed 15 days, you will be charged 3$ each day.""");
            } else if(book != null && !book.isAvailable) {
                System.out.println("This book has been borrowed.");
            } else {
                System.out.println("There is no book with this name in our library.");
            }
        }catch (InputMismatchException a){
            System.out.println("The book name has to be a word.");
            }catch(Exception e){
            System.out.println("error in borrowingBook()");
            e.printStackTrace();
        }
        }

    public void returnBook(Reader reader) {
        try {
                    Book book = Library.checkBook();
                    if (book !=null && reader.borrowedBooks.contains(book)) {
                        book.isAvailable = true;
                        book.currentReader=null;
                        reader.borrowedBooks.remove(book);
                        book.returndate = LocalDateTime.now().getDayOfYear();
                        if (book.returndate - book.borrowdate <= 15) {
                            System.out.println("""
                            Congratulations you returned the book without time exceeding.
                            You have earned 5 readerXp.""");
                            reader.readerXp += 5;
                        } else {
                            int timeoutday = (book.returndate - book.borrowdate) - 15;
                            System.out.println("You exceeded the 15 day limit by" + timeoutday + "days.\n" +
                                    "You have to pay" + 3 * timeoutday + "$");
                            System.out.println("You lost 5 readerXp because you exceeded the time limit.");
                            reader.readerXp -= 5;
                        }
                        reader.readedbookcount++;
                        reader.readedBooks.add(book.bookname);
                    }else if(book !=null && book.isAvailable) {
                        System.out.println("You cannot return the book that has not been borrowed.");
                    }else if(book !=null && !book.isAvailable && !reader.borrowedBooks.contains(book)){
                        System.out.println("You cannot return a book borrowed by someone else.");
                    } else {
                        System.out.println("You cannot return the book that is not registered in our library.");
                    }


        }catch (Exception e){
            System.out.println("error in returnBook()");
            e.printStackTrace();
        }
    }
    public String toString(){
        return "Nick: "+nick+"\n"+"Readed book count:"+readedbookcount+"\n"+"Reader Xp:"+readerXp+"\n"+
       "Readed Books:"+readedBooks;
    }


   void getInfo() {
        System.out.println("Nick:"+nick+"\n"+"Readed book count:"+readedbookcount+"\n"+
                "Reader Xp:"+readerXp+"\n"+"Readed Books:"+readedBooks+"\n"+"Unique ID:"+uniqueID);

    }
}
