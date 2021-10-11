/*
 * Copyright (c) 2018.
 * This code is released under The 3-Clause BSD License.
 * https://github.com/techpavan
 */

package com.steeplesoft.maven;

import java.io.File;
import java.util.Objects;

public class FileInfo {

    private File file;

    private String groupId;

    private String artifactId;

    private String version;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public FileInfo getParentFileInfo() {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setArtifactId(this.artifactId);
        fileInfo.setGroupId(this.groupId);
        fileInfo.setVersion(this.version);
        fileInfo.setFile(this.file.getParentFile());
        return fileInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(groupId, fileInfo.groupId) &&
                Objects.equals(artifactId, fileInfo.artifactId) &&
                Objects.equals(version, fileInfo.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, artifactId, version);
    }
}
