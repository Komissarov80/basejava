package com.urise.webapp.storage;

import org.junit.jupiter.api.Test;

class ListStorageTest extends AbstractArrayStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test
    void overflowArrayException() {
    }
}