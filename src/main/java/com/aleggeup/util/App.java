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
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

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

        System.out.println("source: " + sourceFile.getAbsolutePath());
        Collection<File> files = FileUtils.listFiles(sourceFile,
                new SuffixFileFilter(".jpg", IOCase.INSENSITIVE),
                TrueFileFilter.INSTANCE);
        for (final File file : files) {
            try {
                final InputStream inputStream = FileUtils.openInputStream(file);
                final Metadata metadata = new Metadata();
                final BodyContentHandler contentHandler = new BodyContentHandler();
                final AutoDetectParser fileParser = new AutoDetectParser();
                final String mimeType = new Tika().detect(file);
                metadata.set(Metadata.CONTENT_TYPE, mimeType);
                fileParser.parse(inputStream, contentHandler, metadata, new ParseContext());
                inputStream.close();
                System.out.println(file.getAbsolutePath() + " : " + mimeType);
                for (final String name : metadata.names()) {
                    System.out.println(name + " : " + metadata.get(name));
                }
                System.out.println(contentHandler.toString());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TikaException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("target: " + targetFile.getAbsolutePath());
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
