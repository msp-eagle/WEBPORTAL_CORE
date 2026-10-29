package com.msp.webportal.businesslogic.service.event;


import com.msp.webportal.businesslogic.dto.event.EventImagedto;
import com.msp.webportal.businesslogic.model.event.EventImage;
import com.msp.webportal.businesslogic.model.event.Evententity;
import com.msp.webportal.businesslogic.repository.events.EventRepo;
import com.msp.webportal.businesslogic.repository.events.EventimageRepo;
import com.msp.webportal.businesslogic.repository.events.EventscontentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
     private EventRepo eventRepo;
    @Autowired
    private EventscontentRep eventscontentRep;
    @Autowired
    private EventimageRepo eventimageRepo;

//
//    public Events saveEvents(Eventdto eventdto) throws IOException, ParseException {
//        Events events=new Events();
//        events.setEventitle(eventdto.getEventitle());
//        events.setDesc(eventdto.getDesc());
//        events.setImage(eventdto.getImage().getBytes());
//        DateFormat inputFormat = new SimpleDateFormat(
//                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
//        Date date = null;
//        try {
//            date = inputFormat.parse(eventdto.getTodate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        events.setTodate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//
//        events.setTime(LocalTime.parse(eventdto.getTime()));
//        return  eventRepo.save(events);
//    }
//
//
//
//    public List<Events> getList() {
//        return eventRepo.findAll();
//    }
//
//    public List<Events> getEventsByDate(LocalDate date) {
//        List<Events> data = eventRepo.findAllByEventDateBetween(date.with(TemporalAdjusters.firstDayOfMonth()),date.with(TemporalAdjusters.lastDayOfMonth()));
//        System.out.println(data);
//        return data;
//    }
//
//
//    public Events eventUpdate(Eventdto eventdto) throws IOException {
//        Events events=new Events();
//        events.setId(eventdto.getId());
//        events.setEventitle(eventdto.getEventitle());
//
//        events.setImage(eventdto.getImage().getBytes());
//        events.setDesc(eventdto.getDesc());
//        DateFormat inputFormat = new SimpleDateFormat(
//                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
//        Date date = null;
//        try {
//            date = inputFormat.parse(eventdto.getTodate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        events.setTodate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//
//        events.setTime(LocalTime.parse(eventdto.getTime()));
//
//        return  eventRepo.save(events);
//    }
//
//    public void delete(Events events) {
//        eventRepo.delete(events);
//    }

//
//    public Optional<Events> findById(int id) {
//        Optional<Events> events = eventRepo.findById(id);
//        return events;
//    }

    @Autowired
    private EventscontentRep aboutRepository;


    public Evententity save(Evententity about){
        Evententity save = aboutRepository.save(about);
        return save;
    }

    public List<Evententity> list(){
        List<Evententity> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private Evententity updateDetails(long id, Evententity abouts) {

        Optional<Evententity> optionalUser = aboutRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Evententity about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Evententity modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public Evententity update(long id, Evententity aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {
        try{
            aboutRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Evententity findByid(Long id) {

        Optional<Evententity> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
    public EventImage saveEvents(EventImagedto eventdto) throws IOException, ParseException {
        EventImage events=new EventImage();
        events.setImage(eventdto.getImage().getBytes());
        return  eventimageRepo.save(events);
    }
    public List<EventImage> getList() {
        return eventimageRepo.findAll();
    }

    public EventImage eventUpdate(EventImagedto eventdto) throws IOException {
        EventImage events=new EventImage();
        events.setId(eventdto.getId());
        events.setImage(eventdto.getImage().getBytes());
        return  eventimageRepo.save(events);
    }

    public void delete(EventImage events) {
        eventimageRepo.delete(events);
    }


    public Optional<EventImage> findById(int id) {
        Optional<EventImage> events = eventimageRepo.findById(id);
        return events;
    }

}


