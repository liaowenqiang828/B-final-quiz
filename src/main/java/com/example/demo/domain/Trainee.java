package com.example.demo.domain;

import com.example.demo.constants.ErrorConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = ErrorConstants.NAME_ERROR_MESSAGE)
    private String name;
    @NotNull(message = ErrorConstants.OFFICE_ERROR_MESSAGE)
    private String office;
    @NotNull(message = ErrorConstants.EMAIL_ERROR_MESSAGE)
    @Email(message = ErrorConstants.EMAIL_FORMAT_ERROR_MESSAGE)
    private String email;
    @NotNull(message = ErrorConstants.ZOOM_ID_ERROR_MESSAGE)
    private String zoomId;
    @NotNull(message = ErrorConstants.GITHUB_ERROR_MESSAGE)
    private String gitHub;

    @JsonIgnore
    private String grouped;
}
