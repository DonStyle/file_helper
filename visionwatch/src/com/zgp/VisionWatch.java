package com.zgp;

import java.io.IOException;
import java.nio.file.*;

public class VisionWatch {

    public static void main(String[] args) {
        try {
            ResourceListener.addListener("d:\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
