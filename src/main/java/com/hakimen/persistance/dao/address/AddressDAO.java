package com.hakimen.persistance.dao.address;

import com.hakimen.model.Appointment;
import com.hakimen.model.auxiliar.Address;
import com.hakimen.persistance.dao.DAO;

import javax.persistence.NoResultException;
import java.util.List;

public interface AddressDAO extends DAO<Integer, Address> {

}
