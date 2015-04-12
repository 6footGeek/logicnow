package mailstore;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by andy on 4/7/2015.
 *
 *
 *      This TestClass just runs some simple tests on the mailstore class
 *          The tests run using JUnit4
 *          All tests pass!
 *
 *
 */


public class mailstoreControlTest {

    @Test
    public void testMount() throws Exception {
        mailstoreControl m = new mailstoreControl();
        m.mount();
        assertTrue(m.check());
    }

    @Test
    public void testDismount() throws Exception {
        mailstoreControl m = new mailstoreControl();
        m.dismount();
        assertFalse(m.check());
    }

    @Test
    public void testCheck() throws Exception {
        mailstoreControl m = new mailstoreControl();
        assertFalse(m.check());
    }
}