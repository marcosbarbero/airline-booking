package com.marcosbarbero.booking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApplication.class)
public class BookingApplicationTest {

    @Test
    public void testLoadContext() {
    }
}
