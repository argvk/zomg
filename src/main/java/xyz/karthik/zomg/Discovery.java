package xyz.karthik.zomg;

import no.tv2.serf.client.Client;
import no.tv2.serf.client.Member;
import no.tv2.serf.client.SocketEndpoint;
import xyz.karthik.zomg.utils.Params;

import java.util.List;

/**
 * Created by vk on 4/29/2017.
 */
public class Discovery {

    // set up the rpc client for a running serf agent
    private static Client sc;

    static {
        try {
            sc = new Client(new SocketEndpoint(Params.SerfRPC.getIp(), Params.SerfRPC.getPort()));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // look to join other agents
    static {
        try {
            sc.join(Params.Members.getMemberSerfs(), false);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static boolean isMembers() {
        try {
            List<Member> members = sc.members().getMembers();
            // rudimentary check to look for
            for (Member member : members) {
                boolean contains = Params.Members.getMemberSerfs().contains(member.getAddr().toString() + ":" + member.getPort());
                if (!contains) return false;
            }
            return true;
        } catch (Exception e){
            System.err.println(e);
        }
        return false;
    }
}
