package com.onz.modules.admin.menu.infra;

import com.onz.modules.admin.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>,
        MenuRepositoryExtension {
    List<Menu> findByMainCodeEqualsOrderBySubCodeAsc(Long mainCode);

    List<Menu> findAllByOrderByMainCodeAscSubCodeAsc();
    boolean existsByMainCodeAndSubCode(Long mainCode, Long subCode);

//    @Query("select  from plates.menu m  WHERE 1=1 AND main_code =:mainCode AND sub_code =:subCode ")
//    Menu menuinfo(@Param("mainCode")Long mainCode,@Param("subCode")Long subCode);
}
