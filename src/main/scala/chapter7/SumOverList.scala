package chapter7

object SumOverList {
  def sum(ints: Seq[Int]) = {
    ints.foldLeft(0)((a, b) => a + b)
  }

  def sumDivideAndConque(ints: Seq[Int]) = {
    def sum(start: Int, end: Int): Int = {
      if (start > end) {
        return 0
      }
      val mid = start + (end - start) / 2
      ints(mid) + sum(start, mid - 1) + sum(mid + 1, end)
    }

    sum(0, ints.length - 1)
  }

  def sum(ints: IndexedSeq[Int]): Int = {
    if (ints.size <= 1) {
      ints.headOption getOrElse 0
    }
    else {
      val (l, r) = ints.splitAt(ints.size / 2)
      sum(l) + sum(r)
    }
  }

}
