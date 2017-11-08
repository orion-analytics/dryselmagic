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

import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.orchestral.automation.drysel.json.JsonDeserializer;
import com.orchestral.automation.dryselmagic.helper.FileHelper;
import com.orchestral.automation.dryselmagic.model.MagicTest;
import com.orchestral.automation.dryselmagic.model.MagicTestSuite;
import com.orchestral.automation.dryselmagic.model.annotations.MagicTestOptions;

public class MagicTestRunner extends Runner {

	private final Class<?> magicTestClass;
	private RunNotifier runNotifier;
	private WebDriver webDriver = null;

	public MagicTestRunner(final Class<?> magicTestClass) throws Exception {
		super();
		this.magicTestClass = magicTestClass;
		readTestClassAnnotations();
	}

	private void readTestClassAnnotations() throws FileNotFoundException {
		final Annotation annotation = this.magicTestClass.getAnnotation(MagicTestOptions.class);
		final MagicTestOptions options = (MagicTestOptions) annotation;
		// set base paths of test files
		FileHelper.setBasePathOfTestDescriptions(options.pathOfTestDescriptions());
		FileHelper.setBasePathOfTestObjects(options.pathOfTestObjects());
	}

	private void initializeWebDriver() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
		this.webDriver = new FirefoxDriver();
		this.webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
	}

	private void quitWebDriver() {
		this.webDriver.quit();
		this.webDriver = null;
	}

	@Override
	public Description getDescription() {
		return Description.createSuiteDescription(this.magicTestClass);

	}

	@Override
	public void run(final RunNotifier notifier) {
		this.runNotifier = notifier;
		// deserialize test-descriptoins
		try {
			for (final String descriptionFile : FileHelper.getPathsOfDescriptionFiles()) {
				final MagicTestSuite magicTestSuite = new JsonDeserializer<MagicTestSuite>(MagicTestSuite.class,
						descriptionFile).deserializeJsonObject();
				// execute magic test
				for (final MagicTest magicTest : magicTestSuite.getTestList()) {
					try {
						// initialize web-driver
						initializeWebDriver();
						// run setups
						runSetups(magicTestSuite);
						// run givens
						runGivens(magicTest);
						// run whens
						runWhens(magicTest);
						// run thens
						runThens(magicTest);
					} catch (final Exception e) {
						throw new RuntimeException(e);
					} finally {
						// run teardowns
						runTeardowns(magicTestSuite);
						// quit web-driver
						quitWebDriver();
					}
				}
			}
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void runSetups(final MagicTestSuite magicTestSuite) {
		if (!magicTestSuite.getSetupList().isEmpty()) {
			new MagicTestExecutor(this.runNotifier).runSetups(this.webDriver, magicTestSuite.getSetupList());
		}
	}

	private void runGivens(final MagicTest magicTest) {
		if (!magicTest.getGivenList().isEmpty()) {
			new MagicTestExecutor(this.runNotifier).runGivens(this.webDriver, magicTest.getGivenList());
		}
	}

	private void runWhens(final MagicTest magicTest) {
		if (!magicTest.getWhenList().isEmpty()) {
			new MagicTestExecutor(this.runNotifier).runWhens(this.webDriver, magicTest.getWhenList());
		}
	}

	private void runThens(final MagicTest magicTest) {
		if (!magicTest.getThenList().isEmpty()) {
			new MagicTestExecutor(this.runNotifier).runThens(this.webDriver, magicTest.getThenList());
		}
	}

	private void runTeardowns(final MagicTestSuite magicTestSuite) {
		if (!magicTestSuite.getTeardownList().isEmpty()) {
			new MagicTestExecutor(this.runNotifier).runTeardowns(this.webDriver, magicTestSuite.getTeardownList());
		}
	}
}
