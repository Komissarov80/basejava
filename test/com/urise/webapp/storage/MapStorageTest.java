package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapStorageTest extends AbstractArrayStorageTest{

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    void overflowArrayException() {

    }
}