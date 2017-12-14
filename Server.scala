
import com.scala.examples.datagurn.lesson07.Teacher
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import scala.concurrent.impl.Future
import java.util.concurrent.FutureTask
import scala.concurrent.impl.Future
import com.scala.examples.datagurn.lesson07.Teacher

object Server {
  
  // ????????????
  val executorService: ExecutorService = Executors.newFixedThreadPool(10);
  
  val teachers: List[Teacher] = List(
    new Teacher("t1", 20),
    new Teacher("t2", 30),
    new Teacher("t1", 25),
    new Teacher("t1", 21)
  );
  
  def concurrentFilterByName(name: String): FutureTask[List[Teacher]] = {
    val futures = new FutureTask[List[Teacher]](new Callable[List[Teacher]]() {
      def call(): List[Teacher] = {
        return filterByName(name);
      }
    })
    executorService.execute(futures);
    return futures;  // ?? Future
  }
  
  def filterByName(name: String) : List[Teacher] = {
    teachers.synchronized {
      for {
        item <- teachers
        if item.name == name
      } yield item
    }
  }
  
  def main(args: Array[String]): Unit = {
    val teachers : List[Teacher] = concurrentFilterByName("t1").get
    println(teachers)
    executorService.shutdown();
  }
  
  
} 
