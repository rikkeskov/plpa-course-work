import Circle.draw

import org.scalatest.funsuite.AnyFunSuite

class CircleTest extends AnyFunSuite:
  val graphics = mock(classOf[Graphics])
  test("Circle.sc") {
    assert(Circle.draw())
  }