package com.sample

import com.sample.domain.Board
import com.sample.service.FindSolutionService

object Boot extends App {
  val lines = (1 to 12)
    .map(
      _ => scala.io.StdIn.readLine()
    )
  val board = Board.createFromLines(lines.toList)
  val solutions = FindSolutionService.findAllSolutions(board)
  print(solutions.map(_.toPrint()).mkString("\n"))

}