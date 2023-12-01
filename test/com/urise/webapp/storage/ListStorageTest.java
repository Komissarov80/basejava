package com.urise.webapp.storage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ListStorageTest extends AbstractArrayStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Disabled
    void overflowArrayException() {
    }
}