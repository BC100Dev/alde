package alde.commons;

import java.io.File;

import static net.bc100dev.commons.utils.RuntimeEnvironment.USER_HOME;
import static net.bc100dev.commons.utils.RuntimeEnvironment.getOperatingSystem;

public class ALDEDefaults {

    public static File getConfigDirectory() {
        return switch (getOperatingSystem()) {
            case LINUX -> new File(USER_HOME.getAbsolutePath() + "/.config/bc100dev/alde");
            case MAC_OS -> new File(USER_HOME.getAbsolutePath() + "/Library/net.bc100dev/alde");
            case WINDOWS -> new File(USER_HOME.getAbsolutePath() + "\\AppData\\Local\\BC100Dev\\ALDE");
        };
    }

}
