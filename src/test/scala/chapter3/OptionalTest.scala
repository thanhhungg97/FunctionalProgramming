package chapter3

import chapter4.Optional
import org.scalatest.funsuite.AnyFunSuiteLike

class OptionalTest extends AnyFunSuiteLike {

  test("testOrElse") {
    val orElse = Optional(5).filter((item: Int) => item % 2 == 0).getOrElse( 3)
    assert(3 == orElse)
  }

  test("testFlatMap") {
    val integerOption = Optional.apply(5).flatMap((item: Int) => Optional(item * 2))
    assert(Optional(10) == integerOption)
  }

  test("testFilter") {
    val filter = Optional.apply(5).filter((item: Int) => item % 2 == 0)
    assert(chapter4.None == filter)

  }



  test("testGetOrElse") {
    val orElse = chapter4.None.getOrElse(5)
    assert(5 == orElse)

  }

  test("testMap") {
    val value = Optional(5).map(item => item * 2)
    assert(Optional(10) == value)
  }

  test("testMap2") {

  }

  test("testNone") {

  }

}
