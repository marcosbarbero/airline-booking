package com.marcosbarbero;

import com.marcosbarbero.discovery.DiscoveryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiscoveryApplication.class)
public class DiscoveryApplicationTest {

    @Test
    public void testLoadContext() {
    }
}
