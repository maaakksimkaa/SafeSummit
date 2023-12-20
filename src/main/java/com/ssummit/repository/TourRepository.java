package com.ssummit.repository;

import com.ssummit.model.Tour;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TourRepository extends GenericRepository<Tour> {
	// Возвращает первый Tour, где ActualMarkedTime в CheckpointMarks = null, ScheduledMarkedTime в CheckpointMarks < передаваемого scheduledMarkedTime
	Tour getFirstByCheckpointMarks_ActualMarkedTimeNullAndCheckpointMarks_ScheduledMarkedTimeBeforeOrderByCheckpointMarks_ScheduledMarkedTimeAsc(LocalDateTime scheduledMarkedTime);

	// Возвращает list Tours, где startDate < передаваемого startDate && endDate > передаваемого endDate.
	List<Tour> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

	Set<Tour> findAllByIdIn(Set<Long> ids); // Возвращает set Users по id
}
