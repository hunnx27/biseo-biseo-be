package com.onz.modules.admin.auth.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AdminAuthRepository extends JpaRepository<Admin, Long>{
}
