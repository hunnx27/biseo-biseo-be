package com.onz.modules.account.web;

import static com.onz.common.testenv.WithAccountFixture.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.onz.infra.MockMvcTest;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.infra.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@MockMvcTest
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("계정 전체 조회")
    @Test
    @WithAccount(USERNAME)
    void findAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].name").value("dlo"));
    }

//    @DisplayName("계정 단건 조회")
//    @Test
//    @WithAccount(USERNAME)
//    void findAccount() throws Exception {
//        Long id = accountRepository.findByName(USERNAME).getId();
//        mockMvc.perform(get("/api/accounts/{id}", id))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.name").value("dlo"));
//    }

//    @DisplayName("계정 변경")
//    @Test
//    @WithAccount(USERNAME)
//    void updateAccount() throws Exception {
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "변경 고");
//        map.put("userId", "변경이메일");
//        map.put("age", "12");
//        map.put("location", "판교");
//
//        Long id = accountRepository.findByName(USERNAME).getId();
//        mockMvc.perform(put("/api/accounts/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(map)))
//            .andDo(print())
//            .andExpect(status().isOk());
//
//        Account account = accountRepository.findByPlainUserId(map.get("userId")).get();
//        ModelMapper modelMapper = new ModelMapper();
//        Account compare = modelMapper.map(map, Account.class);
//        assertEquals(account.getUserId(), compare.getUserId());
//    }

//    @DisplayName("계정 삭제")
//    @Test
//    @WithAccount(USERNAME)
//    void deleteAccount() throws Exception {
//        Long id = accountRepository.findByName(USERNAME).getId();
//        mockMvc.perform(delete("/api/accounts/{id}", id))
//            .andDo(print())
//            .andExpect(status().isOk());
//    }
}