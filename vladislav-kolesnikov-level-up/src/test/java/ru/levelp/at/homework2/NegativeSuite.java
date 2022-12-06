package ru.levelp.at.homework2;

import static ru.levelp.at.homework2.TestGroupName.NEGATIVE;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("ru.levelp.at")
@IncludeTags(NEGATIVE)
public class NegativeSuite {
}
