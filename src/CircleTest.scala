import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.awt.{Color, Graphics2D}
import java.awt.geom.Path2D

class CircleTest extends AnyFlatSpec with Matchers {
  val g: Graphics2D = mock(classOf[Graphics2D])
  val d: DrawContext = mock(classOf[DrawContext])

  "Circle" should "parse arguments correctly" in {
    val circle = new Circle("(10 20) 30")
    circle.arguments should be(Array(10, 20, 30))
  }

  it should "throw DrawException for invalid argument" in {
    assertThrows[DrawException] {
      new Circle("10 10 10")
    }
  }

  it should "calculate correct x0, y0, and radius" in {
    val circle = new Circle("(10 20) 50")
    circle.x0 should be(100)
    circle.y0 should be(200)
    circle.radius should be(500)
  }

  it should "draw circle in correct colour" in {
    val circle = new Circle("(0 0) 10")
    val g: Graphics2D = mock(classOf[Graphics2D])
    val d: DrawContext = mock(classOf[DrawContext])

    when(d.graphics).thenReturn(g)
    circle.draw(d)

    verify(g, times(2)).setColor(any)
    verify(g).fill(any[Path2D.Float])
    verify(g).draw(any[Path2D.Float])

    val col = new Color(0, 0, 0)
    val fill = new Color(0, 0, 0, 0)
    assert(circle.color == col)
    assert(circle.fill == fill)
  }

  it should "handle negative coordinates" in {
    val circle = new Circle("(-10 -10) 10")
    val g: Graphics2D = mock(classOf[Graphics2D])
    val d: DrawContext = mock(classOf[DrawContext])

    when(d.graphics).thenReturn(g)
    circle.draw(d)

    verify(g, times(2)).setColor(any)
    verify(g).fill(any[Path2D.Float])
    verify(g).draw(any[Path2D.Float])
  }

  it should "handle negative radius" in {
    val circle = new Circle("(10 10) -20")
    val g: Graphics2D = mock(classOf[Graphics2D])
    val d: DrawContext = mock(classOf[DrawContext])

    when(d.graphics).thenReturn(g)
    circle.draw(d)

    verify(g, times(2)).setColor(any)
    verify(g).fill(any[Path2D.Float])
    verify(g).draw(any[Path2D.Float])
  }

  it should "handle no radius" in {
    val circle = new Circle("(10 10) 0")
    val g: Graphics2D = mock(classOf[Graphics2D])
    val d: DrawContext = mock(classOf[DrawContext])

    when(d.graphics).thenReturn(g)
    circle.draw(d)

    verify(g, times(2)).setColor(any)
    verify(g).fill(any[Path2D.Float])
    verify(g).draw(any[Path2D.Float])
  }
}

