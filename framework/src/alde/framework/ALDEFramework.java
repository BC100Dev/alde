package alde.framework;

import net.bc100dev.commons.utils.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static alde.commons.ALDEDefaults.getConfigDirectory;

public class ALDEFramework {

    protected ALDEFramework(File configDir, LinkedList<String> libs) {
    }

    public static ALDEFramework init() throws IOException {
        File file = new File(getConfigDirectory().getAbsolutePath());
        if (!FileUtil.exists(file.getAbsolutePath()))
            FileUtil.createDirectory(file.getAbsolutePath());

        String sessionType = System.getenv("XDG_SESSION_TYPE");
        if (sessionType != null) {
        }

        return new ALDEFramework(file, new LinkedList<>());
    }

}
