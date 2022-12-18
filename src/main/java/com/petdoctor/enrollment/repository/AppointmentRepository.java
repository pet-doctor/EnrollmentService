package com.petdoctor.enrollment.repository;

import com.petdoctor.enrollment.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findAppointmentEntityById(Long appointmentId);
    Optional<List<AppointmentEntity>> findAppointmentEntitiesByClientId(Long clientId);
}
