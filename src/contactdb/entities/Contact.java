package contactdb.entities;

/**
 * Created by andy on 4/8/2015.
 *
 *      My version of a Contact class using OOP
 *      This class takes care of the getters and setters for the Contact object.
 *
 *      int id;
 *      String firstName;
 *      String lastName;
 *
 *
 *      Removed 2 methods from interface as seemed better to keep them in a seperate class -
 *      the following methods are now located in DBmethods.java
 *
 *      void save(); (also renamed to insert())
 *      void delete();
 *
 */


public class Contact implements iContact {
    protected int id;
    protected String firstName;
    protected String lastName;

    public Contact() {

    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void setID(int id) {
        this.id = id;
    }


    public int getID() {
        return id;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getLastName() {
        return lastName;
    }


}
