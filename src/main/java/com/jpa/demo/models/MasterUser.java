package com.jpa.demo.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "master_user")

public class MasterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    public MasterUser() {}
    public MasterUser(String fullName) {
        this.fullName = fullName;
    }
    public MasterUser(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
