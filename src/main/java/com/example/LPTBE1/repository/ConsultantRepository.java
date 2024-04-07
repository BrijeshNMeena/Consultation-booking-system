package com.example.LPTBE1.repository;

import com.example.LPTBE1.model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {
    Consultant findByEmail(String emailId);
}
