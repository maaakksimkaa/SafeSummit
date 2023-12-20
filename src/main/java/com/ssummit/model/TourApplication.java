package com.ssummit.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tour_applications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "tourapplications_seq", allocationSize = 1)
public class TourApplication extends GenericModel {

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition="TEXT")
    private String desctiption;

    @Column(name = "outcoming_post_number")
    private String outcomingPostNumber; // Номер исходящего сообщения

    @Column(name = "application_date")
    private LocalDate applicationDate; // Дата подачи документовs

    @Column(name = "incoming_post_number")
    private String incomingPostNumber; // Номер входящего сообщения

    @Column(name = "application_registration_date")
    private LocalDate applicationRegistrationDate; // Дата регистрации заявки

    @Builder
    public TourApplication(Long id, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime,
                           String updatedBy, Boolean isDeleted, LocalDateTime deletedDateTime, String deletedBy,
                           String title, String desctiption, String outcomingPostNumber, LocalDate applicationDate,
                           String incomingPostNumber, LocalDate applicationRegistrationDate) {
        super(id, createdBy, createdDateTime, updatedDateTime, updatedBy, isDeleted, deletedDateTime, deletedBy);
        this.title = title;
        this.desctiption = desctiption;
        this.outcomingPostNumber = outcomingPostNumber;
        this.applicationDate = applicationDate;
        this.incomingPostNumber = incomingPostNumber;
        this.applicationRegistrationDate = applicationRegistrationDate;
    }
}
