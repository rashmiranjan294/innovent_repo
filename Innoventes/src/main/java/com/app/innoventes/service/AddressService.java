package com.app.innoventes.service;

import java.util.List;

import com.app.innoventes.model.Address;

public interface AddressService {
	
    public List<Address> getAllAddress();
	
	public Address createAddress(Address address);

	public Address getAddressById(long id);

	public int deleteAddressById(long id);

	public Address updateAddress(Address address);
	
}
