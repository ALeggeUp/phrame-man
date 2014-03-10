/**
 * App.java
 * 
 * Copyright 2014 [A Legge Up Consulting]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aleggeup.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Starter application
 * 
 * @author Stephen Legge
 */
public class App {

    public static void main(final String[] args) {
        final CommandLineParser parser = new PosixParser();
        final Options options = buildOptions();

        try {
            final CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption('h')) {
                printHelp(options);
                System.exit(0);
            }

        } catch (final ParseException e) {
            printHelp(options);
            System.exit(-1);
        }
    }

    private static Options buildOptions() {
        final Options options = new Options();

        options.addOption(AppOptions.help);

        return options;
    }

    private static void printHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "image-organizer", options );
    }

}
