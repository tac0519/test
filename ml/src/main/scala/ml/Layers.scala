package ml

import ml.util.ActivationFunction

class Layers(val data: Data, func: ActivationFunction) {

  val neuralNetwork = new NeuralNetwork(func)
  neuralNetwork.initRandom(data)
  val error = new Array[Double](data.outputs.size)

  val visibleData = data.inputs
  val hiddenData = data.outputs

  def backward(aboveLayer: Layers, learningRate: Double) {
    for (o <- 0 until data.outputs.size) {
      layerError(o, aboveLayer)
      neuralNetwork.updateParam(o, error, data, learningRate)
    }
  }

  def layerError(o: Int, layers: Layers): Unit = {
    for (p <- 0 until layers.data.outputs.size) {
      error(o) += layers.neuralNetwork.weights(o)(p) * layers.data.inputs(p)
    }
    error(o) *= func.deactivate(data.outputs(o))
  }

  // TODO think about transferring errors to forward
  def forward(belowLayer: Layers, learningRate: Double) {
    neuralNetwork.output(data)
  }



}