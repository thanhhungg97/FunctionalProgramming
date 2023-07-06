package chapter2

import org.scalatest.funsuite._

class ListTest extends AnyFunSuiteLike {

  test("Test map function") {
    val tmp = List[Int](1, 2, 3).map(item => item * 2)
    assert(List(2, 4, 6).equals(tmp))
  }
  test("Test fold Right function") {
    val tmp = List.foldRight(List[Int](1, 2, 3), 0)((a, b) => a + b)
    assert(6.equals(tmp))
  }
  test("Test fold Right function 1") {
    val tmp = List.foldRight(List[Int](1, 2, 3), 1)((a, b) => a * b)
    assert(6.equals(tmp))
  }
  test("Test fold Right function 2") {
    val tmp = List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)) // recesive
    assert(List(3, 2, 1).equals(tmp))
  }
  test("Test length function") {
    val tmp = List.length(List(1, 2, 3))
    assert(3.equals(tmp))
  }
  test("Test length function 1") {
    val tmp = List.length(List())
    assert(0.equals(tmp))
  }
}
