package contactdb.dao;

import com.barlow.andy.contactdb.entities.Contact;

import java.util.List;

/**
 * Created by andy on 4/8/2015.
 *
 *
 *      interface for DB functionality. Added:
 *
 *      contactCreateTable() <-- create table in DB if not exist
 *      List<Contact> selectAll() <-- lists all contacts in db
 *      Contact selectById() <-- method allows updating / deleting slightly cleaner
 *      insert() <-- instead of "save();"
 *      update() <-- update method seperate from save()/insert() for cleaner solution
 *
 *
 *
 */


public interface iDBmethods {

    void contactCreateTable();

    void insert(Contact contact);

    Contact selectById(int id);

    List<Contact> selectAll();

    void delete(int id);

    void update(Contact contact, int id);
}

