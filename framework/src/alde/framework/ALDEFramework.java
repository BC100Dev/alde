package alde.framework;

import java.io.File;

public class ALDEFramework {

    private static ALDEFramework frameworkInstance;

    private boolean daemon = false;

    public ALDEFramework() {
        if (frameworkInstance != null)
            throw new RuntimeException("The ALDE Framework cannot be initialized multiple times");

        updateFramework();
    }

    private void updateFramework() {
        ALDEFramework.frameworkInstance = this;
    }

    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
        updateFramework();
    }

    public boolean isDaemon() {
        return daemon;
    }

    public void runSystem(int sdkVersion) throws ALDEException {
    }

    public void runPackage(String _package) throws ALDEException {
    }

    public void runPackage(File apkPackage) throws ALDEException {
    }

    public static ALDEFramework getFrameworkInstance() {
        return frameworkInstance;
    }

}
