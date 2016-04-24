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
    new Fixture(Array(apple, orange, orange), 3, 1.1),
    new Fixture(Array(apple, apple), 2, 0.6),
    new Fixture(Array(apple, apple, apple), 3, 1.2),
    new Fixture(Array(orange, orange, orange), 3, 0.5),
    new Fixture(Array(orange, orange, orange, orange), 4, 0.75),
    new Fixture(Array(apple, apple, orange, orange, orange), 5, 1.1)
  )

  "Input with zero products" should behave like addToBasketAndCheckout(fixtures(0))

  "Input with two products" should behave like addToBasketAndCheckout(fixtures(1))

  "Input with invalid products" should behave like addToBasketAndCheckout(fixtures(2))

  "Multiple Products qty which doesn't qualify for offers" should behave like addToBasketAndCheckout(fixtures(3))

  "Apples qty qualifying offer" should behave like addToBasketAndCheckout(fixtures(4))

  "Apples qty qualifying offer and without qualifying offer" should behave like addToBasketAndCheckout(fixtures(5))

  "Oranges qty qualifying offer" should behave like addToBasketAndCheckout(fixtures(6))

  "Oranges qty qualifying offer and without qualifying offer" should behave like addToBasketAndCheckout(fixtures(7))

  "Mixed products qty qualifying offer" should behave like addToBasketAndCheckout(fixtures(8))

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
