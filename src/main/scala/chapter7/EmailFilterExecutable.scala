package chapter7

case class Email(phase: String)

case class EmailFilter(matches: Email => Boolean) {
  def &&(other: EmailFilter): EmailFilter = {
    EmailFilter(email => matches(email) && other.matches(email))
  }

  def ||(other: EmailFilter): EmailFilter = {
    EmailFilter(email => matches(email) || other.matches(email))
  }

  def unary_! : EmailFilter =
    EmailFilter(email => !matches(email))


}

object EmailFilter {
  def subjectContains(phase: String): EmailFilter = {
    EmailFilter(_.phase.contains(phase))
  }

  def main(args: Array[String]): Unit = {
    val filter: EmailFilter =
      (subjectContains("discount") || subjectContains("clearance")) &&
        !subjectContains("liquidation")
    println(filter.matches(Email("discount")))
    println(filter.matches(Email("liquidation")))

  }
}