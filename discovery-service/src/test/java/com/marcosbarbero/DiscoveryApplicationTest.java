package com.marcosbarbero;

import com.marcosbarbero.discovery.DiscoveryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiscoveryApplication.class)
@WebAppConfiguration
public class DiscoveryApplicationTest {

    @Test
    public void testLoadContext() {
    }
}
