package com.urise.webapp.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MapResumeStorageTest extends AbstractArrayStorageTest{

    public MapResumeStorageTest() {
        super(new MapResumeStorage());

    }
}