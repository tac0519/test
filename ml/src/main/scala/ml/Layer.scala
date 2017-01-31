package ml

import ml.util.ActivationFunction

class Layer(val data: Data, func: ActivationFunction) {

  val neuralNetwork = new NeuralNetwork(func)
  neuralNetwork.initRandom(data)
  val error = new Array[Double](data.outputs.size)


  def backward(aboveLayer: Layer, learningRate: Double) {
    for (o <- 0 until data.outputs.size) {
      layerError(o, aboveLayer)
      neuralNetwork.updateParam(o, error, data, learningRate)
    }
  }

  def layerError(o: Int, layer: Layer): Unit = {
    for (p <- 0 until layer.data.outputs.size) {
      error(o) += layer.neuralNetwork.weights(o)(p) * layer.data.inputs(p)
    }
    error(o) *= func.deactivate(data.outputs(o))
  }

  // TODO think about transferring errors to forward
  def forward(belowLayer: Layer, learningRate: Double) {
    neuralNetwork.output(data)
  }
}