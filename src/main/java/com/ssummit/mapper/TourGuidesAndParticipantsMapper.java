package com.ssummit.mapper;

import org.springframework.stereotype.Component;

@Component
public class TourGuidesAndParticipantsMapper /*extends GenericMapper<Tour, TourGuidesAndParticipantsDto>*/ {

//    private final ModelMapper mapper;
//    private final UserRepository userRepository;
//
//    protected TourGuidesAndParticipantsMapper(ModelMapper mapper, UserRepository userRepository) {
//        super(mapper, Tour.class, TourGuidesAndParticipantsDto.class);
//        this.mapper = mapper;
//        this.userRepository = userRepository;
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Tour.class, TourGuidesAndParticipantsDto.class)
//                .addMappings(m -> m.skip(TourGuidesAndParticipantsDto::setParticipantsIds)).setPostConverter(toDtoConverter());
//        mapper.createTypeMap(TourGuidesAndParticipantsDto.class, Tour.class)
//                .addMappings(m -> m.skip(Tour::setParticipants)).setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    void mapSpecificFields(TourGuidesAndParticipantsDto source, Tour destination) {
//        destination.setParticipants(userRepository.findAllByIdIn(source.getParticipantsIds()));
//    }
//
//    @Override
//    void mapSpecificFields(Tour source, TourGuidesAndParticipantsDto destination) {
//        destination.setParticipantsIds(getIds(source));
//    }
//
//    private Set<Long> getIds(Tour tour) {
//        return Objects.isNull(tour) || Objects.isNull(tour.getId())
//                ? null
//                : tour.getParticipants().stream()
//                .map(GenericModel::getId)
//                .collect(Collectors.toSet());
//    }
}
