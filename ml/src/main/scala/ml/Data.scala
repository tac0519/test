package ml

final class Data(val inputs: Array[Double], val outputs: Array[Double]) extends java.io.Serializable with java.lang.Cloneable {
  override def clone(): Data = {
    new Data(inputs.clone(), outputs.clone())
  }

  def set(data: Data) {
    inputs.foreach(input => data.inputs(inputs.indexOf(input)))
    outputs.foreach(output => data.outputs(outputs.indexOf(output)))
  }

}