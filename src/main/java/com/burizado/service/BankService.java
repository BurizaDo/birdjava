package com.burizado.service;

import com.burizado.model.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BurizaDo on 3/3/17.
 */

public interface BankService {
    List<Bank> getBankByAddress(String address);
    List<Bank> getBank(String address);

    List<Bank> getBankXML(String address);
}
