package com.ssummit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkpoint_marks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "checkpointmarks_seq", allocationSize = 1)
public class CheckpointMark {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "checkpoint_id",
			foreignKey = @ForeignKey(name = "FK_CHECKPOINT")
	)
	private Checkpoint checkpoint;

	@Column(name = "scheduled_mark_time")
	private LocalDateTime scheduledMarkedTime; // Запланированное отмеченное время

	@Column(name = "actual_marked_time")
	private LocalDateTime actualMarkedTime; // Фактическое отмеченное время

	@Column(name = "message_send")
	private boolean messageSend;

	@ManyToOne
	@JoinColumn(
			name = "tour_id",
			foreignKey = @ForeignKey(name = "FK_CHECKPOINT_MARK_TOUR")
	)
	private Tour tour;

    @Builder
    public CheckpointMark(Long id, Checkpoint checkpoint, LocalDateTime scheduledMarkedTime, LocalDateTime actualMarkedTime) {
        this.id = id;
        this.checkpoint = checkpoint;
        this.scheduledMarkedTime = scheduledMarkedTime;
        this.actualMarkedTime = actualMarkedTime;
    }
}
