package persistence;

import model.LongTerm;
import model.ShortTerm;
import model.WorkUnit;
import org.json.JSONObject; 


import java.io.*;

public class JsonWriter {
     private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of longTerm to file
    public void write(LongTerm longTerm) {
    }

       // MODIFIES: this
    // EFFECTS: writes JSON representation of longTerm to file
    public void write(ShortTerm shortTerm) {
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }
}
