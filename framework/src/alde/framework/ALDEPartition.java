package alde.framework;

import java.io.File;

public class ALDEPartition {

    private String name;
    private File partFile;

    private String[] mntOptions;
    private String mntPath;

    protected ALDEPartition(String name, File partFile) {
        this.name = name;
        this.partFile = partFile;
    }

    public String getName() {
        return name;
    }

    public void setMountPoint(String point) {
        this.mntPath = point;
    }

    public String getMountPoint() {
        return mntPath;
    }

    public void setMountOptions(String... mntOptions) {
        this.mntOptions = mntOptions;
    }

    public String[] getMountOptions() {
        return mntOptions;
    }

    @Override
    public String toString() {
        StringBuilder opts = new StringBuilder();

        for (String mntOpt : mntOptions)
            opts.append(mntOpt).append(" ");

        return "name=\"" + name + "\" " +
                "point=\"" + mntPath + "\" " +
                "options=\"" + opts + "\"";
    }
}
