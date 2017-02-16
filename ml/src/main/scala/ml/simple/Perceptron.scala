package ml.simple

import ml.util.ActivationFunction

class Perceptron(val func: ActivationFunction) {

  var weight: Array[Double] = null

  def prop(values:Array[Double], bias:Double): Double = {
    // TODO init
    var num:Double = 0
    for (v <- 0 until values.size) num += weight(v) * values(v)
    num += bias
    func.activate(num)
  }
  
}