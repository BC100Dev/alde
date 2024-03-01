package alde.framework.android;

import alde.framework.ALDEPartition;

import java.io.File;
import java.util.List;

public class AndroidPackage {

    private final File apkFile;

    public static boolean isValidPackage(File file) {
        return false;
    }

    public AndroidPackage(File apkFile) {
        this.apkFile = apkFile;
    }

    public void install(ALDEPartition part, String installPath, String... params) {
    }

    public static class Manifest {

        private final String pkgName, ver, sharedUserId;
        private final int verCode;
        private final List<String> permissions, features;
        private final Application application;

        protected Manifest(String pkgName, String ver, int verCode, String sharedUserId,
                           List<String> permissions, List<String> features, Application application) {
            this.pkgName = pkgName;
            this.ver = ver;
            this.verCode = verCode;
            this.sharedUserId = sharedUserId;
            this.permissions = permissions;
            this.features = features;
            this.application = application;
        }

        public String getPackageName() {
            return pkgName;
        }

        public String getVersion() {
            return ver;
        }

        public int getVersionCode() {
            return verCode;
        }

        public String getSharedUserId() {
            return sharedUserId;
        }

        public String[] getPermissions() {
            String[] perms = new String[permissions.size()];

            for (int i = 0; i < permissions.size(); i++)
                perms[i] = permissions.get(i);

            return perms;
        }

        public String[] getFeatures() {
            String[] feat = new String[features.size()];

            for (int i = 0; i < features.size(); i++)
                feat[i] = features.get(i);

            return feat;
        }

        public Application getApplication() {
            return application;
        }
    }

    public static class Application {
    }

    public static class Activity {

        private final String className;
        private final boolean enabled, exported, startable;

        protected Activity(String className, boolean enabled, boolean exported, boolean startable) {
            this.className = className;
            this.enabled = enabled;
            this.exported = exported;
            this.startable = startable;
        }

        public boolean isStartable() {
            return startable;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public boolean isExported() {
            return exported;
        }

        public String getClassName() {
            return className;
        }
    }

    public static class BroadcastReceiver {
    }

    public static class Service {
    }

    public static class ContentProvider {
    }

}
