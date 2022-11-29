package de.hsos.swa.studvw.control;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.studvw.entity.StudentIn;

@ApplicationScoped
public class StudentInMgr {
    private final ConcurrentMap<Long, StudentIn> studs = new ConcurrentHashMap<>();
    private static AtomicLong matnrCounter = new AtomicLong(1000);

    public boolean addStudent(final String name) {
        StudentIn stud = new StudentIn(StudentInMgr.matnrCounter.incrementAndGet(), name);
        return (this.studs.put(stud.getMatnr(), stud) == null);
    }

    public Optional<StudentIn> getStudentByMatnr(Long matnr) {
        return Optional.ofNullable(studs.get(matnr));
    }

    public Collection<StudentIn> getAll() {
        return this.studs.values();
    }
}
