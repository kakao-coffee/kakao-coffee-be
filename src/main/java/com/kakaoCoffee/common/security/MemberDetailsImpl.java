package com.kakaoCoffee.common.security;

import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MemberDetailsImpl implements UserDetails {

    private final MemberRoleEnum memberRoleEnum;

    private final String memberId;

    public MemberDetailsImpl(MemberRoleEnum memberRoleEnum, String memberId) {
        this.memberRoleEnum = memberRoleEnum;
        this.memberId = memberId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = memberRoleEnum.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}