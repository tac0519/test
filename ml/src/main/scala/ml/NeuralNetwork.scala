package ml

import ml.util.ActivationFunction
import ml.util.RandomGenerator

class NeuralNetwork(val func: ActivationFunction) {

  var weights: Array[Array[Double]] = null
  var bias: Array[Double] = null

  private def init(data: Data) {
    if (weights == null) weights = RandomGenerator.fill2DRandom(data.outputs.size, data.inputs.size)
    if (bias == null) bias = RandomGenerator.fillRandomMinMax(data.outputs.size)
  }


  def activate(data: Data) {
    if(func.multi) activateNeurons(true, data)
    else activateNeurons(false, data)
  }
  private def activateNeurons(multi: Boolean, data: Data) {
    init(data)
    for (o <- 0 to data.outputs.size - 1) {
      for (i <- 0 to data.inputs.size - 1) {
        data.outputs(o) += weights(o)(i) * data.inputs(i)
      }
      data.outputs(o) += bias(o)
      if (!multi) data.outputs(o) = func.activate(data.outputs(o))
    }
    if (multi) func.activates(data.outputs)

  }

  def train(data: Data, learningRate: Double): Array[Double] = {
    init(data)
    val prediction = data.clone;
    activate(prediction)
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
