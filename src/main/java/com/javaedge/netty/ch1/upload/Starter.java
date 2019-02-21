package com.javaedge.netty.ch1.upload;


import com.javaedge.netty.ch1.upload.server.UploadFileServer;

/**
 * @description:
 * @author: JavaEdge
 * @version: 1.0
 */
public class Starter {

    //请求上传
    //创建文件
    //将客户端数据写入本地磁盘
    //command 4  fileName 4 8

    //请求上传 -> 是否能够上传 -> 上传（客户端） -》将客户端数据写入本地磁盘
    public static void main(String[] args) throws Exception {
        new UploadFileServer(9001).run();
    }
}
