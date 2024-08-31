package com.ecomm.project.controller;

import com.ecomm.project.dtos.AddressDTO;
import com.ecomm.project.models.User;
import com.ecomm.project.services.AddressService;
import com.ecomm.project.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDto = addressService.createAddress(addressDTO, user);
        return new ResponseEntity<>(savedAddressDto, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddress() {
        List<AddressDTO> addressList = addressService.getAddress();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long addressId) {
        AddressDTO address = addressService.getAddressById(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddress() {
        User user = authUtil.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddress(user);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> createAddress(@PathVariable Long addressId, @Valid @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddressDto = addressService.updateAddress(addressDTO, addressId);
        return new ResponseEntity<>(updatedAddressDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        String status = addressService.deleteAddressById(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
