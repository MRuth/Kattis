/**
  * Created by Montana Ruth on 11/8/2015.
  */

object HiddenPassword extends App {
  val Array(p, m) = io.StdIn.readLine().split(" ")
  println(if((m intersect p) == p) "PASS" else "FAIL")
}