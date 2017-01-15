package ml

import ml.util.ActivationFunction
import ml.util.RandomGenerator

class NeuralNetwork(val func: ActivationFunction) {

  var weights: Array[Array[Double]] = null
  var bias: Array[Double] = null

  private def init(data: Data) {
    if (weights == null || bias == null) {
      bias = Seq.fill(data.outputs.size)(0.0).toArray
      weights = Seq.fill(data.outputs.size)(Seq.fill(data.inputs.size)(0.0).toArray).toArray
    }
  }


  def activate(data: Data) {
    init(data)
    if (func.multi) activateNeurons(true, data)
    else activateNeurons(false, data)
  }

  private def activateNeurons(multi: Boolean, data: Data) {
    for (o <- 0 until data.outputs.size) {
      for (i <- 0 until data.inputs.size) {
        data.outputs(o) += weights(o)(i) * data.inputs(i)
      }
      data.outputs(o) += bias(o)
      if (!multi) data.outputs(o) = func.activate(data.outputs(o))
    }
    if (multi) func.activates(data.outputs)

  }

  def train(data: Data, learningRate: Double): Array[Double] = {
    val prediction = data.copyInputs;
    activate(prediction)
    train(prediction, data, learningRate)
  }

  private def train(prediction: Data, data: Data, learningRate: Double): Array[Double] = {
    val error: Array[Double] = new Array[Double](data.outputs.size)
    for (o <- 0 until data.outputs.size) {
      error(o) = prediction.outputs(o) - data.outputs(o);
      for (i <- 0 until data.inputs.size) {
        weights(o)(i) -= error(o) * data.inputs(i) * learningRate
      }
      bias(o) -= error(o) * learningRate
    }
    error
  }

}
