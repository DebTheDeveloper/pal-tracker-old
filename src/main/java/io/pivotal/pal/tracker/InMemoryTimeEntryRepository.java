package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> dataBase = new HashMap<>();
    private long count = 1l;

    public TimeEntry create(TimeEntry timeEntry) {
//        long id = dataBase.keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1;
        long id = count++;
        timeEntry.setId(id);
        dataBase.put(id, timeEntry);
        return dataBase.get(id);
    }

    public TimeEntry find(long id) {
        return dataBase.get(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = null;
        if (Objects.nonNull(dataBase.get(id))) {
            timeEntry.setId(id);
            dataBase.put(id, timeEntry);
            updatedTimeEntry = dataBase.get(id);
        }

        return updatedTimeEntry;
    }

    public void delete(long id) {
        dataBase.remove(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> values = new ArrayList<>();
        dataBase.forEach((x, y) -> values.add(y));
        return values;
    }
}
