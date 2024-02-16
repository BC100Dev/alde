package net.bc100dev.alde;

import alde.framework.ALDEFramework;
import net.bc100dev.commons.NativeLibrary;

import java.io.File;
import java.io.IOException;

public class MainClass {

    public static void main(String[] args) {
        try {
            ALDEFramework framework = ALDEFramework.init();
            File f = NativeLibrary.findNativeLibrary("epub");
            System.out.println(f.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
