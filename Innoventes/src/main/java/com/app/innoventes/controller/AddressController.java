package com.app.innoventes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.innoventes.model.Address;
import com.app.innoventes.response.ApiResponse;
import com.app.innoventes.service.AddressService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getAllAddress")
	public ApiResponse getAllAddress(){
		List<Address> addrList  = addressService.getAllAddress();
		if(addrList != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Total "+addrList.size()+ " Address found", addrList);
		}
		else {
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Address not found", null);
		}
	}
	
	@PostMapping("/createAddress")
	public ApiResponse createAddress(@RequestBody Address address){
		Address addRecord= addressService.createAddress(address); 
		if(addRecord != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Address added", addRecord);
		}
		else {
			return new ApiResponse(HttpStatus.EXPECTATION_FAILED.value(), "Address registration failed", null);
		}
	}
	
	@GetMapping("/getAddressById/{id}")
	public ApiResponse getAddressById(@PathVariable("id") long id){
		
		Address getAddr=addressService.getAddressById(id);
		//getEmp
		if(getAddr != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Address found", getAddr);
		}
		else {
			
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Address not found with id: "+id, null);
		}
	}
	
	@PutMapping("/editAddress")
	public ApiResponse editAddressById(@Valid @RequestBody Address address) {
		Address editAddress= addressService.updateAddress(address);
		
		if(editAddress != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Address updated with id: "+address.getId(), editAddress);
		}
		else {
			
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Address not found with id: "+address.getId(), null);	
		}
	}
	
	@DeleteMapping("/deleteAddress/{id}")
	public ApiResponse deleteAddressById(@PathVariable("id") long id){
		int status= addressService.deleteAddressById(id);
		
		if(status == 1) {
			return new ApiResponse(HttpStatus.OK.value(), "Address deleted with id: "+id, null);
		}else {
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Address not found with id: "+id, null);
		}
	}
	

}
