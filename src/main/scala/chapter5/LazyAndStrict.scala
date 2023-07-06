package chapter5

object LazyAndStrict {
  def if2[A](cond: Boolean, onTrue: () => A, onFalse: () => A): A =
    if (cond) onTrue() else onFalse() // lazy compute

  def if3[A](cond: Boolean, onTrue: => A, onFalse: => A): A = {
    if (cond) onTrue else onFalse // pass by name
  }

  def maybeTwice(b: Boolean, i: => Int) = if (b) i + i else 0 // evaluate at the time it reference, don't cache the value

  def maybeTwiceWithLazy(b: Boolean, i: => Int) = {
    lazy val j = i
    if (b) j + j else 0
  } // delay evaluation of the right hand side of lazy val until it referenced, and cache the result

  def main(args: Array[String]): Unit = {

    if2(24 < 22,
      () => println("a"),
      () => println("b")
    )
    val x = maybeTwice(true, {
      println("hi");
      1 + 41
    })
    println(x)
    println(maybeTwiceWithLazy(true, {
      println("hi");
      1 + 41
    }))
  }

  2

}
