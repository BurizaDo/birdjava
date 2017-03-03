package com.burizado.service.impl;

import com.burizado.mapper.BankMapper;
import com.burizado.mapper.BankXMLMapper;
import com.burizado.model.Bank;
import com.burizado.service.BankService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BurizaDo on 3/3/17.
 */
@Component
public class BankServiceImpl implements BankService {
    @Autowired
    BankMapper bankMapper;
    @Autowired
    BankXMLMapper xmlMapper;

    @Override
    public List<Bank> getBankByAddress(String address) {
//        return bankMapper.findBankByCity(address);
        return bankMapper.findBankByAddress(address);
    }

    @Override
    public List<Bank> getBank(String address) {
        try {
            InetSocketTransportAddress ip = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);

            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress((ip));
            SearchResponse response = client.prepareSearch("bank")
                    .setQuery(QueryBuilders.termQuery("address", address)).get();
            List<Bank> banks = new ArrayList<>();
            for(SearchHit sh : response.getHits()) {
                Bank b = new Bank();
                b.address = (String)sh.getSource().get("address");
                b.city = (String)sh.getSource().get("city");
                b.firstname = (String)sh.getSource().get("firstname");
                banks.add(b);
            }
            return banks;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bank> getBankXML(String address) {
        return xmlMapper.getBank(address);
    }
}
