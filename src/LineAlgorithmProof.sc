// #Sireum #Logika
import org.sireum._

@abs def sorted(a: MSZ[Z]): B = All(a.indices)(j => All(0 until j)(i => a(i) <= a(j)))

def line_algorithm(x1: Z, y1: Z, x2: Z, y2: Z): MSZ[Z] = {
  // we only prove that sequence of x in array m increases from x1 to x2
  // and do not deal with other cases or y
  Contract(
    Requires(
      x2 > x1,
      x2 > 0,
      x1 >= 0), // line algorithm draws in all directions, we only check for x moving positive
    //Ensures(sorted(Res[MSZ[Z]]))
  )
  var x = x1
  // var y = y1
  val dx = abs(x2 - x1)
  val dy = -abs(y2 -y1)
  // val sy = conditional_var(y1, y2)
  var error = dx //+ dy
  var mx: MSZ[Z] = MSZ() // array of x values

  while ((x != x2)) { //  & (y != y2)
    Invariant(
      Modifies(mx, x, error), // , y
      x >= x1,
      x <= x2,
      //sorted(mx)
    )
    //Deduce(|- (sorted(mx)))
    Deduce(|- (x >= x1))
    Deduce(|- (x <= x2))
    val x_pre = x // to check for termination
    val mx_size_pre = mx.size
    mx = mx :+ x // replacement for draw line
    Deduce(|- (mx.size > mx_size_pre))
    Deduce(|- (!mx.isEmpty))
    //Deduce(|- (sorted(mx)))
    val error2 = 2 * error
    if (error2 >= dy) {
      Deduce(|- (x != x2))
      error = error + dy
      x = x + 1 // because we require x2 > x1
      Deduce(|- (x > x_pre))
      Deduce(|- (x >= x1))
      Deduce(|- (x <= x2))
    } else {
      Deduce(|- (x == x_pre))
      Deduce(|- (error2 < dy))
    }
    Deduce(|- (x >= x_pre))
//    if (error2 <= dx ) {
//      Deduce(|- (y != y2))
//      error = error + dx
//      y = y + sy
//      Deduce(|- (x >= x_pre))
//    } else {
//      Deduce(|- (error2 > dx))
//      Deduce(|- (x >= x_pre))
//    }
    val x_post = x
    Deduce(|- (x_post >= x_pre)) // check for termination
    Deduce(|- (x_post <= x2)) // check for termination
    //Deduce(|- (All(1 until mx.size)(i => mx(i - 1) <= mx(i))))
    Deduce(|- (mx.size >= 0))
    Deduce(|- (x >= x1))
    Deduce(|- (x <= x2))
  }
  Deduce(|- (x== x2)) // check for termination || y == y2
  //Deduce(|- (sorted(mx)))
  return mx
}

def conditional_var(a: Z, b: Z): Z = {
  Contract(
    Ensures(Res == 1 || Res == -1)
  )
  var res: Z = 0
  if (a < b) {
    Deduce( |- (a < b))
    res = 1
    Deduce( |- (res == 1))
  } else {
    Deduce( |- (a >= b))
    res = -1
    Deduce( |- (res == -1))
  }
  Deduce( |- (res == 1 || res == -1))
  return res
}

@pure def abs(a: Z): Z = {
  Contract(
    Ensures(Res[Z] >= 0)
  )
  var res: Z = 0
  if ( a < 0 ) {
    Deduce(|-(a < 0))
    res = -a
    Deduce( |- (res > 0))
  } else {
    Deduce(|-(a >= 0))
    res = a
    Deduce( |- (res >= 0))
  }
  Deduce( |- (res >= 0))
  return res
}