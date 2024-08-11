package com.example.jpaexercise.Service;

import com.example.jpaexercise.Model.MerchantStock;
import com.example.jpaexercise.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;

    public List<MerchantStock> getMerchantStock() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock (MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    public boolean updateMerchantStock (Integer id,MerchantStock merchantStock) {
        MerchantStock oldmerchantStock = merchantStockRepository.getById(id);

        if (oldmerchantStock == null) {
            return false;
        }
        oldmerchantStock.setProductId(merchantStock.getProductId());
        oldmerchantStock.setMerchantId(merchantStock.getMerchantId());
        oldmerchantStock.setStock(merchantStock.getStock());

        merchantStockRepository.save(oldmerchantStock);
        return true;
    }


    public boolean deleteMerchantStock (Integer id) {
        MerchantStock OldmerchantStock = merchantStockRepository.getById(id);
        if (OldmerchantStock == null) {
            return false;
        }
        merchantStockRepository.delete(OldmerchantStock);
        return true;
    }

    public boolean addStock (Integer productId, Integer MerchantId, Integer MerchantStockId, int stock ) {
        MerchantStock OldmerchantStock = merchantStockRepository.getById(MerchantStockId);
        if (OldmerchantStock.getProductId()==productId && OldmerchantStock.getMerchantId()==MerchantId && OldmerchantStock.getStock() > 0) {
            OldmerchantStock.setStock(OldmerchantStock.getStock() + stock);

            merchantStockRepository.save(OldmerchantStock);
            return true;
        }else
            return false;
    }

}
