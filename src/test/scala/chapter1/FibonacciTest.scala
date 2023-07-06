package chapter1

import org.scalatest.funsuite.AnyFunSuiteLike

class FibonacciTest extends AnyFunSuiteLike {
  test("test simple fibonacci") {
    val x = Fibonacci.fib(5)
    assert(5 == x)
  }
  test("test simple fibonacci 1") {
    val x = Fibonacci.fib(6)
    assert(8 == x)
  }

  test("test simple fibonacci 2") {
    val x = Fibonacci.fib(0)
    assert(0 == x)
  }

}
