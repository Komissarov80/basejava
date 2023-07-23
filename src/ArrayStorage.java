/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;
    private int cursor = -1;

    public ArrayStorage() {
        size = 0;
    }

    public int getCursor() {
        return cursor;
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
        cursor = -1;
    }

    void save(Resume r) {
        storage[++cursor] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                if(i == size - 1) {
                    storage[i] = null;
                    size--;
                    cursor--;
                } else {
                    System.arraycopy(storage, (i + 1), storage, i, (size - i - 1));
                    storage[size - 1] = null;
                    size--;
                    cursor--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resultsResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            resultsResume[i] = storage[i];
        }
        return resultsResume;
    }

    int size() {
        return size;
    }
}
