/*
 * Copyright (c) 2018.
 * This code is released under The 3-Clause BSD License.
 * https://github.com/techpavan
 */

package com.github.techpavan.maven;

import java.text.ParseException;
import java.util.List;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import org.apache.commons.lang3.time.DateUtils;

public class ArgData {

    @Parameter(names = {"--path", "-p"}, description = "Path to m2 directory, if using a custom path.")
    private String m2Path;

    @Parameter(names = {"--downloadedBefore", "-db"}, description = "Delete all libraries (even if latest version) downloaded on or before this date (MM-DD-YYYY).", converter = DateToMillisConverter.class)
    private Long downloadedBefore = 0L;

    @Parameter(names = {"--accessedBefore", "-ab"}, description = "Delete all libraries (even if latest version) last accessed on or before this date (MM-DD-YYYY).", converter = DateToMillisConverter.class)
    private Long accessedBefore = 0L;

    @Parameter(names = {"--ignoreArtifacts", "-ia"}, description = "Comma separated list of groupId:artifactId combination to be ignored."/*, converter = StringToListConverter.class*/)
    private List<String> ignoreArtifacts;

    @Parameter(names = {"--ignoreGroups", "-ig"}, description = "Comma separated list of groupIds (full or part) to be ignored."/*, converter = StringToListConverter.class*/)
    private List<String> ignoreGroups;

    @Parameter(names = {"--deleteAllSnapshots", "-dsn"}, description = "Delete all snapshots irrespective of being latest.")
    private boolean deleteAllSnapshots;

    @Parameter(names = {"--deleteSource", "-dsr"}, description = "Delete sources for all libraries.")
    private boolean deleteSource;

    @Parameter(names = {"--deleteJavadoc", "-djd"}, description = "Delete javadocs for all libraries.")
    private boolean deleteJavadoc;

    @Parameter(names = {"--forceArtifacts", "-fa"}, description = "Comma separated list of groupId:artifactId combination to be deleted."/*, converter = StringToListConverter.class*/)
    private List<String> forceArtifacts;

    @Parameter(names = {"--forceGroups", "-fg"}, description = "Comma separated list of groupIds (full or part) to be deleted."/*, converter = StringToListConverter.class*/)
    private List<String> forceGroups;

    @Parameter(names = {"--dryrun", "-dr"}, description = "Do not delete files, just simulate and print result.")
    private boolean dryRun;

    @Parameter(names = {"--retainOld", "-ro"}, description = "Retain the artifacts even if old versions. Only process the configured inputs.")
    private boolean retainOld;

    public String getM2Path() {
        return m2Path;
    }

    public void setM2Path(String m2Path) {
        this.m2Path = m2Path;
    }

    public Long getDownloadedBefore() {
        return downloadedBefore;
    }

    public void setDownloadedBefore(Long downloadedBefore) {
        this.downloadedBefore = downloadedBefore;
    }

    public Long getAccessedBefore() {
        return accessedBefore;
    }

    public void setAccessedBefore(Long accessedBefore) {
        this.accessedBefore = accessedBefore;
    }

    public List<String> getIgnoreArtifacts() {
        return ignoreArtifacts;
    }

    public void setIgnoreArtifacts(List<String> ignoreArtifacts) {
        this.ignoreArtifacts = ignoreArtifacts;
    }

    public List<String> getIgnoreGroups() {
        return ignoreGroups;
    }

    public void setIgnoreGroups(List<String> ignoreGroups) {
        this.ignoreGroups = ignoreGroups;
    }

    public boolean isDeleteAllSnapshots() {
        return deleteAllSnapshots;
    }

    public void setDeleteAllSnapshots(boolean deleteAllSnapshots) {
        this.deleteAllSnapshots = deleteAllSnapshots;
    }

    public boolean isDeleteSource() {
        return deleteSource;
    }

    public void setDeleteSource(boolean deleteSource) {
        this.deleteSource = deleteSource;
    }

    public boolean isDeleteJavadoc() {
        return deleteJavadoc;
    }

    public void setDeleteJavadoc(boolean deleteJavadoc) {
        this.deleteJavadoc = deleteJavadoc;
    }

    public List<String> getForceArtifacts() {
        return forceArtifacts;
    }

    public void setForceArtifacts(List<String> forceArtifacts) {
        this.forceArtifacts = forceArtifacts;
    }

    public List<String> getForceGroups() {
        return forceGroups;
    }

    public void setForceGroups(List<String> forceGroups) {
        this.forceGroups = forceGroups;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public void setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
    }

    public boolean isRetainOld() {
        return retainOld;
    }

    public void setRetainOld(boolean retainOld) {
        this.retainOld = retainOld;
    }

    static class DateToMillisConverter implements IStringConverter<Long> {

        @Override
        public Long convert(String value) {
            try {
                return DateUtils.parseDate(value, "MM-dd-yyyy").getTime();
            } catch (ParseException e) {
                System.out.println("Could not parse " + value + " as a valid date. Please enter in MM-DD-YYYY format.");
                System.exit(1);
                return 0L;
            }
        }
    }

}
