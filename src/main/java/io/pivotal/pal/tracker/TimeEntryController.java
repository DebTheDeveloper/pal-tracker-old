package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
//@RequestMapping(value = {"/time-entries"})
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value = {"/time-entries"})
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry response = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = {"/time-entries/{id}"})
    public ResponseEntity<TimeEntry> read(@PathVariable(value = "id") long timeEntryId) {
        TimeEntry response = timeEntryRepository.find(timeEntryId);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/time-entries"})
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> response = timeEntryRepository.list();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = {"/time-entries/{id}"})
    public ResponseEntity<?> update(@PathVariable(value = "id") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry response = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/time-entries/{id}"})
    public ResponseEntity<TimeEntry> delete(@PathVariable(value = "id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
