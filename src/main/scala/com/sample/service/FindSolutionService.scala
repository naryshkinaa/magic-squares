package com.sample.service

import com.sample.domain.{Board, Square}

object FindSolutionService {

  def findAllSolutions(board: Board): List[Board] = {
    subSolution(Nil, board.squares)
  }

  private def subSolution(fixed: List[Square], free: List[Square]): List[Board] = {
    if (free.isEmpty) List(Board(fixed))
    else {
      free
        .filter(s => BoardCheckService.subCheckForNewSquare(fixed :+ s))
        .flatMap(s => subSolution(fixed :+ s, free.filter(_ != s)))
    }
  }

}
