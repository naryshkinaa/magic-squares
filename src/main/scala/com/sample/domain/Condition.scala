package com.sample.domain

case class Condition(
                      dependSquares: Set[Int],
                      condition: List[Square] => Boolean
                    )