package com.ecomm.project.services;

import com.ecomm.project.dtos.AddressDTO;
import com.ecomm.project.exceptions.ResourceNotFoundException;
import com.ecomm.project.models.Address;
import com.ecomm.project.models.User;
import com.ecomm.project.repositories.AddressRepository;
import com.ecomm.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);

        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        address.setUser(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddress() {
        List<Address> addresses = addressRepository.findAll();
       List<AddressDTO> addressDTOS = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
       return addressDTOS;
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address addressFromDB = addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address", "addressId", addressId));
        return modelMapper.map(addressFromDB, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddress(User user) {
        List<Address> addresses = user.getAddresses();
        return addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO, Long addressId) {
        Address addressFromDB = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        addressFromDB.setCity(addressDTO.getCity());
        addressFromDB.setPincode(addressDTO.getPincode());
        addressFromDB.setState(addressDTO.getState());
        addressFromDB.setStreet(addressDTO.getStreet());
        addressFromDB.setCountry(addressDTO.getCountry());
        addressFromDB.setBuildingName(addressDTO.getBuildingName());

        Address updatedAddress = addressRepository.save(addressFromDB);

        User user = addressFromDB.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);

        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddressById(Long addressId) {
        Address addressFromDB = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        User user = addressFromDB.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDB);

        return "Address successfully deleted with addressId: " + addressId;
    }
}
