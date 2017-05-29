package prospector.shootingstar.version;

import prospector.traverse.core.TraverseConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionUtils {
    public static boolean isVersionLessOrEqual(Version comparate1, Version comparate2) {
        if (comparate1.major > comparate2.major) {
            return false;
        } else if (comparate1.major == comparate2.major) {
            if (comparate1.minor > comparate2.minor) {
                return false;
            } else if (comparate1.major == comparate2.major && comparate1.minor == comparate2.minor) {
                if (comparate1.patch > comparate2.patch) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public static Version getVersion(String version) {
        if (TraverseConstants.MOD_VERSION.matches("(\\d+\\.\\d+\\.+\\d-\\d+\\.\\d+\\.\\d+-\\d)")) {
            Pattern pattern = Pattern.compile("-(\\d+)\\.(\\d+)\\.(\\d+)-");
            Matcher matcher = pattern.matcher(TraverseConstants.MOD_VERSION);
            return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
        }
        return new Version(1, 0, 0);
    }
}
