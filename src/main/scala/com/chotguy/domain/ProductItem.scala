package com.chotguy.domain

/**
 * Product Item Domain Object
 * @param id Product Id
 * @param price Product Price
 */

case class ProductItem(id: String, price: Double) {
  override def toString = id
}

