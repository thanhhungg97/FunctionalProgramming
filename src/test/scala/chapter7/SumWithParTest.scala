package chapter7

import chapter7.Par.Par
import org.scalatest.funsuite.AnyFunSuiteLike

import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

class SumWithParTest extends AnyFunSuiteLike {
  implicit val executorContext = new ThreadPoolExecutor(2, 10, 5, TimeUnit.MINUTES, new LinkedBlockingQueue)
  test("testSumWithPar") {
    val executionDescription: Par[Int] = SumWithPar.sumWithPar(IndexedSeq(1, 2, 3, 4))
    val result = Par.run(executorContext)(executionDescription)
    assert(10 == result.get)

  }

}
