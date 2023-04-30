package com.biseo.modules.common.address.infra;

import com.biseo.modules.common.address.domain.Address;

import java.util.List;

public interface AddressRepositoryExtension {

    public List<Address> findByAddressGroupBySidoCide();

}
