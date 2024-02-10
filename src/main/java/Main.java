import com.mongodb.client.*;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");

        for (String s : mongoClient.listDatabaseNames()) {
            System.out.println(s);
        }

        database.createCollection("customers");
        database.listCollectionNames().forEach(System.out::println);


        //insert
        MongoCollection<Document> collection = database.getCollection("customers");
        Document document = new Document();
        document.put("name", "Javist");
        document.put("age", 24);
        document.put("company", "Picsart");
        collection.insertOne(document);


        //update
        Document query = new Document();
        query.put("name", "Javist");

        Document newDocument = new Document();
        newDocument.put("name", "John");

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);

        //find
        Document query2 = new Document();
        query2.put("age", new Document("$lt", 30));

        FindIterable<Document> result = collection.find(query2);

        for (Document doc : result) {
            System.out.println(doc.toJson());
        }

        //remove
        Document queryRemove = new Document("name", "Javist");

        // Delete the first document that matches the query
        collection.deleteOne(queryRemove);


    }
}