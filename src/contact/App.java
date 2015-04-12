package contact;

import contact.entities.Contact;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Andy on 12/04/15.
 *      Simple console based menu for manipulating contact class / db.
 */
public class App {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        Contact c = new Contact();


        System.out.println("1: Insert Contact");
        System.out.println("2: Update Contact");
        System.out.println("3: Delete Contact");
        System.out.println("4: ListAll Contacts");
        System.out.println("5: Exit");

        int menu;
        boolean exit = false;
        do {
            System.out.println("Choose menu option");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    c.save();
                    break;
                case 2:
                    c.update();
                    break;
                case 3:
                    c.delete();
                    break;
                case 4:
                    List<Contact> contacts = c.selectAll();
                    for (Contact contact : contacts) {
                        System.out.println(contact.getID() + ": " + contact.getFirstName() + " " + contact.getLastName());
                    }
                    break;
                case 5:
                    exit = true;
                    System.exit(1);
                    break;
                default:
                    System.out.println("Enter a menu number!");
            }
        } while (!exit);
    }
}