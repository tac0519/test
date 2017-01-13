package ml

object Data {

  def copy(dataSet: Array[Data]): Array[Data] = {
    val copySet = new Array[Data](dataSet.size)
    dataSet.foreach(data => copySet(dataSet.indexOf(data)) = data.clone)
    copySet
  }

}

final class Data(val inputs: Array[Double], val outputs: Array[Double]) extends java.io.Serializable with java.lang.Cloneable {

  def this(data: Data) = {
    this(data.inputs.clone(), data.outputs.clone())
  }

  override def clone(): Data = {
    new Data(inputs.clone(), outputs.clone())
  }

}