package ml

import ml.util.ActivationFunction

class Layer(data:Data, func: ActivationFunction) {

  val neuralNetwork = new NeuralNetwork(func);

  def backward(aboveLayer:Layer, learningRate: Double): Array[Double] = {
    null
  }

}