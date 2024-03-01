package alde.framework;

import alde.framework.android.AndroidUser;

import java.io.IOException;

public class ALDESystem {

    public static ALDESystem findSystem(String name) throws IOException, ALDEException {
        return null;
    }

    public static ALDESystem initializeSystem(String name) throws IOException, ALDEException {
        return null;
    }

    public ALDEPartition getPartition(String partName) throws IOException {
        return null;
    }

    public void mount(ALDEPartition... partitions) throws IOException, ALDEException {
        if (partitions == null || partitions.length == 0)
            throw new ALDEException("No partitions given to mount");
    }

    public static AndroidUser getUser(ALDEPartition partition, String name) {
        return null;
    }

}
