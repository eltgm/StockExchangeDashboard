package ru.sultanyarov.stockexchangedashboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.sultanyarov.stockexchangedashboard.domain.User;
import ru.sultanyarov.stockexchangedashboard.repository.UserRepository;
import ru.sultanyarov.stockexchangedashboard.user.dto.Stock;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class UserControllerTests {
    private final static String CORRECT_USER_ID = "760fe85e-639c-45c0-adfb-07d1a209b5a5";
    private final static UserDetails CORRECT_USER = new User("eltgm", "hash");

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        User user = new User("eltgm", "hash");
        user.setUserId(UUID.fromString(CORRECT_USER_ID));
        userRepository.save(user);
    }

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("Должен вернуть 302 тк пользователь не авторизован")
    void testGetNotExistUser() throws Exception {
        UserDetails user = new User("not_exist_user", "hash");

        mvc.perform(get("/api/v1/user")
                        .with(user(user)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Должен вернуть 200")
    void testGetExistUser() throws Exception {
        mvc.perform(get("/api/v1/user")
                        .with(user(CORRECT_USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("eltgm"));
    }

    @Test
    @Transactional
    @DisplayName("Должен вернуть 200 и успешно добавить акцию в избранное пользователю")
    void testAddFavoriteStock() throws Exception {
        Stock stock = new Stock();
        stock.setTicker("ticker");

        mvc.perform(post("/api/v1/user/{userId}/stock/add", CORRECT_USER_ID)
                        .content(objectMapper.writeValueAsString(stock))
                        .contentType(APPLICATION_JSON)
                        .with(user(CORRECT_USER))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticker").value("ticker"));
    }

    @Test
    @DisplayName("Должен вернуть 400 тк пытаемся добавить пустую акцию")
    void testAddEmptyStock() throws Exception {
        Stock stock = new Stock();

        mvc.perform(post("/api/v1/user/{userId}/stock/add", CORRECT_USER_ID)
                        .content(objectMapper.writeValueAsString(stock))
                        .contentType(APPLICATION_JSON)
                        .with(user(CORRECT_USER))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
