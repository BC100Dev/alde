package net.bc100dev.alde;

import alde.framework.ALDEException;
import alde.framework.ALDEFramework;
import alde.framework.ALDEPartition;
import alde.framework.ALDESystem;
import alde.framework.android.AndroidPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

    private static int androidSdkVersion = 34; // Defaults to 28 (Android 9)
    private static boolean daemon = false; // The background service that relies on Services, Broadcast Receivers and Content Providers

    private static void runArgsParse(String[] args) {
        switch (args[0]) {
            case "-av", "--android-version" -> {
                // Specific Android version (may need to initialize the system)
                // Requires the version as the SDK Version
                // e.g. Android 7.1 being 25

                if (args.length != 2) {
                    System.err.println("Missing AVer parameter");
                    System.exit(1);
                    return;
                }

                int sdkVer;

                try {
                    sdkVer = Integer.parseInt(args[1]);
                } catch (NumberFormatException ignore) {
                    System.err.println("Not a valid Android SDK Version.");
                    System.exit(1);
                    return;
                }

                androidSdkVersion = sdkVer;
            }
            case "-d", "--daemon" -> {
                // Launch the ALDE Daemon that will resolve into using Services, Content Providers and Broadcast Receivers
                if (!daemon)
                    daemon = true;
            }
            case "-i", "--install" -> {
                // Install a specific package. May be able to give in multiple APK files.
                // If two or more APKs use the same UID, they will be able to install as a one/single package.
                // Can pass flags:
                /*
                --system: Installs the Application on the System Partition
                --current-user: Installs the Application on the current User
                --user=[uid]: Installs the Application on the user, defined by the UID
                --params=[params]: Passes parameters for the installer
                 */

                if (args.length == 1) {
                    System.err.println("Requires at least one argument");
                    System.exit(1);
                    return;
                }

                String params = null, user = null;
                boolean systemInstall = false;
                List<File> apkFiles = new ArrayList<>();

                for (int i = 1; i < args.length; i++) {
                    if (args[i].startsWith("-")) {
                        switch (args[i]) {
                            case "-s", "--system" -> {
                                systemInstall = true;
                                user = null;
                            }
                            case "-cu", "--current-user" -> {
                                user = "__current__";
                                systemInstall = false;
                            }
                            case "--user" -> {
                                if (!args[i].contains("=")) {
                                    System.err.println("Invalid Argument: " + args[i]);
                                    System.exit(1);
                                    return;
                                }

                                String[] prm = args[i].split("=", 2);
                                if (prm[1].trim().isEmpty()) {
                                    System.err.println("No user given in argument " + args[i]);
                                    System.exit(1);
                                    return;
                                }

                                user = prm[1].trim();
                            }
                        }
                    } else {
                        File apkFile = new File(args[i]);
                        if (!apkFile.exists())
                            System.err.println("APK File \"" + apkFile.getPath() + "\" not found; ignoring");

                        if (!apkFile.canRead())
                            System.err.println("APK File \"" + apkFile.getPath() + "\" cannot be opened; ignoring");

                        if (AndroidPackage.isValidPackage(apkFile))
                            apkFiles.add(apkFile);
                    }
                }


                try {
                    ALDESystem coreSystem = ALDESystem.findSystem("alde-core");
                    if (coreSystem == null)
                        coreSystem = ALDESystem.initializeSystem("alde-core");

                    ALDEPartition systemPart = coreSystem.getPartition("system");
                    ALDEPartition userPart = coreSystem.getPartition("userdata");

                    userPart.setMountPoint("/data");
                    userPart.setMountOptions("rw", "nosuid");

                    systemPart.setMountPoint("/system");
                    systemPart.setMountOptions("rw", "nosuid");

                    coreSystem.mount(userPart, systemPart);

                    // Perform installation
                } catch (IOException | ALDEException ex) {
                    System.err.println("An error occurred during initialization.");
                    ex.printStackTrace(System.err);
                    System.exit(1);
                }
            }
            case "-s", "--send" -> {
                // Sends a Broadcast, or starts a Service.
                // Second argument passes as the type ("srv" → Service; "rcv" → Broadcast Receiver).
                // If the second argument is passed as a Service, then the third argument is required for the package name,
                // with the fourth argument being the Service class.
            }
        }
    }

    public static void main(String[] args) {
        if (args != null && args.length >= 1)
            runArgsParse(args);

        try {
            ALDEFramework framework = new ALDEFramework();
            framework.setDaemon(daemon);
            framework.runSystem(28);
        } catch (ALDEException ex) {
            ex.printStackTrace();
        }
    }

}
