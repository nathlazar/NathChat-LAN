/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;

import java.io.Serializable;

/**
 *
 * @author Lazar
 */
public class TextPaneElement implements Serializable{
    
    private Constants[] fontAttr;
    private Constants fontFamily;
    private String text;
    
    public TextPaneElement(String text,Constants[] fontAttr,Constants fontFamily){
        
        this.text=text;
        this.fontAttr=fontAttr;
        this.fontFamily=fontFamily;
        
    }
    
    public String getTPEString(){
        
        return this.text;
        
    }
    
    public Constants[] getTPEBIStatus(){
        
        return this.fontAttr;
        
    }
    
    public Constants getTPEFontFamily(){
        
        return this.fontFamily;
        
    }
    
    
}
