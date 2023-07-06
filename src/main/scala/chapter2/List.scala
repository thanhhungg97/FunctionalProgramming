package chapter2

import scala.annotation.tailrec


sealed trait List[+A] {
  def tail: List[A]

  def map[B >: A](f: A => B): List[B]
}

case object Nil extends List[Nothing] {
  def tail: List[Nothing] = throw new IllegalStateException()

  override def map[B >: Nothing](f: Nothing => B): List[B] = Nil
}

case class Cons[+A](head: A, tails: List[A]) extends List[A] {
  def tail: List[A] = tails

  override def map[B >: A](f: A => B): List[B] = {
    Cons(f(head), tails.map(f))
  }
}

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(head, tail) => head + sum(tail)
  }

  @tailrec
  def drop[A](l: List[A], n: Int): List[A] = {
    l match {
      case Nil => Nil
      case Cons(_, tail) => if (n == 1) tail else drop(tail, n - 1)
    }

  }

  def length[A](as: List[A]): Int = {
    foldRight(as, 0)((_, b) => b + 1)
  }

  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Cons(head, tails) => foldLeft(tails, f(z, head))(f)
      case Nil => z
    }
  }

  @tailrec
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Cons(head, tails) => foldRight(tails, f(head, z))(f)
      case Nil => z
    }
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = {
    l match {
      case Cons(head, tails) if f(head) => dropWhile(tails)(f)
      case _ => l
    }
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}