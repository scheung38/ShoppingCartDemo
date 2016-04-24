package com.chotguy.service

import com.chotguy.domain.ProductItem

/**
  * Till Service class parses list of strings to products and calculates total
  *
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
    * Groups basket by the products and returns a map containing products as keys and items qty as the value
    */
  private lazy val groupedByProducts = basket.foldLeft(Map.empty[ProductItem, Int]) {
    (products: Map[ProductItem, Int], product: ProductItem) =>
      products + (product -> (1 + products.getOrElse(product, 0)))
  }

  /**
    * Calculates basket total as per products offers
    */
  lazy val total = groupedByProducts.foldLeft(0.0) {
    (total, kv) =>
      total + (kv._1.price * ((kv._2 % kv._1.offer.getQty) +
        (kv._2 / kv._1.offer.getQty * kv._1.offer.forPriceOfQty)))
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
