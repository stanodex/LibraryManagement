
import java.util.Scanner;

public class Admin{
protected String nick;
protected String password;
protected String data;
public Admin(String nick,String password){
    this.nick=nick;
    this.password=password;
    this.data=nick+password;
    Library.adminData.add(data);
}

private static void registerLibrarian(){
    try {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter the Librarian username you want to register: ");
        String nick=scan.next();
        System.out.print("Set the Librarian password: ");
        String password=scan.next();
        Librarian librarian=new Librarian(nick,password);
        System.out.println("Librarian " + librarian.nick + " registered successfully.");
    }catch (Exception e){
        System.out.println("error in registerLibrarian()");
        e.printStackTrace();
    }
}
private static void removeLibrarian() {
    try {
        Librarian librarian = Library.checkLibrarian();
        if (librarian != null) {
            System.out.println("Librarian " + librarian.nick + " is deleted.");
            Library.librariandata.remove(librarian.data);
            Library.librarianList.remove(librarian);
            librarian = null;
            Runtime.getRuntime().gc();
        } else
            System.out.println("There is no Librarian with this name.");
    } catch (Exception e) {
        System.out.println("error in removeLibrarian()");
        e.printStackTrace();
    }
}
public static void librarianListsort(){
    System.out.println("-Librarian List-");
    for (Librarian librarian:Library.librarianList){
        librarian.getInfo(librarian);
    }
}
public static void adminLogin(){
    try {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter your username:");
        String nick=scan.next();
        System.out.print("Enter your password:");
        String password=scan.next();
        String data = nick+password;
        if (Library.adminData.contains(data)){
            System.out.println("\n"+"Admin logged in"+"\n");
            adminMenu();
        }else
            System.out.println("Wrong username or password");
    }catch (Exception e){
        System.out.println("error in adminLogin()");
        e.printStackTrace();
    }
}
private static void adminMenu(){
    try {
        while (true){
            Scanner scan=new Scanner(System.in);
            System.out.println("1)Register Librarian"+"\n"+"2)Remove Librarian"+"\n"+"3)Librarian List"+"\n"+"4)Back"+"\n"+"5)Exit");
            System.out.print("Enter the number of the action:");
            int choice=scan.nextInt(6);
            switch (choice) {
                case 1 -> registerLibrarian();
                case 2 -> removeLibrarian();
                case 3 -> librarianListsort();
                case 4 -> Main.mainmenu();
                case 5 -> System.exit(1);
                default -> System.out.println("adminMenu default case");
            }
        }

    }catch (Exception a){
        System.out.println("adminMenu exception block");
        a.printStackTrace();
    }
}

}
