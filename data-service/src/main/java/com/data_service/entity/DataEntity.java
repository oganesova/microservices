package com.data_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String message;
}
