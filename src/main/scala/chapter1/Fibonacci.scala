package chapter1

object Fibonacci {
  def fib(n: Int): Int = {
    def fib(n: Int, a: Int, b: Int = 1): Int = {
      if (n == 0) {
        return a
      }
      if (n == 1) {
        return b
      }
      fib(n - 1, b, b + a)
    }
    fib(n, 0)
  }

  def fibSimple(n: Int): Int = {
    if (n == 0) {
      return 0
    }
    if (n == 1 || n == 2) {
      return 1
    }
    fibSimple(n - 1) + fibSimple(n - 2)
  }

}
