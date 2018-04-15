/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Fneich
 */
public class Parameter {
    private String Key;
    private String Value;

    public Parameter(String Key, String Value) {
        this.Key = Key;
        this.Value = Value;
    }

    public String getKey() {
        return Key;
    }

    public String getValue() {
        return Value;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }
    
}
