package io.getarrays.contactapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.getarrays.contactapi.domain.Note;
import java.util.Optional;


@Repository
public interface NoteRepo extends JpaRepository<Note, String>{
    Optional<Note> findById(String id);
}
