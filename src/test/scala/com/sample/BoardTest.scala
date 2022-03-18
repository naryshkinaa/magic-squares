package com.sample

import com.sample.domain.Board
import com.sample.service.FindSolutionService
import org.scalatest.funspec.AnyFunSpec

import scala.io.Source

class BoardTest extends AnyFunSpec {

  test("Board1.txt")
  test("Board2.txt")
  test("Board3.txt")
  test("Board4.txt")

  def test(fileName: String): Unit = {
    it(s"Test $fileName") {
      val resource = Source.fromResource(fileName)
      val lines: Iterator[String] = resource.getLines
      val board = Board.createFromLines(lines.toList)
      val solutions = FindSolutionService.findAllSolutions(board)
      solutions.zipWithIndex.foreach{s =>
        println(s"Solution ${s._2 + 1}")
        print(s._1.toPrint())
        println()
      }
    }
  }

}
