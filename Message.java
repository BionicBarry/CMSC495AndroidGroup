/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BionicBarry
 */
public class Message {
    private String sender, recipient, body;
    private int urgency;
    
    public Message(String s, String r, String b, int u){
        this.sender = s;
        this.recipient = r; 
        this.body = b;
        this.urgency = u;  
    }
}
