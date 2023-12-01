package com.urise.webapp.model;

import java.util.Comparator;

public class ResumeComparator {

    Comparator<Resume> comparator;

    public ResumeComparator() {
        comparator = Comparator.comparing(Resume::getFullName).
                thenComparing(Resume::getUuid);
    }

}
