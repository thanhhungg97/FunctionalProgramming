package chapter7

import chapter7.Par.{Par, fork}

import java.util.concurrent._


object Par {
  type Par[A] = ExecutorService => Future[A]

  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true

    def get(timeout: Long, units: TimeUnit) = get

    def isCancelled = false

    def cancel(evenIfRunning: Boolean): Boolean = false
  }

  def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  def asyncF[A, B](f: A => B): A => Par[B] = a => {
    lazyUnit(f(a))
  }

  def map[A, B](pa: Par[A])(f: A => B): Par[B] = {
    map2(pa, unit(()))((a, _) => f(a))
  }

  def sortPar(parList: Par[List[Int]]) = map(parList)(_.sorted)

  def run[A](e: ExecutorService)(par: Par[A]): Future[A] = {
    par(e)
  }


  def sequence[A](ps: List[Par[A]]): Par[List[A]] = {
    ps.foldRight(Par.unit(List.empty[A]))((a: Par[A], acc) => {
      Par.map2(a, acc)(_ :: _)
    })
  }

  def fork[A](a: => Par[A]): Par[A] = (ec: ExecutorService) => {
    ec.submit(() => a(ec).get())
  }

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def map2[A, B, C](par1: Par[A], par2: Par[B])(f: (A, B) => C): Par[C] = (ec: ExecutorService) => {
    val af = par1(ec)
    val bf = par2(ec)
    UnitFuture(f(af.get, bf.get))

  }

}


object SumWithPar {


  def sumWithPar(ints: IndexedSeq[Int]): Par[Int] = fork {
    if (ints.size <= 1) {
      Par.unit(ints.headOption getOrElse 0)
    }
    else {
      val (l, r) = ints.splitAt(ints.size / 2)
      Par.map2(Par.fork(sumWithPar(l)), Par.fork(sumWithPar(r)))(_ + _)
    }
  }
}

