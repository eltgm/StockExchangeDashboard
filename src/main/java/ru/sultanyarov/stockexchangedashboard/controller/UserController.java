package ru.sultanyarov.stockexchangedashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sultanyarov.stockexchangedashboard.mapper.StockMapper;
import ru.sultanyarov.stockexchangedashboard.mapper.UserMapper;
import ru.sultanyarov.stockexchangedashboard.service.UserService;
import ru.sultanyarov.stockexchangedashboard.user.UserApi;
import ru.sultanyarov.stockexchangedashboard.user.dto.Stock;
import ru.sultanyarov.stockexchangedashboard.user.dto.User;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
@RequestMapping("/api/v1")
public class UserController implements UserApi {
    private final UserService userService;
    private final UserMapper userMapper;
    private final StockMapper stockMapper;

    public UserController(UserService userService, UserMapper userMapper, StockMapper stockMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.stockMapper = stockMapper;
    }

    @Override
    public ResponseEntity<User> getUserInfo() {
        String username = ((ru.sultanyarov.stockexchangedashboard.domain.User) (SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()))
                .getUsername();
        return ok(userMapper.domainUserToDto(userService.loadUserFullInfoByUsername(username)));
    }

    @Override
    public ResponseEntity<Stock> addStockToFavorite(UUID userId, Stock stock) {
        return ok(stockMapper.domainDtoToDto(userService.addStockToFavorites(userId, stockMapper.dtoStockToDomainDto(stock))));
    }

    @Override
    public ResponseEntity<Void> removeStockFromFavorite(UUID userId, UUID stockId) {
        userService.removeStockFromFavorites(userId, stockId);
        return ok().build();
    }
}
