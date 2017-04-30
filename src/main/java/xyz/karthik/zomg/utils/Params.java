package xyz.karthik.zomg.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by vk on 4/29/2017.
 */
public class Params {

    public static class SerfRPC {
        public static String getIp() {
            return System.getProperty("serfRPCIp");
        }

        public static int getPort() {
            return Integer.valueOf(System.getProperty("serfRPCPort"));
        }
    }

    public static class Members {
        public static List<String> getMemberSerfs() {
            return Arrays.asList(System.getProperty("members").split(","));
        }
    }
}
