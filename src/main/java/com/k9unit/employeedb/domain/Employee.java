package com.k9unit.employeedb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide an First Name")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_INITIAL", length = 1, nullable = true)
    private String middleInitial;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    @NotEmpty(message = "Please provide an Last Name")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_OF_BIRTH", length = 10, nullable = false)
    @NotNull(message = "Please provide an Date of Birth")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "EMPLOYMENT_DATE", length = 10, nullable = false)
    private LocalDate employmentDate;

    @Column(name = "STATUS_TYPE", length = 10, nullable = false)
    @NotNull(message = "The status needs to be ACTIVE or INACTIVE")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", employmentDate=" + employmentDate +
                ", status=" + status +
                '}';
    }
}
