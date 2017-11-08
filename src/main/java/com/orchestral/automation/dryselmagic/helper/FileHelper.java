/*
 * Copyright (c) Orchestral Developments Ltd and the Orion Health group of companies (2001 - 2017).
 * Author: Kuldeep Sinh Chauhan (@KuldeepSinhC)
 * emails: kuldeeps@orionhealth.com, chauhan.kuldeep.sinh@gmail.com
 *
 * This file is provided to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an  "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,  either express or implied.  See the License for the specific language governing    
 * permissions and limitations under the License.
 */
package com.orchestral.automation.dryselmagic.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
	private static String basePathOfTestDescriptions = "";
	private static String basePathOfTestObjects = "";

	public static String getBasePathOfTestDescriptions() {
		return FileHelper.basePathOfTestDescriptions;
	}

	public static void setBasePathOfTestDescriptions(final String basePathOfTestDescriptions) {
		FileHelper.basePathOfTestDescriptions = basePathOfTestDescriptions;
	}

	public static String getBasePathOfTestObjects() {
		return FileHelper.basePathOfTestObjects;
	}

	public static void setBasePathOfTestObjects(final String basePathOfTestObjects) {
		FileHelper.basePathOfTestObjects = basePathOfTestObjects;
	}

	public static List<String> getPathsOfDescriptionFiles() throws FileNotFoundException {
		return getPathsOfJsonFiles(getBasePathOfTestDescriptions());
	}

	public static List<String> getPathsOfTestObjectFiles() throws FileNotFoundException {
		return getPathsOfJsonFiles(getBasePathOfTestObjects());
	}

	private static List<String> getPathsOfJsonFiles(final String pathOfBaseFolder) throws FileNotFoundException {
		final File folder = new File(pathOfBaseFolder);
		final File[] listOfFiles = folder.listFiles();
		final List<String> listOfJsonFiles = new ArrayList<String>();

		for (final File each : listOfFiles) {
			if (each.isFile() && each.getName().endsWith(".json")) {
				listOfJsonFiles.add(Paths.get(pathOfBaseFolder, each.getName()).toString());
			}
		}
		if (!listOfJsonFiles.isEmpty()) {
			return listOfJsonFiles;
		} else {
			throw new FileNotFoundException("No JSON files found under " + pathOfBaseFolder + " folder.");
		}
	}
}
