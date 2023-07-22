package chapter7


sealed trait EmailFilterDeclarative {
  self =>
  def &&(that: EmailFilterDeclarative): EmailFilterDeclarative = And(self, that)

  def ||(that: EmailFilterDeclarative): EmailFilterDeclarative = Or(self, that)

  def unary_! : EmailFilterDeclarative = Not(self)
}

final case class SubjectContains(phrase: String) extends EmailFilterDeclarative

final case class And(left: EmailFilterDeclarative, right: EmailFilterDeclarative) extends EmailFilterDeclarative

final case class Or(left: EmailFilterDeclarative, right: EmailFilterDeclarative) extends EmailFilterDeclarative

final case class Not(value: EmailFilterDeclarative) extends EmailFilterDeclarative

object EmailFilterDeclarative {
  def matches(filter: EmailFilterDeclarative, email: Email): Boolean =
    filter match {
      case And(l, r) => matches(l, email) && matches(r, email)
      case Or(l, r) => matches(l, email) || matches(r, email)
      case Not(v) => !matches(v, email)
      case SubjectContains(phrase) => email.phase.contains(phrase)
    }


  def subjectContains(phrase: String): EmailFilterDeclarative = {
    SubjectContains(phrase)

  }

  def describe(filter: EmailFilterDeclarative): String =
    filter match {
      case And(l, r) => s"(${describe(l)} && ${describe(r)})"
      case Or(l, r) => s"(${describe(l)} || ${describe(r)})"
      case Not(v) => s"!${describe(v)}"
      case SubjectContains(phrase) => s"(subject contains ${phrase})"
    }

  def main(args: Array[String]): Unit = {
    val filter = And(
      SubjectContains("discount") || SubjectContains("clearance"),
      Not(SubjectContains("liquidation"))
    )
    println(describe(filter))
  }

}