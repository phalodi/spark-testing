import org.apache.spark.sql.Dataset

case class Person(name:String,age:String)

object CountJson {

  def countName(name: String, dataset: Dataset[Person]) = {
   dataset.filter(dataset("name") === name)
  }

}
