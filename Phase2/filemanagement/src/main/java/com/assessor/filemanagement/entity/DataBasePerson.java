package com.assessor.filemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

@Entity
@Table(name="person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Profile("postgres")
public class DataBasePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Postgres auto-increment
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING) // store enum name
    @Column(name = "color", nullable = false)
    private Farbe color;

}
