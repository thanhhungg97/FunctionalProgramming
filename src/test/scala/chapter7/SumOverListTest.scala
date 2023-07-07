package chapter7

import org.scalatest.funsuite.AnyFunSuiteLike

class SumOverListTest extends AnyFunSuiteLike {
  test("test divide and conquer 1") {
    val a = SumOverList.sumDivideAndConque(IndexedSeq(1, 2, 3, 4))

    assert(10 == a)
  }
  test("test divide and conquer 2") {
    val a = SumOverList.sumDivideAndConque(IndexedSeq())

    assert(0 == a)
  }
  test("test divide and conquer 3") {
    val a = SumOverList.sum(IndexedSeq(1))

    assert(1 == a)
  }
  test("test divide and conquer 4") {
    val a = SumOverList.sumDivideAndConque(IndexedSeq(1, 2))

    assert(3 == a)
  }
}
