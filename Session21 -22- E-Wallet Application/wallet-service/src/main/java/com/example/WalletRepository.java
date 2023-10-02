package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
public interface WalletRepository extends JpaRepository<Wallet,Integer> {
    Wallet findByPhone(String phone);

    @Modifying
    @Query("update Wallet w set w.balance = w.balance+ ?2 where w.phone= ?1")
    void updateWallet(String phone,Double amount);

}
