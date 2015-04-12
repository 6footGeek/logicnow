package mailstore;

/**
 * Created by andy on 4/7/2015.
 *
 *      Simple mailstoreControl class
 *      Mailstore can only be in 2 states:
 *          mounted or not mounted.
 *
 *      3 methods:
 *          mount()
 *          dismount()
 *          check()
 */


public class mailstoreControl {
    protected boolean mounted;

    protected mailstoreControl() {
        //init mounted as false
        this.mounted = false;
    }

    protected String mount() {
        this.mounted = true;
        return "MailStore has been Mounted";
    }

    protected String dismount() {
        this.mounted = false;
        return "MailStore has been dismounted";
    }

    protected boolean check() {
        if(this.mounted) {
            System.out.println("mailStore is mounted");
            return true;
        } else {
            System.out.println("mailStore is not mounted");
            return false;
        }
    }
}