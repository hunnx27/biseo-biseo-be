package com.onz.modules.common.address.infra;

import com.onz.modules.common.address.domain.Address;

import java.util.List;

public interface AddressRepositoryExtension {

    public List<Address> findByAddressGroupBySidoCide();

}
