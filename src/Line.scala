import java.awt.Polygon

class Line(args: String) extends DrawObject[_] {

  override val arguments: _ = ???

  override def draw(context: DrawContext): Unit = {
    // Should use Bresenham's line algorithm
    var p: Polygon = new Polygon()
    // p.addPoint(x, y)
    context.graphics.drawPolygon(p)
    context.latestBoundingBox match {
      case Some(bb) => context.graphics.setClip(bb)
      case None =>  context.addError(this, )
    }

  }
}
