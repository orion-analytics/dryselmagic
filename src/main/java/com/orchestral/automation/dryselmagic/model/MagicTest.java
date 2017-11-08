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

public class MagicTest {
	private String scenario;
	private List<TestItem> givenList = new ArrayList<TestItem>();
	private List<TestItem> whenList = new ArrayList<TestItem>();
	private List<TestItem> thenList = new ArrayList<TestItem>();

	public MagicTest() {

	}

	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(final String scenario) {
		this.scenario = scenario;
	}

	public List<TestItem> getGivenList() {
		return this.givenList;
	}

	public void setGivenList(final List<TestItem> givenList) {
		this.givenList = givenList;
	}

	public List<TestItem> getWhenList() {
		return this.whenList;
	}

	public void setWhenList(final List<TestItem> whenList) {
		this.whenList = whenList;
	}

	public List<TestItem> getThenList() {
		return this.thenList;
	}

	public void setThenList(final List<TestItem> thenList) {
		this.thenList = thenList;
	}
}
