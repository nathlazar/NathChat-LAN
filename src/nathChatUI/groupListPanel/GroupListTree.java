/*
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.groupListPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import nathChatEntities.GroupTreeCellRenderer;
import nathChatUI.NathChatClient;
import static nathChatUI.NathChatClient.core;
import static nathChatUI.NathChatClient.groupListTree;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class GroupListTree extends JTree implements MouseListener,MouseMotionListener{
    
    private TreePath path;
    
    public GroupListTree(DefaultMutableTreeNode treeNode){
        
        super(treeNode);
        this.setCellRenderer(new GroupTreeCellRenderer());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
    }
    
    protected TreePath getTreePath(){
        
        return path;
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        int selRow = this.getRowForLocation(e.getX(),e.getY());
        
        if(selRow==-1){
                            
            core.reloadGroupTree();
                            
        }
                        
    }
                    
    @Override
    public void mouseReleased(MouseEvent e){
                     
        if (e.isPopupTrigger()) {
               
            path=groupListTree.getPathForLocation(e.getX(),e.getY());
            int selRow = groupListTree.getRowForLocation(e.getX(),e.getY());
            if(selRow!=-1&&selRow!=0){
                            
                NathChatClient.groupListTreePopup.show(e.getComponent(), e.getX(), e.getY());
                
            }
            
        }
                        
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        ;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        ;
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        ;
    }
    
    @Override
    public void mouseExited(MouseEvent e){
       
    }
 
    @Override
    public void mouseMoved(MouseEvent e){
       JTree tree=(JTree) e.getSource();
       int selRow=tree.getRowForLocation(e.getX(), e.getY());
       if(selRow==-1){
           tree.clearSelection();
           
       }
       else{
           tree.setSelectionRow(selRow);
           
       }
    }
    
}
