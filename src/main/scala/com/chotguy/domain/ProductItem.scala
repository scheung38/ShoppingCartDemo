package com.chotguy.domain

/**
 * Product Item Domain Object
 * @param id Product Id
 * @param price Product Price
 * @param offer Product offer
 */

case class ProductItem(id: String, price: Double, offer: Offer) {
  override def toString = id
}

/**
+ * Product Offer Domain Object
+ * @param getQty Get product items qty
+ * @param forPriceOfQty For the price of qty
+ */

case class Offer(getQty: Int, forPriceOfQty: Int)