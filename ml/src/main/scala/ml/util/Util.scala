package ml.util

import ml.Data

/**
  * Created by tac on 17/01/31.
  */
object Util {

  val randomGenerator = new RandomGenerator()

  def outputBinomial(data: Data): Array[Int] = {
    data.outputs.map(output => randomGenerator.binomial(1, output))
    data.outputs.map(output => output.toInt)
  }
}
