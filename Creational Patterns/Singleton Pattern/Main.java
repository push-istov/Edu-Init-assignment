// Following is a system to handle configurations or global settings for a system
//Singleton pattern allows us to have only one global isntance of the settings

class SettingsManager {
    private static SettingsManager instance = null;
    private static final Object lock = new Object();
    private final java.util.Map<String, Object> config = new java.util.HashMap<>();

    private SettingsManager() {
    }

    public static SettingsManager getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new SettingsManager();
            }
            return instance;
        }
    }

    public void set(String key, Object value) {
        config.put(key, value);
    }

    public Object get(String key) {
        return config.get(key);
    }
}

// Both settings1 and settings2 are the same instance
public class Main {
    public static void main(String[] args) {
        SettingsManager settings1 = SettingsManager.getInstance();
        settings1.set("max_students_per_class", 30);

        SettingsManager settings2 = SettingsManager.getInstance();
        System.out.println(settings2.get("max_students_per_class"));  // Output: 30

        System.out.println(settings1 == settings2);  // Output: true (because they're the same instance)
    }
}
