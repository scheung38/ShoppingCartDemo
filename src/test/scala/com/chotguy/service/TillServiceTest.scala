package com.chotguy.service

import org.scalatest.{FlatSpec, Matchers}

class TillServiceTest extends FlatSpec with TillServiceBehavior {

  val apple: String = "apple"
  val orange: String = "orange"
  val invalid = "invalid"

  val fixtures = Array(
    new Fixture(Array(), 0, 0.0),
    new Fixture(Array(apple, orange), 2, 0.85),
    new Fixture(Array(invalid, apple, invalid, orange), 2, 0.85),
    new Fixture(Array(apple, orange, apple, apple, orange), 5, 2.3)
  )

  "Input with zero products" should behave like addToBasketAndCheckout(fixtures(0))

  "Input with two products" should behave like addToBasketAndCheckout(fixtures(1))

  "Input with invalid products" should behave like addToBasketAndCheckout(fixtures(2))

  "Input with multiple products" should behave like addToBasketAndCheckout(fixtures(3))

}

trait TillServiceBehavior extends Matchers {
  this: FlatSpec =>
  def addToBasketAndCheckout(fixture: Fixture): Unit = {

    val classUnderTest = new TillService(fixture.input) with MemoryProductService

    it should "have correct number of products" in {
      classUnderTest.count should be(fixture.productsCount)
    }

    it should "calculate correct basket total" in {
      classUnderTest.total should be(fixture.total)
    }

  }
}

case class Fixture(input: Array[String], productsCount: Int, total: Double)
