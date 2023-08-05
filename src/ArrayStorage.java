import static java.util.Arrays.copyOf;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    /*
   More suitable method from Arrays class for method clear is copyOf whit zero length
    */
    void clear() {
        storage = copyOf(storage, 0);
        size = 0;
    }

    void save(Resume r) {
        boolean foundResume = false;
        if (size == storage.length) {
            System.out.println("storage is full, can't add resume, size is " + size);
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                System.out.println("already there are in storage resume whit uuid=" + r.uuid);
                foundResume = true;
            }
        }
        if (!foundResume) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("there are not in storage resume whit uuid=" + uuid + " to getting");
        return null;
    }

    void delete(String uuid) {
        boolean foundResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[--size] = null;
                foundResume = true;
            }
        }
        if (!foundResume) {
            System.out.println("there are not in storage resume whit uuid=" + uuid + " to deleting");
        }
    }

    void update(Resume resume) {
        boolean foundResume = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                storage[i] = resume;
                foundResume = true;
            }
        }
        if (!foundResume) {
            System.out.println("there are not in storage resume whit uuid=" + resume.uuid + " to updating");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     * More suitable method from Arrays class for method getAll is copyOf whit size length
     */
    Resume[] getAll() {
        return copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
