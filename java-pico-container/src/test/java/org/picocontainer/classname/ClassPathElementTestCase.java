package org.picocontainer.classname;

import org.junit.Test;

import java.io.FilePermission;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 
 * @author Mauro Talevi
 */
public class ClassPathElementTestCase {

    @Test public void testGetUrl() throws Exception{
        URL url = new URL("file:///usr/lib");
        ClassPathElement element = new ClassPathElement(url);
        assertEquals(url, element.getUrl());
    }

    @Test public void testGrantPermission() throws Exception{
        ClassPathElement element = new ClassPathElement(new URL("file:///usr/lib"));
        element.grantPermission(new FilePermission("/usr/lib", "read"));
        assertNotNull(element.getPermissionCollection());
    }
}
