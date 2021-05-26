package persistence;

import org.json.JSONObject;

//a writable class will have the method toJson and implement this class
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
