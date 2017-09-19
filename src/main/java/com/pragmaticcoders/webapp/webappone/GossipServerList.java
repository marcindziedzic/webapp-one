package com.pragmaticcoders.webapp.webappone;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.pragmaticcoders.loadbalancer.GossipConnector;
import org.apache.gossip.LocalMember;
import org.apache.gossip.manager.GossipManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GossipServerList implements ServerList<Server>{

    @Autowired
    private GossipConnector gossipConnector;

    @Override
    public List<Server> getInitialListOfServers() {
        GossipManager gossipManager = gossipConnector.getGossipManager();
        List<LocalMember> liveMembers = gossipManager.getLiveMembers();

        // TODO extract http properties from LocalMember properties

        List<Server> servers = new LinkedList<>();

        for (LocalMember liveMember : liveMembers) {
            servers.add(GossipServer.of(liveMember));
        }

        return servers;
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        return getInitialListOfServers();
    }

    static class GossipServer extends Server {

        private String appName;

        // TODO send real port
        public static GossipServer of(LocalMember localMember) {

            String host = localMember.getUri().getHost();
            int computedPort = localMember.getUri().getPort() - 1000;

            return new GossipServer(host, computedPort, localMember.getId());
        }


        public GossipServer(String host, int port, String appName) {
            super(host, port);
            this.appName = appName;
        }

        @Override
        public MetaInfo getMetaInfo() {
            return new Server.MetaInfo() {

                @Override
                public String getAppName() {
                    return appName;
                }

                @Override
                public String getServerGroup() {
                    return appName;
                }

                @Override
                public String getServiceIdForDiscovery() {
                    return appName;
                }

                @Override
                public String getInstanceId() {
                    return appName;
                }

            };
        }
    }
}
