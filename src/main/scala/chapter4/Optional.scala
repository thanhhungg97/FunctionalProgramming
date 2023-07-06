package chapter4

trait Optional[+A] {

  def map[B](f: A => B): Optional[B]

  def flatMap[B](f: A => Optional[B]): Optional[B]

  def getOrElse[B >: A](default: => B): B

  def orElse[B >: A](ob: => Optional[B]): Optional[B]

  def filter(f: A => Boolean): Optional[A]
}

object Optional {
  def apply[A](a: A): Optional[A] = Some(a)

  def none(): Optional[Nothing] = None

  def map2[A, B, C](a: Optional[A], b: Optional[B])(f: (A, B) => C): Optional[C] = (a, b) match {
    case (Some(x), Some(y)) => Some(f(x, y))
    case (_, _) => None
  }
//
//  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
//
//  }

}

case class Some[+A](get: A) extends Optional[A] {
  override def map[B](f: A => B): Optional[B] = Some(f(get))

  override def flatMap[B](f: A => Optional[B]): Optional[B] = f(get) match {
    case Some(t) => Some(t)
    case None => None
  }

  override def getOrElse[B >: A](default: => B): B = get

  override def orElse[B >: A](ob: => Optional[B]): Optional[B] = this

  override def filter(f: A => Boolean): Optional[A] = if (f(get)) Some(get) else None
}

case object None extends Optional[Nothing] {
  override def map[B](f: Nothing => B): Optional[B] = None

  override def flatMap[B](f: Nothing => Optional[B]): Optional[B] = None

  override def getOrElse[B >: Nothing](default: => B): B = default

  override def orElse[B >: Nothing](ob: => Optional[B]): Optional[B] = ob

  override def filter(f: Nothing => Boolean): Optional[Nothing] = this
}