package com.dev.sphone.mod.common.phone;

public class Weather {
    private int clearTime;
    private int rainTime;
    private int thunderTime;
    private boolean isRaining;
    private boolean isThundering;

    public Weather() {}

    public Weather(int clearTime, int rainTime, int thunderTime, boolean isRaining, boolean isThundering) {
        this.clearTime = clearTime;
        this.rainTime = rainTime;
        this.thunderTime = thunderTime;
        this.isRaining = isRaining;
        this.isThundering = isThundering;
    }

    public int getClearTime() {
        return clearTime;
    }

    public void setClearTime(int clearTime) {
        this.clearTime = clearTime;
    }

    public int getRainTime() {
        return rainTime;
    }

    public void setRainTime(int rainTime) {
        this.rainTime = rainTime;
    }

    public int getThunderTime() {
        return thunderTime;
    }

    public void setThunderTime(int thunderTime) {
        this.thunderTime = thunderTime;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public boolean isThundering() {
        return isThundering;
    }

    public void setThundering(boolean thundering) {
        isThundering = thundering;
    }
}
