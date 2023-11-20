package org.example.entity;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "sequenceIdentifier")
public  class SequenceIdentifier {
    @Id
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "0"),
    })
    @GeneratedValue(generator = "sequence", strategy= GenerationType.SEQUENCE)
    private int id;
}
