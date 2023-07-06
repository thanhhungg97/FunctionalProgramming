package chapter3

sealed trait Tree[+A] {
  def size: Int

  def dept: Int
}

case class Leaf[A](value: A) extends Tree[A] {
  override def size: Int = 1

  override def dept: Int = 1
}

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def size: Int = Tree.size(this)

  override def dept: Int = Tree.dept(this)

}


object Tree {
  def maximum(tree: Tree[Int]): Int = {
    tree match {
      case Leaf(value: Int) => value
      case Branch(left: Tree[Int], right: Tree[Int]) => Math.max(maximum(left), maximum(right))
    }
  }

  def dept[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 1
      case Branch(left: Tree[Int], right: Tree[Int]) => Math.max(dept(left), dept(right)) + 1
    }
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
    tree match {
      case Leaf(x) => Leaf(f(x))
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
    }

  }

  def size[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 1
      case Branch(left, right) => size(left) + size(right) + 1
    }
  }

}

