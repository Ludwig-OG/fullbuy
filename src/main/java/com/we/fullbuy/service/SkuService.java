package com.we.fullbuy.service;

import com.we.fullbuy.pojo.Sku;

public interface SkuService {
    /*添加Sku*/
    int addSku(Sku sku);
    /*删除Sku*/
    int deleteSku(int skuId);
    /*查询Sku*/
    Sku showSku(int skuId);
    /*更新Sku*/
    int updateSku(Sku sku);
    /*获取价格*/
    Sku getPrice(int productId, int itemId, int secondItemId);

}
