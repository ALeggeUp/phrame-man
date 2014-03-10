/**
 * AppOptions.java
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

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

/**
 * @author Stephen Legge
 */
public final class AppOptions {

    protected static final String HELP_HEADER = "Specify options for working with photos\n";
    protected static final String HELP_FOOTER = "\n"
            + "Copyright 2014 [A Legge Up Consulting]\n"
            + "Licensed under the Apache License, Version 2.0\n"
            + "* \n"
            + "Please report issues at https://github.com/ALeggeUp/phrame-man/issues\n";

    private AppOptions() {
    }

    /**
     * This is a standard "help" option
     */
    @SuppressWarnings("static-access")
    protected static final Option HELP = OptionBuilder.withLongOpt("help").hasArg(false)
            .withDescription("Print this help").create('h');

    @SuppressWarnings("static-access")
    protected static final Option SOURCE_DIR = OptionBuilder.withLongOpt("source").hasArg(true).withArgName("DIR")
            .withDescription("Source directory").isRequired().create('s');

    @SuppressWarnings("static-access")
    protected static final Option TARGET_DIR = OptionBuilder.withLongOpt("target").hasArg(true).withArgName("DIR")
            .withDescription("Target directory").isRequired().create('t');
}
