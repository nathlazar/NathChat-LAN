/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class StringNotif{
    
    private StringBuilder stringBuilder;
    private String notifTemplate="<html>"
            + " <body>"
            + "     <div style='width:25px;height:25px'>"
            + "         <div style='width:10px;height:10px[BGCOLOR]'>"
            + "             <p style='color:white;font-size:7px;font-style:bold' align='center'></p>"
            + "         </div>"
            + "     </div>"
            + " </body>"
           +"</html>";
    
    public StringNotif(){
        
        stringBuilder=new StringBuilder();
        generateString();
        
    }
    
    public void generateString(){
        
        stringBuilder.append(
            "<html>"
            + " <body>"
            + "     <div style='width:25px;height:25px'>"
            + "         <div style='width:10px;height:10px'>"
            + "             <p style='color:white;font-size:7px;font-style:bold' align='center'></p>"
            + "         </div>"
            + "     </div>"
            + " </body>"
           +"</html>"
        );
        
    }
    
    public StringBuilder getStringBuilder(int n){
        
        String value="";
        String bg="";
        
        if(n!=0){
            
            value=String.valueOf(n);
            bg=";background-color:red";
        
        }
        
        stringBuilder.delete(stringBuilder.indexOf(stringBuilder.toString()),stringBuilder.toString().length());
        stringBuilder.append(notifTemplate);
        stringBuilder.replace(stringBuilder.indexOf("[BGCOLOR]"),stringBuilder.indexOf("]")+1,bg);
        stringBuilder.replace(stringBuilder.indexOf("'center'")+9,stringBuilder.indexOf("</p>"),value);
        return stringBuilder;
        
    }
    
}
