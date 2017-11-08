
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
package com.orchestral.automation.dryselmagic;

import java.nio.file.Paths;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebDriver;

import com.orchestral.automation.drysel.json.JsonTestScriptExecutor;
import com.orchestral.automation.dryselcore.eventlib.ElementNotEnabledException;
import com.orchestral.automation.dryselmagic.helper.FileHelper;
import com.orchestral.automation.dryselmagic.model.TestItem;

public class MagicTestExecutor {

	private final RunNotifier runNotifier;

	public MagicTestExecutor(final RunNotifier runNotifier) {
		this.runNotifier = runNotifier;
	}

	public void runSetups(final WebDriver webDriver, final List<TestItem> setupList) {
		executeInputScripts(webDriver, setupList, "Setup : ");
	}

	public void runGivens(final WebDriver webDriver, final List<TestItem> givenList) {
		executeInputScripts(webDriver, givenList, "Given : ");
	}

	public void runWhens(final WebDriver webDriver, final List<TestItem> whenList) {
		executeInputScripts(webDriver, whenList, "When : ");
	}

	public void runThens(final WebDriver webDriver, final List<TestItem> thenList) {
		executeOutputScripts(webDriver, thenList, "Then : ");
	}

	public void runTeardowns(final WebDriver webDriver, final List<TestItem> teardownList) {
		executeInputScripts(webDriver, teardownList, "Teardown : ");
	}

	private void executeInputScripts(final WebDriver webDriver, final List<TestItem> tesetItemList,
			final String methodName) {
		for (final TestItem testItem : tesetItemList) {
			// fire test started
			fireTestStarted(methodName + testItem.getDescription());
			// execute script
			try {
				final String basePathOfTestObjects = FileHelper.getBasePathOfTestObjects();
				new JsonTestScriptExecutor(webDriver).executeInputScript(
						Paths.get(basePathOfTestObjects, testItem.getUiFilePath()).toString(),
						Paths.get(basePathOfTestObjects, testItem.getDataFilePath()).toString());
			} catch (final ElementNotEnabledException e) {
				e.printStackTrace();
			}
			// fire test finished
			fireTestFinished(methodName + testItem.getDescription());
		}
	}

	private void executeOutputScripts(final WebDriver webDriver, final List<TestItem> testItemList,
			final String methodName) {
		for (final TestItem testItem : testItemList) {
			// fire test started
			fireTestStarted(methodName + testItem.getDescription());
			// execute script
			try {
				final String basePathOfTestObjects = FileHelper.getBasePathOfTestObjects();
				new JsonTestScriptExecutor(webDriver).executeOutputScript(
						Paths.get(basePathOfTestObjects, testItem.getUiFilePath()).toString(),
						Paths.get(basePathOfTestObjects, testItem.getDataFilePath()).toString());
			} catch (final ElementNotEnabledException e) {
				e.printStackTrace();
			}
			// fire test finished
			fireTestFinished(methodName + testItem.getDescription());
		}
	}

	private void fireTestStarted(final String testName) {
		this.runNotifier.fireTestStarted(createTestDescription(testName));
	}

	private void fireTestFinished(final String testName) {
		this.runNotifier.fireTestFinished(createTestDescription(testName));
	}

	private Description createTestDescription(final String testName) {
		return Description.createTestDescription(MagicTestRunner.class, testName);
	}

}
