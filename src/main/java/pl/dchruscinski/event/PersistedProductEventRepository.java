package pl.dchruscinski.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PersistedProductEventRepository extends JpaRepository<PersistedProductEvent, Integer> {
}