package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Test;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public static final Storage sortedStorage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }


}
