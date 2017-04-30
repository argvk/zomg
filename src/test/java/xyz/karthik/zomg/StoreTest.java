package xyz.karthik.zomg;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vk on 4/29/2017.
 */
public class StoreTest {
    @Test
    public void testWrite() {
        String dummyKey = "key";
        String dummyVal = "value";
        Assert.assertTrue(Store.store(dummyKey.getBytes(), dummyVal.getBytes()));
        String retVal = new String(Store.get(dummyKey.getBytes()));
        Assert.assertEquals(dummyVal, retVal);
    }
}
