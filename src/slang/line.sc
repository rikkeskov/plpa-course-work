// #Sireum #Logika

package chart.line

import org.sireum._

object Line{

    def line(x1: Z, x2: Z, y1: Z, y2: Z){
        Contract(
            Modifies(objects),
            Ensures(Res == line_spec(x1, x2, y1, y2))
        )

    }

    @pure def line_spec(x1: Z, x2: Z, y1: Z, y2: Z){

    }
}