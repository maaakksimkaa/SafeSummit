package com.ssummit.repository;

import com.ssummit.model.CheckpointMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface CheckpointMarkRepository extends JpaRepository<CheckpointMark, Long> {
	// Возвращает первый CheckpointMark по id, где ActualMarkedTime != null.
	// Результаты сортируются в порядке возрастания ActualMarkedTime.
	CheckpointMark getFirstByTour_IdAndActualMarkedTimeNotNullOrderByActualMarkedTimeAsc(Long id);

	// Возвращает первый CheckpointMark по id, где ScheduledMarkedTime < передаваемого ScheduledMarkedTime и ActualMarkedTime == null
	// Результаты сортируются в порядке возрастания ScheduledMarkedTime.
	CheckpointMark getFirstByTour_IdAndScheduledMarkedTimeBeforeAndActualMarkedTimeNullOrderByScheduledMarkedTimeAsc(Long id, LocalDateTime scheduledMarkedTime);

    // Возвращает первый CheckpointMark, у которого ActualMarkedTime != null.
	// Результаты сортируются в порядке возрастания ActualMarkedTime.
	CheckpointMark findFirstByActualMarkedTimeNotNullOrderByActualMarkedTimeAsc();

	// Возвращает первый CheckpointMark, где ActualMarkedTime == null и ActualMarkedTime < передаваемого actualMarkedTime.
	// Результаты сортируются в порядке убывания ScheduledMarkedTime.
	CheckpointMark getFirstByActualMarkedTimeNullAndActualMarkedTimeBeforeOrderByScheduledMarkedTimeDesc(LocalDateTime actualMarkedTime);

    // Возвращает первый CheckpointMark с непустым ActualMarkedTime.
	// Результаты сортируются в порядке убывания ActualMarkedTime.
	CheckpointMark getFirstByActualMarkedTimeNotNullOrderByActualMarkedTimeDesc();

    // Возвращает первый CheckpointMark, где ScheduledMarkedTime == передаваемому scheduledMarkedTime и ActualMarkedTime == null.
	//  Результаты сортируются в порядке возрастания ScheduledMarkedTime.
	CheckpointMark getFirstByScheduledMarkedTimeAndActualMarkedTimeNullOrderByScheduledMarkedTimeAsc(LocalDateTime scheduledMarkedTime);

    // Возвращает первый CheckpointMark, у которого ScheduledMarkedTime равно передаваемому scheduledMarkedTime и ActualMarkedTime позже указанного времени передаваемого actualMarkedTime.
	// Результаты сортируются в порядке возрастания ActualMarkedTime.
	CheckpointMark findFirstByScheduledMarkedTimeAndActualMarkedTimeAfterOrderByActualMarkedTimeAsc(@Nullable LocalDateTime scheduledMarkedTime, LocalDateTime actualMarkedTime);

    // Возвращает set CheckpointMarks по id
	Set<CheckpointMark> findAllByIdIn(Set<Long> ids);
}
