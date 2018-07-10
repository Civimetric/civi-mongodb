package mongo;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ConnectMongodb {

	public ConnectMongodb() {
		System.out.println(this.getClass().getName());

		// === open connection ===
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Connection opened");
		
		// === Connect to a database ===
		MongoDatabase db = mongoClient.getDatabase("Civimetric-prep");

		// === Open a collection ===
		MongoCollection<Document> collection = db.getCollection("Option");
		System.out.println("Collection opened");
		// === Query ===
		
		// Bson query = Filters.eq("_id", "P001");
		
		Bson query = Filters.and(
				Filters.eq("name_short", "16-34"),
				Filters.eq("name_long", "16 ans à 34 ans")
				);
		Document option = collection.find(query).first();
		System.out.println(option);
		String category  = option.getString("id_category");
		System.out.println(category);


		// === Save data in Mongodb ===
		
		Document optionToCreate = new Document();
		optionToCreate
		.append("_id", "Option-Amiens-2018-C0001-O02")
		.append("id_category", "Category-Amiens-2018-C0001")
		.append("name_short", "35-64")
		.append("name_long", "35 ans à 64 ans")
		;
		System.out.println(optionToCreate);
		
		collection.insertOne(optionToCreate);
		
		
		// === close connection ===
		mongoClient.close();
		System.out.println("Connection closed");
		
	}
	
	public static void main(String[] args) {
		new ConnectMongodb();
	}

}
