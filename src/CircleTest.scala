import org.mockito.Mockito._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.awt.Graphics2D

class CircleTest extends AnyFlatSpec with Matchers {
  val g: Graphics2D = mock(classOf[Graphics2D])
  "Circle.draw" should "draw circle correctly" in {
    val circle = new Circle("(0 0) 10")
    circle.draw(new DrawContext(g))
  }
}
