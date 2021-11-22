package algorithms;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.sql.Timestamp;


public class LLNode{

		public String file_name;
		public Timestamp timestamp;
		public int id;
		private static final AtomicInteger count = new AtomicInteger(0);

		LLNode(String file_name, Timestamp timestamp){
			//Storing the URL(file_name), current timestamp and auto-incremental id.
			this.file_name= file_name;
			this.timestamp = timestamp;
			this.id = count.incrementAndGet();
		}
		public  String getFilename() {
			return this.file_name;
		}
		public Timestamp getTimestamp() {
			return this.timestamp;

		}

}
