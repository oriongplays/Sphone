package com.dev.sphone.mod.common.phone;

import com.dev.sphone.mod.client.gui.phone.AppManager;

public class Notification {
    private AppManager.App relatedApp;
    private String title;
    private String content;
    private String icon;
    private NotificationType type;
    private long sendTime;

    public Notification() {}

    public Notification(AppManager.App relatedApp, String title, String content, String icon, NotificationType type, long sendTime) {
        this.relatedApp = relatedApp;
        this.title = title;
        this.content = content;
        this.icon = icon;
        this.type = type;
        this.sendTime = sendTime;
    }

    public AppManager.App getRelatedApp() {
        return relatedApp;
    }

    public void setRelatedApp(AppManager.App relatedApp) {
        this.relatedApp = relatedApp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public enum NotificationType {
        PERSISTENT,
        TEMPORARY,
        DANGER
    }
}
