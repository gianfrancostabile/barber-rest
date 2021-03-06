package com.gfstabile.java.barberrest.repository;

import com.gfstabile.java.barberrest.entity.barber.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * The repository bean related to Barber entity
 *
 * @author G. F. Stabile
 */
@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    @Transactional
    @Modifying
    @Query(value = "update BARBERS b set b.name = :name, b.surname = :surname where b.internal_code = :internal_code",
        nativeQuery = true)
    void updateBarber(@Param("internal_code") String internalCode, @Param("name") String name,
        @Param("surname") String surname);

    @Transactional
    void deleteByInternalCode(String internalCode);

    Optional<Barber> findByInternalCode(String internalCode);
}
