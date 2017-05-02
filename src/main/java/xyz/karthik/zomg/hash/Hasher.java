package xyz.karthik.zomg.hash;

import no.tv2.serf.client.Member;

import java.util.List;

/**
 * Created by vk on 4/30/2017.
 */
public abstract class Hasher {
   private List<String> members;

   public Hasher withMembers(List<String> members) {
      this.members = members;
      return this;
   }

   public List<String> getMembers() {
      return members;
   }

   public abstract String getMemberForKey(String key);
}
