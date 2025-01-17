package org.example.host;

import com.opencsv.bean.CsvBindByName;

public class Host {

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String url;

    @CsvBindByName
    private String messagingEndpoint;

    @CsvBindByName
    private String heartbeatEndpoint;

    @CsvBindByName
    private int heartbeatInterval;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessagingEndpoint() {
        return messagingEndpoint;
    }

    public void setMessagingEndpoint(String messagingEndpoint) {
        this.messagingEndpoint = messagingEndpoint;
    }

    public String getHeartbeatEndpoint() {
        return heartbeatEndpoint;
    }

    public void setHeartbeatEndpoint(String heartbeatEndpoint) {
        this.heartbeatEndpoint = heartbeatEndpoint;
    }

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    @Override
    public String toString() {
        return "Host{name=" + name + ", url=" + url + ", messagingEndpoint=" + messagingEndpoint + ", heartbeatEndpoint=" + heartbeatEndpoint + ", heartbeatInterval=" + heartbeatInterval + '}';
    }
}
