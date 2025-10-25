package com.shimita.crud.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "daily_prayer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DailyPrayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prayerId;
    private String username;
    private String phone;
    private String author;
    private String role;
    private String details;
    private String verse;
    private String imagePath;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
}
