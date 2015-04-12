package contact.entities;

import contact.db.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Andy on 12/04/15.
 * This contact class implements the contact interface accurately without modification.
 * *
 *          save();
 *          insert is the DB insert function. inserts first and last name from console input.
 *          Inserts first_name and last_name up to length of 100 into DB. int id is auto_increment so not included here.
 *
 *          delete();
 *          delete is the DB delete function. It deletes row from database where id = id that is taken as int parameter from console.
 *          int id is unique so allows for correct deletion of record rather than deleting by first_name/last_name which could be duplicated in the db.
 *
 *          selectAll();
 *          method allows all records to be displayed in format of id, first_name, last_name. Just used for display / test purposes.
 *
 *          update();
 *          update method allows the updating of previously saved info in the contact DB. it takes the unique id of the contact from scanner to be updated and updates the correct contact.
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setID(int id) {
        this.id = id;
    }


    public int getID() {
        return id;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void save() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter Last Name: ");
        String lastName = sc.nextLine();
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            c = DButil.getConnection();
            preparedStatement = c.prepareStatement("INSERT INTO contact(first_name, last_name)"
                    + "Values(?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO contact(first_name, last_name)"
                    + "Values(" + firstName + "," + lastName + ")");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void delete() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of Contact to DELETE: ");
        int id = sc.nextInt();

        Connection c = null;
        PreparedStatement sql = null;

        try {
            c = DButil.getConnection();
            sql = c.prepareStatement("DELETE FROM contact WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            System.out.println("DELETE FROM contact WHERE id = " + id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sql != null) {
                try {
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID of contact to update: ");
        int id = sc.nextInt();

        System.out.println("Enter First Name: ");
        String firstName = sc.next();

        System.out.println("Enter Last Name: ");
        String lastName = sc.next();

        Connection c = null;
        PreparedStatement sql = null;

        try {
            c = DButil.getConnection();
            sql = c.prepareStatement("UPDATE contact SET " +
                    "first_name = ?, last_name = ? WHERE id = ?");

            sql.setString(1, firstName);
            sql.setString(2, lastName);
            sql.setInt(3, id);
            sql.executeUpdate();
            System.out.println("UPDATE contact SET " +
                    "first_name = " + firstName + ", last_name = " + lastName + " WHERE id = " + id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sql != null) {
                try {
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public List<Contact> selectAll() {
        List<Contact> contacts = new ArrayList<Contact>();
        Connection c = null;
        Statement sql = null;
        ResultSet result = null;
        try {
            c = DButil.getConnection();
            sql = c.createStatement();
            result = sql.executeQuery("SELECT * FROM contact");

            while (result.next()) {
                Contact contact = new Contact();
                contact.setID(result.getInt("id"));
                contact.setFirstName(result.getString("first_name"));
                contact.setLastName(result.getString("last_name"));

                contacts.add(contact);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (sql != null) {
                try {
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return contacts;
    }

}
