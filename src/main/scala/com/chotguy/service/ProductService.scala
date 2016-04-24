package com.chotguy.service

import com.chotguy.domain.ProductItem
import com.chotguy.service.MemoryProductService.products

/**
 * Product Service Trait
 */
trait ProductService {

  def getProduct(id: String): Option[ProductItem] = {
    None
  }

}

/**
 * Memory Product Service companion object to provide in-memory static data
 */
object MemoryProductService {

  private val products = Map(
    "APPLE" -> new ProductItem("Apple", .60),
    "ORANGE" -> new ProductItem("Orange", .25)
  )

}

/**
 * Memory Product Service to provide products from in-memory
 */
trait MemoryProductService extends ProductService {

  override def getProduct(id: String): Option[ProductItem] = {
    products.get(id.toUpperCase)
  }

}