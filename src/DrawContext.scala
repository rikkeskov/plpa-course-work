import java.awt.{Graphics2D, Shape, Color}

class DrawContext(var graphics: Graphics2D = null) {
  var objects: Array[(DrawObject[_], Int)] = Array()
  var latestBoundingBox: Option[Shape] = None
  private var errors: Array[(Int, String)] = Array()
  private var currentColor: Color = Color.BLACK

  def addError(error: String, lineNumber: Int, obj: DrawObject[_]): Unit = {
    errors = errors :+ (lineNumber, error)
  }

  def formatErrors(): String = {
    errors.foldLeft("")((acc, err) => acc ++ s"${err._1}: ${err._2}\n")
  }

  def resetContext(): Unit = {
    errors = Array()
    objects = Array()
    latestBoundingBox = None
    currentColor = Color.BLACK
  }
}
