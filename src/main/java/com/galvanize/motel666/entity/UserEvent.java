package com.galvanize.motel666.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String userId;

    private Date timestamp = new Date();

    private String context;

    @Builder
    public UserEvent(String type, String userId, String context) {
        this.type = type;
        this.userId = userId;
        this.context = context;
    }
}
