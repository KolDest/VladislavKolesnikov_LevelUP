package ru.levelp.at;

import static ru.levelp.at.TestGroupName.NEGATIVE;
import static ru.levelp.at.TestGroupName.POSITIVE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LuckyTicketTest {
    @ParameterizedTest
    @CsvSource({
        "435552",
        "000000",
        "009333"
    })
    @Tag(POSITIVE)
    void positiveCase(String input) {
        boolean actual = LuckyTicket.isLuckyTicket(input);
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({
        "11111",
        "222223",
        "123120",
        "abc123",
        "aaaaaa",
        "AAAAAA"
    })
    @Tag(NEGATIVE)
    void parameterizedNegativeCase(String input) {
        boolean actual = LuckyTicket.isLuckyTicket(input);
        Assertions.assertFalse(actual);
    }
}
