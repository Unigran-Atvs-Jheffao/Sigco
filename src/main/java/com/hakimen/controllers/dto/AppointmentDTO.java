package com.hakimen.controllers.dto;

import com.hakimen.controllers.dto.auxiliar.AttachmentDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;

import java.util.HashSet;
import java.util.List;

public class AppointmentDTO implements DTO<Appointment> {

    private Integer id;
    private EmployeeDTO withDentist;
    private String observations;
    private List<AttachmentDTO> attachments;
    private Float value;

    public AppointmentDTO(){

    }

    public Integer getId() {
        return id;
    }

    public AppointmentDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public EmployeeDTO getWithDentist() {
        return withDentist;
    }

    public AppointmentDTO setWithDentist(EmployeeDTO withDentist) {
        this.withDentist = withDentist;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public AppointmentDTO setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public AppointmentDTO setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
        return this;
    }

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.value = appointment.getValue();
        this.attachments = appointment.getAttachments().stream().map(AttachmentDTO::new).toList();
        this.withDentist = new EmployeeDTO(appointment.getWithDentist());
        this.observations = appointment.getObservations();
    }

    public Float getValue() {
        return value;
    }

    public AppointmentDTO setValue(Float value) {
        this.value = value;
        return this;
    }

    @Override
    public Appointment build() throws InvalidValueException {
        Appointment appointment = new Appointment();

        appointment.setId(id != null && id > 0 ? id : null);

        appointment.setWithDentist(withDentist.build());

        appointment.setObservations(observations != null && !observations.isBlank() ? observations : "");

        if (attachments != null) {
            appointment.setAttachments(new HashSet<>());
            for (AttachmentDTO attachment : attachments) {
                appointment.getAttachments().add(attachment.build());
            }
        }

        if(value != null && value > 0){
            appointment.setValue(value);
        }

        return appointment;
    }
}
