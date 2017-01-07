package ml

import ml.util.ActivationFunction
import ml.util.RamdomGenerator

class NeuralNetwork(val func: ActivationFunction) {

  var weights: Array[Array[Double]] = null
  var bias: Array[Double] = null

  private def init(data: Data) {
    if (weights == null) weights = RamdomGenerator.fill2DRandom(data.outputs.size, data.inputs.size)
    if (bias == null) bias = RamdomGenerator.fillRandomMinMax(data.outputs.size)
  }

  def activate(data: Data) {
    init(data)
    for (o <- 0 to data.outputs.size - 1) {
      var stimulus: Double = 0
      for (i <- 0 to data.inputs.size - 1) {
        stimulus += weights(o)(i) * data.inputs(i)
      }
      stimulus += bias(o)
      data.outputs(o) = func.activate(stimulus)
    }
  }

  def train(data: Data, learningRate: Double): Array[Double] = {
    init(data)
    val prediction = data.clone;
    activate(data)
    train(prediction, data, learningRate)
  }

  private def train(prediction: Data, data: Data, learningRate: Double): Array[Double] = {
    val error: Array[Double] = new Array[Double](data.outputs.size)
    for (o <- 0 to data.outputs.size - 1) {
      error(o) = prediction.outputs(o) - data.outputs(o);
      for (i <- 0 to data.inputs.size - 1) {
        weights(o)(i) += -error(o) * data.inputs(i) * learningRate
      }
      bias(o) += -error(o) * learningRate
    }
    error
  }

}
