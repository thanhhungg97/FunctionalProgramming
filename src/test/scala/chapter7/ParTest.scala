package chapter7

import org.scalatest.funsuite.AnyFunSuiteLike

import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

class ParTest extends AnyFunSuiteLike {
  implicit val executorContext =  new ThreadPoolExecutor(2, 10, 5, TimeUnit.MINUTES , new LinkedBlockingQueue )

  test("testSortPar") {
    val graph = Par.sortPar(Par.unit(List(1, 3, 4, 2, 3, 6)))
    val result = Par.run(executorContext)(graph)
    assert(List(1, 2, 3, 3, 4, 6) == result.get)

  }

  test("testMap") {

  }

}
