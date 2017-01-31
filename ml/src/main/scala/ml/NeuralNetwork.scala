package ml

import ml.util.ActivationFunction

class NeuralNetwork(val func: ActivationFunction) {

  var weights: Array[Array[Double]] = null
  var bias: Array[Double] = null

  private def init(data: Data) {
    if (weights == null || bias == null) {
      bias = Seq.fill(data.outputs.size)(0.0).toArray
      weights = Seq.fill(data.outputs.size)(Seq.fill(data.inputs.size)(0.0).toArray).toArray
    }
  }


  def output(data: Data) {
    init(data)
    if (func.multi) outputs(true, data)
    else outputs(false, data)
  }

  private def outputs(multi: Boolean, data: Data) {
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
    output(prediction)
    train(prediction, data, learningRate)
  }

  private def train(prediction: Data, data: Data, learningRate: Double): Array[Double] = {
    val error: Array[Double] = new Array[Double](data.outputs.size)
    for (o <- 0 until data.outputs.size) {
      error(o) = prediction.outputs(o) - data.outputs(o);
      updateParam(o, error, data, learningRate)
    }
    error
  }

  def updateParam(o:Int, error:Array[Double], data: Data, learningRate: Double): Unit ={
    for (i <- 0 until data.inputs.size) {
      weights(o)(i) -= error(o) * data.inputs(i) * learningRate
    }
    bias(o) -= error(o) * learningRate
  }

}
