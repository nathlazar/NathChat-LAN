/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatEntities;


import javax.swing.GroupLayout;
/**
 *
 * @author Lazar
 */
public abstract class GroupLayoutManager<V extends GroupLayout.Group,H extends GroupLayout.Group> {
    
    public abstract void layouting();
    
    private V vGroup;
    private H hGroup;
    private GroupLayout layout;
    
    public GroupLayoutManager(GroupLayout layout,V vGroup,H hGroup){
        
        this.vGroup=vGroup;
        this.hGroup=hGroup;
        this.layout=layout;
        
        this.layout.setVerticalGroup(
                                
            this.vGroup
                                
        );
                        
        this.layout.setHorizontalGroup(
                                
            this.hGroup
                                
        );
        
        this.layouting();
        
    }
    
    public V getVGroup(){
        
        return this.vGroup;
        
    }
    
    public H getHGroup(){
        
        return this.hGroup;
        
    }
    
    public GroupLayout getLayout(){
        
        return this.layout;
        
    }
        
    
}
