package com.galvanize.motel666.repository;

import com.galvanize.motel666.entity.UserEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserEventRepo extends CrudRepository<UserEvent, Long> {
    List<UserEvent> findAllByTimestampBetween(Date d1, Date d2);
    List<UserEvent> findAllByUserId(String userId);
}
