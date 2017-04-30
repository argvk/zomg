package xyz.karthik.zomg;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;

/**
 * Created by vk on 4/29/2017.
 */
public class Store {
    public static RocksDB db = null;

    static {
        try {
            RocksDB.loadLibrary();

            Options opts = new Options().setCreateIfMissing(true);

            db = RocksDB.open(opts, "r");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static final boolean store(byte[] key, byte[] value) {
        try {
            if (key != null && value != null) {
                db.put(key, value);
                return true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    public static final byte[] get(byte[] key) {
        try {
            if (key != null) {
                return db.get(key);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}
