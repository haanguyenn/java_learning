package appium16;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AndroidSeverFlagEx implements ServerArgument {
    ALLOW_INSECURE("--allow-insecure");

    private final String arg;

    AndroidSeverFlagEx(String arg) {
        this.arg = arg;
    }

    public String getArgument() {
        return this.arg;
    }
}
