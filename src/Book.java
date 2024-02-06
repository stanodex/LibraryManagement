import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;
import java.time.LocalDateTime;
import java.util.ListIterator;
import java.util.Scanner;

public class Book {
    public String bookname;
    public String bookauthor;
    protected boolean isAvailable;
    protected int uniquebooknumber;
   protected int borrowdate;
    protected int returndate;
    protected String currentReader=null;
    protected boolean isExtended=false;


    public Book(String bookname,String bookauthor){
        this.bookname=bookname;
        this.bookauthor= bookauthor;
        uniquebooknumber=Library.bookcount;
        Library.bookcount++;
        this.isAvailable=true;
        Library.allbooks.add(this);
    }
    public void changeInfo() throws IOException {
        Scanner scan = new Scanner(System.in);
        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Update Author? (y/n)");
        input = scan.next();
        if(input.equals("y")){
            System.out.println("Enter new author:");
            bookauthor = reader.readLine();
        }
        System.out.println("Update book name? (y/n)");
        input = scan.next();
        if(input.equals("y")){
            bookname = reader.readLine();
        }
    }
    public String getBookname(){
        return bookname;
    }
    public String getBookauthor(){
        return bookauthor;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public Integer getuniquenum(){
        return uniquebooknumber;
    }
    public void getInfo(){
        try {
            System.out.println("Book Name: " + bookname + "\n" + "Author:" + bookauthor + "\n" + "Unique Book Number: " +
             uniquebooknumber + "\n" + "Is Available? :" + isAvailable+"\n"+"Current Reader:"+currentReader
            );
        }catch (Exception e){
            System.out.println("error in Book class getInfo()");
            e.printStackTrace();
        }
        }
}
