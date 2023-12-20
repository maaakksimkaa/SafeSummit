package com.ssummit.dto;

import com.ssummit.model.Route;
import com.ssummit.model.TourApplication;
import com.ssummit.model.TourEquipment;
import com.ssummit.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDto extends GenericDto {

    private String title;
    private String description;
    private User primaryGuide;
    private User secondaryGuide;
    private Set<Long> participantsIds;
    private Route route;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private TourApplication tourApplication;
    private TourEquipment tourEquipment;
    private Set<Long> checkpointMarksIds;
}
