package com.rainarmy.tools.ftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;
import java.util.*;

public class SFTPUtil {

    /**
     * 连接ftp/sftp服务器
     * SFTP类
     */
    public static SFTPClient getConnect(String host, int port, String username, String password) throws Exception {
        SFTPClient client = new SFTPClient();
        Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;// sftp操作类

        JSch jsch = new JSch();

        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey
        session.setConfig(config);
        session.connect();

        channel = session.openChannel("sftp");
        channel.connect();

        client.setSession(session);
        client.setChannel((ChannelSftp) channel);

        return client;
    }


    /**
     * 下载文件
     *
     * @param remoteDirectory    下载目录 根据SFTP设置的根目录来进行传入
     * @param remoteFileName 下载的文件
     * @param remoteFileName     存在本地的路径
     */
    public static void upload(String uploadFile, String remoteDirectory, String remoteFileName, String host, int port, String username, String password) throws Exception {

        File in = new File(uploadFile);
        try (SFTPClient client = getConnect(host, port, username, password);
             InputStream input = new FileInputStream(in);) {
            ChannelSftp sftp = client.getChannel();// sftp操作类

            sftp.cd(remoteDirectory); //进入目录
            sftp.put(input, remoteFileName);
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录 根据SFTP设置的根目录来进行传入
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     */
    public static void download(String directory, String downloadFile, String saveFile, String host, int port, String username, String password) throws Exception {

        try (SFTPClient client = getConnect(host, port, username, password)) {
            ChannelSftp sftp = client.getChannel();// sftp操作类

            sftp.cd(directory); //进入目录
            File file = new File(saveFile);
            boolean bFile;
            bFile = false;
            bFile = file.exists();
            if (!bFile) {
                bFile = file.mkdirs();//创建目录
            }
            OutputStream out = new FileOutputStream(new File(saveFile, downloadFile));
            sftp.get(downloadFile, out);
            out.flush();
            out.close();

        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @return list 文件名列表
     * @throws Exception
     */
    public static List<String> listFiles(String directory, String host, int port, String username, String password) throws Exception {
        try (SFTPClient client = getConnect(host, port, username, password)) {
            ChannelSftp sftp = client.getChannel();// sftp操作类
            Vector fileList = null;
            List<String> fileNameList = new ArrayList<String>();
            fileList = sftp.ls(directory); //返回目录下所有文件名称

            Iterator it = fileList.iterator();

            while (it.hasNext()) {

                String fileName = ((ChannelSftp.LsEntry) it.next()).getFilename();
                System.out.println(fileName);
                if (".".equals(fileName) || "..".equals(fileName)) {
                    continue;
                }
                fileNameList.add(fileName);
            }

            return fileNameList;
        }
    }

}
