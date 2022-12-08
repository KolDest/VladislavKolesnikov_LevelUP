package ru.levelp.at.homework2;

import static ru.levelp.at.homework2.TestGroupName.POSITIVE;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("ru.levelp.at")
@IncludeTags(POSITIVE)
public class PositiveSuite {
}
