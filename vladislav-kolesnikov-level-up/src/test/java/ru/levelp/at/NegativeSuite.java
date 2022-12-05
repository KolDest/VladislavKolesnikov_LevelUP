package ru.levelp.at;

import static ru.levelp.at.TestGroupName.NEGATIVE;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("ru.levelp.at")
@IncludeTags(NEGATIVE)
public class NegativeSuite {
}
