/*
 * Copyright (c) 2018.
 * This code is released under The 3-Clause BSD License.
 * https://github.com/techpavan
 */

package com.steeplesoft.maven;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import picocli.CommandLine;

public class ArgData {

    @CommandLine.Option(names = {"--path", "-p"},
            description = "Path to m2 directory, if using a custom path.")
    protected String m2Path;

    @CommandLine.Option(names = {"--downloadedBefore", "-db"},
            description = "Delete all libraries (even if latest version) downloaded on or before this date (MM-DD-YYYY).",
            converter = DateToMillisConverter.class)
    protected Long downloadedBefore = 0L;

    @CommandLine.Option(names = {"--accessedBefore", "-ab"},
            description = "Delete all libraries (even if latest version) last accessed on or before this date (MM-DD-YYYY).",
            converter = DateToMillisConverter.class)
    protected Long accessedBefore = 0L;

    @CommandLine.Option(names = {"--ignoreArtifacts", "-ia"},
            description = "Comma separated list of groupId:artifactId combination to be ignored.")
    protected List<String> ignoreArtifacts;

    @CommandLine.Option(names = {"--ignoreGroups", "-ig"},
            description = "Comma separated list of groupIds (full or part) to be ignored.")
    protected List<String> ignoreGroups;

    @CommandLine.Option(names = {"--deleteAllSnapshots", "-dsn"},
            description = "Delete all snapshots irrespective of being latest.")
    protected boolean deleteAllSnapshots;

    @CommandLine.Option(names = {"--deleteSource", "-dsr"},
            description = "Delete sources for all libraries.")
    protected boolean deleteSource;

    @CommandLine.Option(names = {"--deleteJavadoc", "-djd"},
            description = "Delete javadocs for all libraries.")
    protected boolean deleteJavadoc;

    @CommandLine.Option(names = {"--forceArtifacts", "-fa"},
            description = "Comma separated list of groupId:artifactId combination to be deleted.")
    protected List<String> forceArtifacts;

    @CommandLine.Option(names = {"--forceGroups", "-fg"},
            description = "Comma separated list of groupIds (full or part) to be deleted.")
    protected List<String> forceGroups;

    @CommandLine.Option(names = {"--dryrun", "-dr"},
            description = "Do not delete files, just simulate and print result.")
    protected boolean dryRun;

    @CommandLine.Option(names = {"--retainOld", "-ro"},
            description = "Retain the artifacts even if old versions. Only process the configured inputs.")
    protected boolean retainOld;

    static class DateToMillisConverter implements CommandLine.ITypeConverter<Long> {
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
