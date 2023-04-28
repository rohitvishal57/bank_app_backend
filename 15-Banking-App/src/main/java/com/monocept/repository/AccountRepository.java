package com.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monocept.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {



}
