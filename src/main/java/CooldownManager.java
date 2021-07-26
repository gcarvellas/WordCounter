import java.util.HashSet;

public class CooldownManager {
    private static HashSet<String> cooldownList = new HashSet<String>();

    public static void addCooldown(String user){
        cooldownList.add(user);
    }

    public static void removeCooldown(String user){
        cooldownList.remove(user);
    }

    public static boolean hasUser(String user){
        return cooldownList.contains(user);
    }
}
