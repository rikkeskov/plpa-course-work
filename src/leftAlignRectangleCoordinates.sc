// #Sireum #Logika
import org.sireum._

def leftAlignRectangleCoordinates(arguments: ISZ[Z]): ISZ[Z] = {
  Contract(
  Requires(
    arguments.size == 4,
  ),
  Ensures(
    (Res[ISZ[Z]](0) <= Res[ISZ[Z]](2)) & (Res[ISZ[Z]](1) <= Res[ISZ[Z]](3))
  )
  )
  var lowX: Z = arguments(0)
  var lowY: Z = arguments(1)
  var highX: Z = arguments(2)
  var highY: Z = arguments(3)
  if(lowX > highX){
    Deduce(|-(lowX > highX))
    lowX = lowX + highX
    highX = lowX - highX
    lowX = lowX - highX
    Deduce(|-(lowX < highX))
  }
  Deduce(|-(lowX <= highX))
  if(lowY > highY){
    Deduce(|-(lowY > highY))
    lowY = lowY + highY
    highY = lowY - highY
    lowY = lowY - highY
    Deduce(|-(lowY < highY))
  }
  Deduce(|-(lowY <= highY))
  Deduce(|-(lowX <= highX & lowY <= highY))
  return ISZ[Z](lowX, lowY, highX, highY)
}

