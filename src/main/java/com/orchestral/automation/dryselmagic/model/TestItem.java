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
package com.orchestral.automation.dryselmagic.model;

public class TestItem implements Comparable<TestItem> {
	private final int number;
	private final String description;
	private final String uiFilePath;
	private final String dataFilePath;

	public TestItem(final int number, final String description, final String uiFilePath,
			final String dataFilePath) {
		this.number = number;
		this.description = description;
		this.uiFilePath = uiFilePath;
		this.dataFilePath = dataFilePath;
	}

	public int getNumber() {
		return this.number;
	}

	public String getDescription() {
		return this.description;
	}

	public String getUiFilePath() {
		return this.uiFilePath;
	}

	public String getDataFilePath() {
		return this.dataFilePath;
	}

	@Override
	public int compareTo(final TestItem testItem) {
		return this.number - testItem.number;
	}
}
