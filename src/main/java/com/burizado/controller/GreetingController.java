package com.burizado.controller;

import com.burizado.model.Bank;
import com.burizado.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BurizaDo on 3/3/17.
 */
@RestController
public class GreetingController {
    @Autowired
    BankService bankService;

    @RequestMapping("/")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/greeting")
    public List<Bank> greeting(@RequestParam(value="address", defaultValue = "shit") String address){
        return bankService.getBank(address);
    }

    @RequestMapping("/mybatis")
    public List<Bank> mybatis(@RequestParam(value = "address", defaultValue = "shit") String address){
        return bankService.getBankByAddress("%" + address + "%");
    }

    @RequestMapping("/xml")
    public List<Bank> xml(@RequestParam(value = "address", defaultValue = "shit") String address){
        return bankService.getBankXML(address);
    }

}
