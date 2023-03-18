package ru.sultanyarov.stockexchangedashboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sultanyarov.stockexchangedashboard.domain.Quote;
import ru.sultanyarov.stockexchangedashboard.domain.StockDto;
import ru.sultanyarov.stockexchangedashboard.domain.User;
import ru.sultanyarov.stockexchangedashboard.domain.UserStock;
import ru.sultanyarov.stockexchangedashboard.repository.StockRepository;
import ru.sultanyarov.stockexchangedashboard.repository.UserRepository;
import ru.sultanyarov.stockexchangedashboard.repository.UserStockRepository;

import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final FinhubService finhubService;
    private final UserStockRepository userStockRepository;
    private final StockRepository stockRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public UserService(UserRepository userRepository,
                       FinhubService finhubService,
                       UserStockRepository userStockRepository,
                       StockRepository stockRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       @Lazy AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.finhubService = finhubService;
        this.userStockRepository = userStockRepository;
        this.stockRepository = stockRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserLogin(username);
    }

    public User loadUserFullInfoByUsername(String username) {
        User user = userRepository.findByUserLogin(username);
        for (StockDto stock : user.getStocks()) {
            Quote quote = finhubService.loadQuoteByStockSymbol(stock.getTicker());
            stock.setCurrentPrice(quote.getCurrentPrice());
            stock.setChange(quote.getChange());
        }

        return user;
    }

    public StockDto addStockToFavorites(UUID userId, StockDto stock) {
        String ticker = stock.getTicker();
        log.info("Start adding stock {} to user {}", ticker, userId);
        String stockId = stockRepository.findByTicker(ticker);
        UUID uuidId = null;
        if (!hasText(stockId)) {
            log.info("Creating new stock - {} in DB...", ticker);
            stockRepository.save(stock);
        } else {
            uuidId = UUID.fromString(stockId);
            stock.setId(uuidId);
        }
        userStockRepository.save(new UserStock(userId, !hasText(stockId) ? stock.getId() : uuidId));

        var quote = finhubService.loadQuoteByStockSymbol(ticker);
        stock.setCurrentPrice(quote.getCurrentPrice());
        stock.setChange(quote.getChange());

        return stock;
    }

    public void removeStockFromFavorites(UUID userId, UUID stockId) {
        log.info("Start deleting stock {} from user {}", stockId, userId);
        userStockRepository.deleteByUserIdAndStockId(userId, stockId);
    }

    public void registrationUser(String username, String password) {
        User user = userRepository.findByUserLogin(username);
        if (user != null) {
            throw new RuntimeException("User already existed!");
        }

        userRepository.save(new User(username, passwordEncoder.encode(password)));

        addUserInSecurityContext(username, password);
    }

    private void addUserInSecurityContext(String username, String password) {
        var authReq = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
