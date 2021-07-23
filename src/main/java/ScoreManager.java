import java.util.HashMap;

public class ScoreManager {
    private static HashMap<String, Integer> scores = new HashMap();

    public static void addUser(String user, int score){
        scores.put(user, score);
    }

    public static void addUser(String user){
        if (scores.containsKey(user))
            scores.put(user, scores.get(user) + 1);
        else
            scores.put(user, 1);
    }

    public static HashMap<String, Integer> getScores(){
        return scores;
    }

    public static void clearList(){
        scores = new HashMap();
    }

    public static int getScore(String user){
        return scores.get(user);
    }
}
