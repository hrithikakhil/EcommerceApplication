package com.ecomm.project.services;


import com.ecomm.project.dtos.AddressDTO;
import com.ecomm.project.models.User;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddress();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getUserAddress(User user);

    AddressDTO updateAddress(AddressDTO addressDTO, Long addressId);

    String deleteAddressById(Long addressId);
}
