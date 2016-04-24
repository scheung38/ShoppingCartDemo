package com.chotguy.service

import com.chotguy.domain.ProductItem

/**
 * Till Service class parses list of strings to products and calculates total
 * @param input array of strings containing product ids
 */
class TillService(input: Array[String]) extends ProductService {

  /**
   * Parses array of product ids to list of products
   */
  private val basket = input.foldRight(List.empty[ProductItem]) {
    (productId: String, list: List[ProductItem]) =>
      val product = getProduct(productId)
      if (product.nonEmpty)
        product.get :: list
      else
        list
  }

  /**
   * Calculates basket total
   */
  lazy val total = basket.foldLeft(0.0) {
    (total: Double, product: ProductItem) =>
      total + product.price
  }

  /**
   * Gives basket items size count
   */
  val count = basket.size

  /**
   * Displays items in basket and their total
   */
  def checkout() = println("[ %s ] => £%.2f".format(basket.mkString(", "), total))

}
