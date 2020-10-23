package com.app.innoventes.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.innoventes.model.Address;
import com.app.innoventes.repository.AddressRepository;
import com.app.innoventes.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> getAllAddress() {
		return addressRepo.findAll();
	}

	@Override
	public Address createAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address getAddressById(long id) {
		Optional<Address> addr=addressRepo.findById(id);
		if(addr.isPresent()) {
			return addr.get();
		}
		else {
			return null;
		}
	}

	@Override
	public int deleteAddressById(long id) {
		Optional<Address> optionalAddress = addressRepo.findById(id);
		if (optionalAddress.isPresent()) {
			addressRepo.deleteById(id);
			return 1;
		}

		return 0;
	}

	@Override
	public Address updateAddress(Address address) {
		Optional<Address> optionalAddress=addressRepo.findById(address.getId());
		if(optionalAddress.isPresent()) {
			Address newAddr = optionalAddress.get();
			
			newAddr.setId(address.getId());
			newAddr.setCity(address.getCity());
			newAddr.setAddrLineOne(address.getAddrLineOne());
			newAddr.setAddrLineTwo(address.getAddrLineTwo());
			
		return addressRepo.save(newAddr);
		}
		else {
			return null;
		}
			
	}
	
	
	}


