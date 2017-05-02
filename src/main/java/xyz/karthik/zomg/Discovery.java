package xyz.karthik.zomg;

import no.tv2.serf.client.Client;
import no.tv2.serf.client.Member;
import no.tv2.serf.client.SocketEndpoint;
import no.tv2.serf.client.Stats;
import xyz.karthik.zomg.utils.Params;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vk on 4/29/2017.
 */
public class Discovery {

    private static final String KEY_NODENAME = "nn";
    private static Stats MY_STATS;

    // set up the rpc client for a running serf agent
    private static Client sc;

    static {
        try {
            (sc = new Client(new SocketEndpoint(Params.SerfRPC.getIp(), Params.SerfRPC.getPort()))).handshake();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // look to join other agents
    static {
        try {
//            sc.join(Params.Members.getMemberSerfs(), false);
            MY_STATS = sc.stats().getStats();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static List<String> getNodes() {
        try {
            List<Member> members = sc.members().getMembers();
            List<String> nodeNames = new ArrayList<>();
            for (Member member : members) {
                try {
                    String nodename = member.getTags().get(KEY_NODENAME);
                    nodeNames.add(nodename);
                }
                catch (Exception e) {
                    System.err.println(e);
                }
            }
            Collections.sort(nodeNames);
            return nodeNames;
        } catch (Exception e){
            System.err.println(e);
        }
        return null;
    }

    public static String thisNodeName(){
        return MY_STATS.getTags().get(KEY_NODENAME);
    }
}
