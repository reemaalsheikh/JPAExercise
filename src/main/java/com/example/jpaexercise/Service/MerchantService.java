package com.example.jpaexercise.Service;

import com.example.jpaexercise.Model.Merchant;
import com.example.jpaexercise.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;


    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    public void addMerchant (Merchant merchant) {
       merchantRepository.save(merchant);
    }

    public boolean updateMerchant (Integer id,Merchant merchant) {
        Merchant m = merchantRepository.getById(id);

        if(m == null) {
            return false;
        }
        m.setMerchantName(merchant.getMerchantName());
        merchantRepository.save(m);
        return true;
    }

    public boolean deleteMerchant (Integer id) {
        Merchant m = merchantRepository.getById(id);

        if(m == null) {
            return false;
        }
        merchantRepository.delete(m);
        return true;
    }

}
