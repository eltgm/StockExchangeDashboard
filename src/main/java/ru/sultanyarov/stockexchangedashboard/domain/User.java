package ru.sultanyarov.stockexchangedashboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "user_table")
public class User implements UserDetails {
    public User(String userLogin, String passHash) {
        this.userLogin = userLogin;
        this.passHash = passHash;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID userId;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "pass_hash")
    private String passHash;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_stock",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "stock_id")}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter
    private List<StockDto> stocks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> "user");
    }

    @Override
    public String getPassword() {
        return passHash;
    }

    @Override
    public String getUsername() {
        return userLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
