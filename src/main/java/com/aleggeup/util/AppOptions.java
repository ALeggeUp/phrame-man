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

	private AppOptions() {
	}

	@SuppressWarnings("static-access")
	protected static final Option help = OptionBuilder.withLongOpt("help").hasArg(false).isRequired().create('h');
}