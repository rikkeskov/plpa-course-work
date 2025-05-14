// #Sireum #Logika
import org.sireum._

@strictpure def compareCoords(a: (Z, Z), b: (Z, Z),x1: Z, x2: Z, y1:Z, y2:Z): B = {
  // x and y increases so b is larger or equal
  x1 < x2 & y1 < y2 & a._1 <= b._1 & a._2 <= b._2 |
  // x increases and y decreases so b._1 increases and b._2 decreases
  x1 < x2 & y1 > y2 & a._1 <= b._1 & a._2 >= b._2 |
  // x decreases and y increases so b._1 decreases and b._2 increases
  x1 > x2 & y1 < y2 & a._1 >= b._1 & a._2 <= b._2 |
  // x y decreases so b decreases
  x1 > x2 & y1 > y2 & a._1 >= b._1 & a._2 >= b._2
}


@strictpure def sortedCoords(a: MSZ[(Z, Z)], x1: Z, x2: Z, y1: Z, y2: Z): B = {
  Contract(
    Requires(a.size > 1)
  )
  All(a.indices)(j => All(0 until j)(i => compareCoords(a(j), a(i), x1, x2, y1, y2)))
}


def abs(z: Z): Z = {
  if (z < 0){
    return -z
  } else {
    return z
  }
}

def line_coords(x1: Z, x2: Z, y1: Z, y2: Z) : MSZ[(Z, Z)] = {
  Contract(
    Requires(),
    Ensures(sortedCoords(Res[MSZ[(Z, Z)]], x1, x2, y1, y2))
  )

  // Variables / values for the Bresenham's Line Algorithm
  var x = x1
  var y = y1
  val dx = abs(x2 - x1)
  val dy = -abs(y2 - y1)
  var error = dx + dy

  var coordinates: MSZ[(Z, Z)] = MSZ[(Z, Z)]()
  // Add the initial point
  coordinates = coordinates :+ (x, y)
  Deduce(|- (coordinates.size == 1))
  Deduce(|- (sortedCoords(coordinates, x1, x2, y1, y2)))
  while (x != x2 & y != y2){
    Invariant(
      Modifies(coordinates, x, y, error),
      sortedCoords(coordinates, x1, x2, y1, y2)
    )
    Deduce(|-(x != x2 & y != y2))
    if((2 * error) >= dy){
      error = error + dy
      val x_pre = abs(x2 - x)
      if(x1 < x2){
        x = x + 1
      } else {
        x = x - 1
      }
      val x_post = abs(x2 - x)
      Deduce(|- (x_pre > x_post))
    }
    if((2 * error) <= dx){
      error = error + dx
      val y_pre = abs(y2 - y)
      if(y1 < y2){
        y = y + 1
      } else {
        y = y - 1
      }
      val y_post = abs(y2 - y)
      Deduce(|- (y_pre > y_post))
    }
    Deduce(|- (coordinates.size >= 1))
    coordinates = coordinates :+ ((x, y))
    Deduce(|- (coordinates.size > 1))
    Deduce(|- (compareCoords(coordinates(coordinates.size -1), (x, y), x1, x2, y1, y2)))
    Deduce(|- (sortedCoords(coordinates, x1, x2, y1, y2)))

  }
  Deduce(|- (x == x2 | y == y2))
  Deduce(|- (sortedCoords(coordinates, x1, x2, y1, y2)))

  return coordinates
}