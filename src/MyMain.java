
import java.util.Arrays;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MyMain {

	public static void main(String[] args) {
		MongoCredential mongoCredential = MongoCredential.createCredential
				("admin", "admin","admin123".toCharArray());
		ServerAddress serverAddress = new ServerAddress("localhost");
		
		try(MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));){
			MongoDatabase db = mongoClient.getDatabase("admin");
			System.out.println("List of collections:");
			MongoIterable<String> itr = db.listCollectionNames();
			Iterator<String> s = itr.iterator();
			while(s.hasNext()) {
				System.out.println(s.next());
			}	
			//create collection
			/*db.createCollection("User");
			System.out.println("List of collections after creating User coll:");
			MongoIterable<String> itr1 = db.listCollectionNames();
			Iterator<String> s1 = itr1.iterator();
			while(s1.hasNext()) {
				System.out.println(s1.next());
			}*/
			MongoCollection<Document> collection = db.getCollection("User");
			
			/*Document doc = new Document();
			doc.put("_id", 1234);
			doc.put("name", "pqr");
			doc.put("description", "data insertion2 in collection");
			//insert a doc
			collection.insertOne(doc);*/
			
			//update a doc
			/*Document updatedDoc = new Document();
			updatedDoc.put("$set", new Document("description", "learning doc update"));
			collection.updateOne(new Document("name", "pqr"), updatedDoc);*/ 
			
			//update a doc with array
			/*Document updateDoc = new Document("$addToSet", 
					new Document().append("friends", "rohan2"));
			collection.updateOne(new Document("name", "pqr"), updateDoc);*/
			
			//update - remove an element from array 
			/*Document updateDoc = new Document("$pull", new Document("friends", "rohan2"));
			collection.updateOne(new Document("name", "pqr"), updateDoc);*/
			
			//print all the doc in a collection
			FindIterable<Document> findItr = collection.find();
			Iterator<Document> itr2 = findItr.iterator();
			while(itr2.hasNext()) {
				System.out.println(itr2.next());
			}
		}catch(Exception e) {
			System.out.println("exp:"+e.getMessage());
		}
	}

}
