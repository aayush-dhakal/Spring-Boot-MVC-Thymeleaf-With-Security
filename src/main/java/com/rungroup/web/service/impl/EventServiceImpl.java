package com.rungroup.web.service.impl;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.Event;
import com.rungroup.web.respositoy.ClubRepository;
import com.rungroup.web.respositoy.EventRepository;
import com.rungroup.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.web.mapper.ClubMapper.mapToClub;
import static com.rungroup.web.mapper.EventMapper.mapToEvent;
import static com.rungroup.web.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired // this annotation is not mandatory
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto); // get Even type from eventDto type
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }

//    public static Event mapToEvent(EventDto eventDto) {
//        return Event.builder()
//                .id(eventDto.getId())
//                .name(eventDto.getName())
//                .startTime(eventDto.getStartTime())
//                .endTime(eventDto.getEndTime())
//                .type(eventDto.getType())
//                .photoUrl(eventDto.getPhotoUrl())
//                .createdOn(eventDto.getCreatedOn())
//                .updatedOn(eventDto.getUpdatedOn())
//                .club(eventDto.getClub())
//                .build();

//        public static EventDto mapToEventDto(Event event) {
//            return EventDto.builder()
//                    .id(event.getId())
//                    .name(event.getName())
//                    .startTime(event.getStartTime())
//                    .endTime(event.getEndTime())
//                    .type(event.getType())
//                    .photoUrl(event.getPhotoUrl())
//                    .createdOn(event.getCreatedOn())
//                    .updatedOn(event.getUpdatedOn())
//                    .club(event.getClub())
//                    .build();
//        }
    }

