package com.zgp;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourceListener {

    private static ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
    private WatchService ws;
    private String listenerPath;
    private ResourceListener(String path) {
        try {
            ws = FileSystems.getDefault().newWatchService();
            this.listenerPath = path;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        fixedThreadPool.execute(new Listner(ws,this.listenerPath));
    }

    public static void addListener(String path) throws IOException {
        ResourceListener resourceListener = new ResourceListener(path);
        Path p = Paths.get(path);
        p.register(resourceListener.ws,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);
    }

}
