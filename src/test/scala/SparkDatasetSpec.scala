import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, Suite}

trait SparkDatasetSpec extends BeforeAndAfterAll{
  this: Suite =>

  private var _spark: SparkSession = _

  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName(this.getClass.getSimpleName)


  def sparkSession: SparkSession = _spark

  override def beforeAll(): Unit = {
    super.beforeAll()

    _spark =  SparkSession.builder().config(conf).getOrCreate()

  }

  override def afterAll(): Unit = {
    _spark.stop()
    _spark = null

    super.afterAll()
  }

}