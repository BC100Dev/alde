package net.bc100dev.commons;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.bc100dev.commons.utils.RuntimeEnvironment.getOperatingSystem;
import static net.bc100dev.commons.utils.RuntimeEnvironment.isWindows;
import static net.bc100dev.commons.utils.io.FileUtil.listDirectory;

public class NativeLibrary {

    public static String getDefaultPrefix() {
        return switch (getOperatingSystem()) {
            case LINUX, MAC_OS -> "lib";
            case WINDOWS -> "";
        };
    }

    public static String getDefaultSuffix() {
        return switch (getOperatingSystem()) {
            case LINUX -> ".so";
            case MAC_OS -> ".dylib";
            case WINDOWS -> ".dll";
        };
    }

    public static File findNativeLibrary(String libName, String prefix, String suffix, String[] nativeLibraryPaths) throws IOException  {
        if (nativeLibraryPaths == null)
            throw new NullPointerException("Native library paths are pointed as null");

        if (nativeLibraryPaths.length == 0)
            throw new ApplicationRuntimeException("Native library path pointer entry cannot be empty");

        for (String libPath : nativeLibraryPaths) {
            File fLibEntry = new File(libPath);
            if (!fLibEntry.exists())
                continue;

            if (!fLibEntry.canRead())
                continue;

            List<String> pathEntryList = listDirectory(libPath, false, false); // may throw IOException
            List<String> pathCache = new ArrayList<>();

            for (String pathEntry : pathEntryList) {
                if (pathCache.contains(pathEntry))
                    continue;

                File file = new File(pathEntry);
                String name = file.getName();

                if (name.startsWith(prefix) && name.contains(suffix)) {
                    int start = prefix.length();
                    int end = name.lastIndexOf(suffix);

                    String lName = name.substring(start, end);
                    System.out.printf("%d %d %s\n", start, end, lName);
                    if (lName.equals(libName))
                        return file;
                }

                pathCache.add(pathEntry);
            }
        }

        return null;
    }

    public static File findNativeLibrary(String libName, String[] nativeLibraryPaths) throws IOException  {
        return findNativeLibrary(libName, getDefaultPrefix(), getDefaultSuffix(), nativeLibraryPaths);
    }

    public static File findNativeLibrary(String libName, String prefix, String suffix) throws IOException {
        String[] paths = System.getProperty("java.library.path").split(isWindows() ? ";" : ":");

        return findNativeLibrary(libName, prefix, suffix, paths);
    }

    public static File findNativeLibrary(String libName) throws IOException {
        String[] paths = System.getProperty("java.library.path").split(isWindows() ? ";" : ":");

        return findNativeLibrary(libName, getDefaultPrefix(), getDefaultSuffix(), paths);
    }

}
