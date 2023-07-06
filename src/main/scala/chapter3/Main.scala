package chapter3

import chapter3.List

object Main {
  def main(args: Array[String]): Unit = {
    val list= List[Int](1, 2, 3, 4, 5)
    println(List.drop(list, 3))
    println(list.tail)
    val newL  = List.dropWhile(List[Int](1, 2, 3, 4, 5))(x => x < 4)
    println(newL)
  }
}