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

import java.util.ArrayList;
import java.util.List;

public class MagicTestSuite {
	private String testSuiteDescription;
	private List<TestItem> setupList = new ArrayList<TestItem>();
	private List<MagicTest> testList = new ArrayList<MagicTest>();
	private List<TestItem> teardownList = new ArrayList<TestItem>();

	public MagicTestSuite() {

	}

	public String getTestSuiteDescription() {
		return this.testSuiteDescription;
	}

	public void setTestSuiteDescription(final String testSuiteDescription) {
		this.testSuiteDescription = testSuiteDescription;
	}

	public List<TestItem> getSetupList() {
		return this.setupList;
	}

	public List<MagicTest> getTestList() {
		return this.testList;
	}

	public void setTestList(final List<MagicTest> testList) {
		this.testList = testList;
	}

	public List<TestItem> getTeardownList() {
		return this.teardownList;
	}

	public void setTeardownList(final List<TestItem> teardownList) {
		this.teardownList = teardownList;
	}

	public void setSetupList(final List<TestItem> setupList) {
		this.setupList = setupList;
	}
}
