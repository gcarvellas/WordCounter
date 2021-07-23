import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser {

    private static Properties defaultProps;
    private static final String PROP_FILE_NAME = "config.properties";

    public static void loadProperties() {
        try {
            defaultProps = new Properties();
            InputStream in = ConfigParser.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);
            defaultProps.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBotToken(){
        return defaultProps.getProperty("botToken");
    }

    public static String getPrefix(){
        return defaultProps.getProperty("commandPrefix");
    }

    public static String getMongoURI(){
        return defaultProps.getProperty("mongoURI");
    }

    public static String getDatabaseName(){
        return defaultProps.getProperty("database");
    }

    public static String getCollectionName(){
        return defaultProps.getProperty("collection");
    }

    public static String[] getWords(){
        return defaultProps.getProperty("words").split(",");
    }

    public static String getAuthorId(){
        return defaultProps.getProperty("authorId");
    }

}
