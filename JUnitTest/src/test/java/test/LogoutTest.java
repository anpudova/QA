package test;

import org.junit.Test;

public class LogoutTest extends AuthBase {

    @Test
    public void testLogoutTestCase() throws Exception {
        app.getAuth().logout();
    }

}
