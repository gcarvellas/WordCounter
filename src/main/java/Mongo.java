import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import java.util.Map;

public class Mongo extends ListenerAdapter {

    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void start(){
        client = MongoClients.create(ConfigParser.getMongoURI());
        MongoDatabase database = client.getDatabase(ConfigParser.getDatabaseName());
        collection = database.getCollection(ConfigParser.getCollectionName());
    }

    @Override
    public void onShutdown(ShutdownEvent e){
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);
        for (Map.Entry<String, Integer> entry: ScoreManager.getScores().entrySet()){
            collection.insertOne(new Document("User", entry.getKey()).append("Score", entry.getValue()));
        }
        System.out.println("Successfully saved to mongo!");
    }

    @Override
    public void onReady(ReadyEvent e){
        ScoreManager.clearList();
        for (Document doc : collection.find()){
            String user = doc.get("User").toString();
            int score = Integer.parseInt(doc.get("Score").toString());
            ScoreManager.addUser(user, score);
        }
        System.out.println("Successfully loaded data from mongo database");
    }
}
