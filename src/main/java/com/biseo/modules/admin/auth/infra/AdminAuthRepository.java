package com.biseo.modules.admin.auth.infra;

import com.biseo.modules.account.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAuthRepository extends JpaRepository<Admin, Long>{
}
