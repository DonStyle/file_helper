package com.zgp;

import java.io.IOException;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class Listner implements Runnable {
    private WatchService service;
    private String rootPath;

    public Listner(WatchService service, String rootPath) {
        this.service = service;
        this.rootPath = rootPath;
    }

    public void run() {
        try {
            while(true){
                WatchKey watchKey = service.take();
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for(WatchEvent<?> event : watchEvents){

                    //TODO 根据事件类型采取不同的操作。。。。。。。
                    System.out.println("["+rootPath+event.context()+"]文件发生了["+event.kind()+"]事件"+    event.count());
                }
                watchKey.reset();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            System.out.println("fdsfsdf");
            try {
                service.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
