package ru.levelp.at.homework3;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({SendDraftEmailTest.class, SendRuleEmailTest.class, SendTrashEmailTest.class})
public class SeleniumSuite {

}
