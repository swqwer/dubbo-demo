package com.rainarmy.tools.ftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import java.io.Closeable;
import java.io.IOException;

public class SFTPClient implements Closeable {
    private Session session;//会话
    private ChannelSftp channel;//连接通道

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ChannelSftp getChannel() {
        return channel;
    }

    public void setChannel(ChannelSftp channel) {
        this.channel = channel;
    }

    @Override
    public void close() throws IOException {
        if (this.session != null && this.session.isConnected()) {
            this.session.disconnect();
        }

        if (this.channel != null && this.channel.isConnected()) {
            this.channel.disconnect();
        }
    }
}
