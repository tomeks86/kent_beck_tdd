package edu.tseidler;

import java.util.LinkedList;
import java.util.List;

public class SoftAssert {
    private List<Boolean> tests = new LinkedList<>();

    private final String DEFAULT_MESSAGE = "wrong assertion in subtest: ";
    public static final String DEFAULT_HEADING = "\nerrors in subtests:\n";

    public void add(Boolean condition) {
        tests.add(condition);
    }

    public void run() {
        StringBuilder messages = new StringBuilder(DEFAULT_HEADING);

        for (int i = 0; i < tests.size(); i++) {
            try {
                assert tests.get(i);
            } catch (AssertionError e) {
                messages.append(DEFAULT_MESSAGE + (i+1) + "\n");
            }
        }

        if (!messages.toString().equals(DEFAULT_HEADING))
            throw new AssertionError(messages.toString());
    }
}
