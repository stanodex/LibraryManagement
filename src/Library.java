import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    protected String libName;

  public static List<Reader> readerlist=new ArrayList<>();
  public static List<Book> allbooks=new ArrayList<>();
  protected static List<Librarian> librarianList = new ArrayList<>();
  protected static List<String> librariandata=new ArrayList<>();
  public static int bookcount=1;
  protected static List<String > adminData=new ArrayList<>();
  protected static List<String> readerData=new ArrayList<>();

  public Library(String name){
      Book bookEx = new Book("Bookex","authorEx");
       Reader readerex=new Reader("Readerex","password");
       Admin adminEx = new Admin("Adminex","password");
       Librarian librarianEx = new Librarian("Librarianex","password");
      this.libName = name;
  }
  public static Book checkBook(){
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter the name of the book:");
      String input = scan.nextLine();
    for(Book book : allbooks) {
      if (book.bookname.equals(input))
        return book;
    }
    return null;
  }

  public static Reader checkReader(){
    Scanner scanner=new Scanner(System.in);
    System.out.print("Enter your Reader username:");
    String nick=scanner.next();
    System.out.print("Enter your Reader password:");
    String password=scanner.next();
    for (Reader reader : readerlist){
      if (reader.logindata.equals(nick+password))
        return reader;
    }
    return null;
  }
  public static Librarian checkLibrarian(){
    //Librarian username same with real name of librarian
    Scanner scanner=new Scanner(System.in);
    System.out.print("Enter your Librarian username:");
    String nick= scanner.next();
    for (Librarian librarian : librarianList){
      if (librarian.nick.equals(nick))
        return librarian;
    }
    return null;
  }
}
