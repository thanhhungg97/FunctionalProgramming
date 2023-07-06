package chapter3

import chapter3.Tree.{maximum, size}
import org.scalatest.funsuite.AnyFunSuiteLike

class TreeTest extends AnyFunSuiteLike {
  val leaf1: Tree[Int] = Leaf[Int](5)
  val leaf2: Tree[Int] = Leaf[Int](6)
  val root: Tree[Int] = Branch[Int](leaf1, leaf2)

  test("testDept") {
    assert(2 == root.dept)

  }

  test("testSize") {
    assert(3 == size(root))

  }

  test("testMaximum") {
    assert(6 == maximum(root))

  }

  test("testMap") {
    val leaf1: Tree[Int] = Leaf[Int](10)
    val leaf2: Tree[Int] = Leaf[Int](12)
    val root1: Tree[Int] = Branch[Int](leaf1, leaf2)
    assert(root1 == Tree.map(root)(a => a * 2))


  }

}
