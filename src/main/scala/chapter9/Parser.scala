package chapter9

trait Parsers[ParseError, Parser[+_]] {
  def char(a: Char): Parser[Char]

  def run[A](parser: Parser[A])(a: String): Either[ParseError, A]
}

object Parser {


}
