package com.dev.sphone.mod.common.phone;

import com.dev.sphone.mod.client.gui.phone.AppManager;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private String background;
    private boolean silence;
    private List<AppManager.App> downlaodedApps = new ArrayList<>();

    public Settings() {}

    public Settings(String background, boolean silence) {
        this.background = background;
        this.silence = silence;
        this.downlaodedApps = new ArrayList<>();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }

    public List<AppManager.App> getDownloadedApps() {
        return downlaodedApps;
    }

    public void setDownloadedApps(List<AppManager.App> downlaodedApps) {
        this.downlaodedApps = downlaodedApps;
    }
}
