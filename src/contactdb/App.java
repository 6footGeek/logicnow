package contactdb;


import com.barlow.andy.contactdb.dao.DBmethods;
import com.barlow.andy.contactdb.entities.Contact;

import java.util.List;

/**
 * Created by andy on 4/8/2015.
 *
 *
 *      Just some usage code for showing DB functionality
 *
 *
 */


public class App {
    public static void main(String[] args) {

        //make x db methods object
       DBmethods x = new DBmethods();


        //create table
//        x.contactCreateTable();


        //add data to table
//        Contact contact = new Contact("Andy", "Barlow");
//        x.insert(contact);


        //select by id
//        Contact contact = x.selectBtId(2);
//        System.out.println(contact.getID() + ": " + contact.getFirstName() + " " + contact.getLastName());


        //delete by id
//        x.delete(11);

        //list all db entries
//        List<Contact> contacts = x.selectAll();
//            for(Contact contact : contacts) {
//                System.out.println(contact.getID() + ": " + contact.getFirstName() + " " + contact.getLastName());
//            }

            //update contact by id
//        Contact contact = new Contact("Andy", "Barlow");
//        x.update(contact, 6);

    }


}
