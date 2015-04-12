package contact.entities;

/**
 * Created by Andy on 12/04/15.
 */
public interface iContact {

    public void setFirstName(String firstName);
    public String getFirstName();
    public void setLastName(String lastName);
    public String getLastName();
    public void save();
    public void delete();
}
