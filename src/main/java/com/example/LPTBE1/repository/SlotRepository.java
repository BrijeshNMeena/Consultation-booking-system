package com.example.LPTBE1.repository;

import com.example.LPTBE1.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    Slot findBySlotId(String slotId);
}
