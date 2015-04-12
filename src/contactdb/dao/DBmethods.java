package contactdb.dao;

import com.barlow.andy.contactdb.entities.Contact;
import com.barlow.andy.contactdb.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 4/8/2015.
 *      DBmethods keeps the dao methods seperate from the contact class for future ease of use.
 *
 *
 *      contactCreateTable() <-- create table in DB if not exist
 *      List<Contact> selectAll() <-- lists all contacts in db
 *      Contact selectById() <-- method allows updating / deleting slightly cleaner
 *      insert() <-- instead of "save();"
 *      update() <-- update method seperate from save()/insert() for cleaner solution
 *
 *
 *
 *
 *          contactCreateTable();
 *              Creates a contacts table in the DB if it doesnt exist.
 *              Sets up columns for int id, first_name varchar(100) and last_name varchar(100).
 *
 *          insert();
 *              insert is the DB insert function. inserts Contact object taken as parameter.
 *              Inserts first_name and last_name up to length of 100 into DB. int id is auto_increment so not included here.
 *
 *          delete();
 *              delete is the DB delete function. It deletes row from database where id = id that is passed as int parameter.
 *              int id is unique so allows for correct deletion of record rather than deleting by first_name/last_name which could be duplicated.
 *
 *          selectById();
 *              allows the delete() and update() method to work cleaner by selecting id rather than first_name/last_name as id is unique so no inadvertent deleting of duplicated contacts.
 *
 *          selectAll();
 *              method allows all records to be displayed in format of id, first_name, last_name. Just used for display / test purposes.
 *
 *          update();
 *              update method allows the updating of previously saved info in the contact DB. it takes a contact object of the updated data and the unique id of the contact to be updated.
 *
 */


public class DBmethods implements iDBmethods {
    @Override
    public void contactCreateTable() {
        Connection c = null;
        Statement sql = null;

        try {
            c = ConnectionUtil.getConnection();
            sql = c.createStatement();

            sql.execute("CREATE TABLE IF NOT EXISTS contact (id int primary key unique auto_increment, first_name varchar(100), last_name varchar(100))");
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

    @Override
    public void insert(Contact contact) {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            c = ConnectionUtil.getConnection();
            preparedStatement = c.prepareStatement("INSERT INTO contact(first_name, last_name)"
                    + "Values(?,?)");
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO contact(first_name, last_name)"
                    + "Values(" + contact.getFirstName() + ","  + contact.getLastName() + ")");
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
    public Contact selectById(int id) {

        Contact contact = new Contact();
        Connection c = null;
        PreparedStatement sql = null;
        ResultSet result = null;

        try {
            c = ConnectionUtil.getConnection();
            sql = c.prepareStatement("SELECT * FROM contact WHERE id = ?");
            sql.setInt(1, id);
            result = sql.executeQuery();

            while (result.next()) {
                contact.setID(result.getInt("id"));
                contact.setFirstName(result.getString("first_name"));
                contact.setLastName(result.getString("last_name"));
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


        return contact;
    }

    @Override
    public List<Contact> selectAll() {
        List<Contact> contacts = new ArrayList<Contact>();
        Connection c = null;
        Statement sql = null;
        ResultSet result = null;
        try {
            c = ConnectionUtil.getConnection();
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

    @Override
    public void delete(int id) {
            Connection c = null;
            PreparedStatement sql = null;

        try {
            c = ConnectionUtil.getConnection();
            sql = c.prepareStatement("DELETE FROM contact WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            System.out.println("DELETE FROM contact WHERE id = " + id);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sql != null) {
                try {
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Contact contact, int id) {
        Connection c = null;
        PreparedStatement sql = null;

        try {
            c = ConnectionUtil.getConnection();
            sql = c.prepareStatement("UPDATE contact SET " +
                    "first_name = ?, last_name = ? WHERE id = ?");

            sql.setString(1, contact.getFirstName());
            sql.setString(2, contact.getLastName());
            sql.setInt(3, id);
            sql.executeUpdate();
            System.out.println("UPDATE contact SET " +
                    "first_name = " + contact.getFirstName() + ", last_name = " + contact.getLastName() +" WHERE id = " + contact.getID());

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sql != null) {
                try {
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
