package com.hjc.base.model.response;

import java.io.Serializable;

public class VersionBean implements Serializable {

    private String lowVersion;
    private String newVersion;
    private String updateLog;
    private String filePath;

    public String getLowVersion() {
        return lowVersion;
    }

    public void setLowVersion(String lowVersion) {
        this.lowVersion = lowVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "lowVersion='" + lowVersion + '\'' +
                ", newVersion='" + newVersion + '\'' +
                ", updateLog='" + updateLog + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
