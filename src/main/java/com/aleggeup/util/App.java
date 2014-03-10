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

import java.io.File;
import java.io.IOException;

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

        File sourceFile = null;
        File targetFile = null;

        try {
            final CommandLine commandLine = parser.parse(options, args);

            if (commandLine.getOptions().length == 0 || commandLine.hasOption('h')) {
                printHelp(options);
                System.exit(0);
            }

            if (commandLine.hasOption('s')) {
                sourceFile = new File(commandLine.getOptionValue('s')).getCanonicalFile();
            }

            if (commandLine.hasOption('t')) {
                targetFile = new File(commandLine.getOptionValue('t')).getCanonicalFile();
            }

        } catch (final ParseException e) {
            printHelp(options);
            System.exit(-1);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        }
    }

    private static Options buildOptions() {
        final Options options = new Options();

        options.addOption(AppOptions.SOURCE_DIR);
        options.addOption(AppOptions.TARGET_DIR);
        options.addOption(AppOptions.HELP);

        return options;
    }

    private static void printHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();

        formatter.setLeftPadding(4);
        formatter.printHelp("phrame-man", AppOptions.HELP_HEADER, options, AppOptions.HELP_FOOTER, true);
    }

}
