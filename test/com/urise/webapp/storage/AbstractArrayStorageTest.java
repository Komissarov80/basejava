package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;
    public static final Resume r1 = new Resume("Misha");
    public static final Resume r2 = new Resume("Dima");
    public static final Resume r3 = new Resume("Anton");
    protected static final int STORAGE_LIMIT = AbstractArrayStorage.STORAGE_LIMIT;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setValue() {
        storage.save(r3);
        storage.save(r2);
        storage.save(r1);
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
        assertThrows(ExistStorageException.class, () -> storage.save(r4));
        Resume r5 = null;
        assertThrows(IllegalArgumentException.class, () -> storage.save(r5));
    }

    @Test
    void size() {
        assertSize(3);
        Resume r4 = new Resume("Artur");
        storage.save(r4);
        assertSize(4);
    }

    @Test
    void getAll() {
        Resume[] resumesTest = {r3, r2, r1};
        assertArrayEquals(resumesTest, storage.getAll());
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Resume[] resumes = {};
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test
    void getException() {
        assertThrows(NotExistStorageException.class, () -> storage.get("Slava"));
    }

    @Test
    void delete() {
        storage.delete("Dima");
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get("Dima"));
    }

    @Test
    void deleteException() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("Slava"));
    }

    @Test
    void update() {
        Resume r4 = new Resume("Artur");
        storage.save(r4);
        r4.setUuid("Ura");
        assertSame(r4.getUuid(), "Ura");
    }

    @Test
    void updateException() {
        Resume r4 = new Resume("Artur");
        assertThrows(NotExistStorageException.class, () -> storage.update(r4));
    }

    @Test
    void overflowArrayException() {
        Resume r4 = new Resume("Artur");
        storage.clear();
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(String.valueOf(i)));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Storage overflow ahead of time. Test failed");
        }
        assertSize(10000);
        assertThrows(StorageException.class, () -> storage.save(r4));
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

}