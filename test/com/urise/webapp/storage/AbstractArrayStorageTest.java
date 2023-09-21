package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest {

    Storage storage;
    public static final Resume r1 = new Resume("Misha");
    public static final Resume r2 = new Resume("Dima");
    public static final Resume r3 = new Resume("Anton");
    protected static final int STORAGE_LIMIT = 10000;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setValue() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    void save() {
        assertEquals(3, storage.size());
        Resume r4 = new Resume("Valera");
        storage.save(r4);
        assertEquals(r4, storage.get("Valera"));
    }

    @Test
    void saveException() {
        Resume r4 = new Resume("Misha");
        assertThrows(IllegalArgumentException.class, () -> storage.save(r4));
        Resume r5 = new Resume(null);
        assertThrows(IllegalArgumentException.class, () -> storage.save(r4));
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
        Resume r4 = new Resume("Artur");
        storage.save(r4);
        assertEquals(4, storage.size());
    }

    @Test
    void getAll() {
        Resume[] resumesTest = {new Resume("Misha"), new Resume("Dima"), new Resume("Anton")};
        // For test sortedArrayStorage I sort array for checking result
        if (this.getClass().getName().equals("com.urise.webapp.storage.SortedArrayStorageTest")) {
            Arrays.sort(resumesTest);
        }
        assertArrayEquals(resumesTest, storage.getAll());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void get() {
        assertEquals(r1, storage.get("Misha"));
    }

    @Test
    void getException() {
        assertThrows(IllegalArgumentException.class, () -> storage.get("Slava"));
    }

    @Test
    void delete() {
        storage.delete("Dima");
        Resume[] resumesTest = {new Resume("Misha"), new Resume("Anton")};
        // For test sortedArrayStorage I sort array for checking result
        if (this.getClass().getName().equals("com.urise.webapp.storage.SortedArrayStorageTest")) {
            Arrays.sort(resumesTest);
        }
        assertArrayEquals(resumesTest, storage.getAll());
    }

    @Test
    void deleteException() {
        assertThrows(IllegalArgumentException.class, () -> storage.delete("Slava"));
    }

    @Test
    void update() {
        Resume r4 = new Resume("Artur");
        storage.save(r4);
        r4.setUuid("Ura");
        assertEquals(r4.getUuid(), "Ura");
    }

    @Test
    void updateException() {
        Resume r4 = new Resume("Artur");
        assertThrows(IllegalArgumentException.class, () -> storage.update(r4));
    }

    @Test
    void overflowArrayException() {
        Resume r4 = new Resume("Artur");
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(String.valueOf(i)));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Storage overflow ahead of time. Test failed");
        }
        assertEquals(10000, storage.size());
        assertThrows(IllegalArgumentException.class, () -> storage.save(r4));
    }

}