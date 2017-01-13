package ml.util

import ml.Data

object ActivationFunction {

  case object sigmoid extends ActivationFunction("sigmoid", false)

  case object tanh extends ActivationFunction("tanh", false)

  case object ReLU extends ActivationFunction("ReLU", false)

  case object softmax extends ActivationFunction("softmax", true)


}

sealed abstract class ActivationFunction(val name: String, val multi: Boolean) {
  val activate = (output: Double) => func(output, true)
  val deactivate = (output: Double) => func(output, false)
  val activates = (outputs: Array[Double]) => funcMulti(outputs)

  private def func(x: Double, isActivate: Boolean): Double = {
    (name) match {
      case (ActivationFunction.sigmoid.name) => if (isActivate) 1 / (1 + Math.pow(Math.E, -x)) else x * (1 - x);
      case (ActivationFunction.tanh.name) => if (isActivate) Math.tanh(x) else 1 - x * x
      case (ActivationFunction.ReLU.name) => if (isActivate) {
        if (x > 0) x else 0
      } else {
        if (x > 0) 1 else 0
      }
    }
  }


  private def funcMulti(outputs: Array[Double]): Unit = {
    (name) match {
      case (ActivationFunction.softmax.name) => softmax(outputs)
    }
  }

  private def softmax(outputs: Array[Double]): Unit = {
    val max = outputs.max
    for (o <- 0 until outputs.size) outputs(o) = Math.exp(outputs(o) - max)
    val sum = outputs.sum
    for (o <- 0 until outputs.size) outputs(o) = outputs(o) / sum
  }
}