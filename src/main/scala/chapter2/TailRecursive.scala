package chapter2

import scala.annotation.tailrec

object Factorial{
  def factorialTailCall(n: Int): Int = {
    @tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)

    go(n, 1)
  }

  def factorail(n: Int): Int = {
    if (n == 1) 1
    else n * factorail(n - 1)
  }

}
