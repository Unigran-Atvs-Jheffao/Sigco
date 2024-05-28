package com.hakimen.controllers.dto;

import com.hakimen.controllers.auxiliar.AttachmentController;
import com.hakimen.controllers.EmployeeController;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.model.Appointment;
import com.hakimen.model.Employee;
import com.hakimen.model.auxiliar.Attachment;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDTO implements DTO<Appointment> {

    private Integer id;
    private Integer withDentistId;
    private String observations;
    private List<Integer> attachments;
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

    public Integer getWithDentistId() {
        return withDentistId;
    }

    public AppointmentDTO setWithDentistId(Integer withDentistId) {
        this.withDentistId = withDentistId;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public AppointmentDTO setObservations(String observations) {
        this.observations = observations;
        return this;
    }

    public List<Integer> getAttachments() {
        return attachments;
    }

    public AppointmentDTO setAttachments(List<Integer> attachments) {
        this.attachments = attachments;
        return this;
    }

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.value = appointment.getValue();
        this.attachments = appointment.getAttachments().stream().map(Attachment::getId).toList();
        this.withDentistId = appointment.getWithDentist().getId();
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

        try {
            Employee dentist = EmployeeController.INSTANCE.getById(withDentistId).build();
            if (dentist.getLogin().getRole().getId() == 2)
                appointment.setWithDentist(dentist);
            else
                throw new InvalidValueException("O funcionário não é um dentista");
        } catch (NoResultException e) {
            throw new InvalidValueException("Funcionário inválido", e);
        }

        appointment.setObservations(observations != null && !observations.isBlank() ? observations : "");

        if (attachments != null) {
            appointment.setAttachments(new ArrayList<>());
            for (Integer ids : attachments) {
                Attachment attachment;
                try {
                    attachment = AttachmentController.getDAO().getById(ids);
                } catch (NoResultException e) {
                    throw new InvalidValueException("O anexo com id %s é inválido".formatted(ids), e);
                }
                appointment.getAttachments().add(attachment);
            }
        }

        if(value != null && value > 0){
            appointment.setValue(value);
        }

        return appointment;
    }
}
