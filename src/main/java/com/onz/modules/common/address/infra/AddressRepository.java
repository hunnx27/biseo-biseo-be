package com.onz.modules.common.address.infra;

import com.onz.modules.common.address.domain.Address;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>,
        AddressRepositoryExtension {

//    Optional<Account> findByUserId(String userId);

    PageImpl<Address> findBySidoCode(int sidoCode, Pageable pageable);
    String FIND_DISTINCT_IDS = "SELECT DISTINCT a.sigungu_code as sigunguCode ,a.sido_name as sidoName ,a.sigungu_name as sigunguName from address a where a.sigungu_code = :sigungu_code";

    @Query(value = FIND_DISTINCT_IDS, nativeQuery = true)
    List<DistinctAddressResponse> findDistinctBySigunguCode(String sigungu_code);

}