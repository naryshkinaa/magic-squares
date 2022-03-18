package com.sample.service

import com.sample.domain.{Board, Condition, Square}

object BoardCheckService {
  /*
   check only new conditions with last added square
   */
  def subCheckForNewSquare(fixed: List[Square]): Boolean = {
    newConditionsBySquareNumber(fixed.size - 1)
      .forall(_.condition(fixed))
  }


  private val yellowConditions: List[Condition] = {
    List(
      Condition(Set(0, 1), squares => squares(0).rt + squares(1).lt <= 10),
      Condition(Set(0, 2, 3), squares => squares(0).lb + squares(2).rt + squares(3).lt <= 10),
      Condition(Set(1, 4, 5), squares => squares(1).rb + squares(4).rt + squares(5).lt <= 10),
      Condition(Set(2, 6), squares => squares(2).lb + squares(6).lt <= 10),
      Condition(Set(5, 9), squares => squares(5).rb + squares(9).rt <= 10),
      Condition(Set(6, 7, 10), squares => squares(6).rb + squares(7).lb + squares(10).lt <= 10),
      Condition(Set(8, 9, 11), squares => squares(8).rb + squares(9).lb + squares(11).rt <= 10),
      Condition(Set(10, 11), squares => squares(10).rb + squares(11).lb <= 10)
    )
  }

  private val greenConditions: List[Condition] = {
    List(
      List(0, 1, 3, 4),
      List(2, 3, 6, 7),
      List(3, 4, 7, 8),
      List(4, 5, 8, 9),
      List(7, 8, 10, 11)
    ).map {
      case List(s1, s2, s3, s4) => Condition(
        Set(s1, s2, s3, s4),
        squares => squares(s1).rb + squares(s2).lb + squares(s3).rt + squares(s4).lt == 10
      )
    }
  }

  private val allConditions = yellowConditions ++ greenConditions
  /*
      main idea, check only new conditions with new added square
   */
  val newConditionsBySquareNumber: Map[Int, List[Condition]] = {
    (0 to 11).map(
      i =>
        i -> allConditions.filter(c => c.dependSquares.contains(i) && c.dependSquares.forall(_ <= i))
    ).toMap
  }

}
