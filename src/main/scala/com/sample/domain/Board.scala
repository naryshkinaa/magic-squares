package com.sample.domain

case class Board(
                  squares: List[Square]
                ) {
  def toPrint(): String = {
    squares.map(
      s => s"${s.lt} ${s.rt} ${s.lb} ${s.rb}\n"
    ).mkString("")
  }

  def beautyPrint(): Unit = {
    //not optimized, only for test print
    val digits = squares.flatMap(s => List(s.lt.toString, s.rt.toString, s.lb.toString, s.rb.toString))
//    val tmp = digits.slice(40, 48)
    val fullDigits = List(" ", " ", " ", " ") ++
      digits.take(8) ++
      List(" ", " ", " ", " ") ++
      digits.slice(8, 40) ++
      List(" ", " ", " ", " ") ++
      digits.slice(40, 48) ++
      List(" ", " ", " ", " ")
    fullDigits
      .grouped(16)
      .foreach{
        group =>
          println(s"${group(0)}  ${group(1)}${group(4)}  ${group(5)}${group(8)}  ${group(9)}${group(12)}  ${group(13)}")
          println()
          println()
          println(s"${group(2)}  ${group(3)}${group(6)}  ${group(7)}${group(10)}  ${group(11)}${group(14)}  ${group(15)}")
      }
  }
}

object Board {
  def createFromLines(lines: List[String]): Board = {
    val squares = lines.map(
      l => {
        val digits = l.split(" +")
        if (digits.size != 4) throw new RuntimeException("Invalid number of digits")
        Square(digits(0).toInt, digits(1).toInt, digits(2).toInt, digits(3).toInt)
      }
    )
    if (squares.size != 12) throw new RuntimeException("Invalid number of squares")
    Board(squares)
  }
}
