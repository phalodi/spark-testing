import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}

trait SparkSpec extends BeforeAndAfterAll {
  this: Suite =>

  private var _sc: SparkContext = _

  override def beforeAll(): Unit = {
   super.beforeAll()
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName(this.getClass.getSimpleName).set("spark.streaming.clock", "org.apache.spark.streaming.util.ManualClock")

    _sc = new SparkContext(conf)
  }


  override def afterAll(): Unit = {
    if (_sc != null) {
      _sc.stop()
      _sc = null
    }
    super.afterAll()
  }

  def sc: SparkContext = _sc

}