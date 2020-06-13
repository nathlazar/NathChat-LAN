/* 
 * Copyright (C) Jonathan Lazar 2019-Present
 * All Rights Reserved 2019
 */
package nathChatUI.menuDialog;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Jonathan Lazar <jonathanlazar17@gmail.com>
 */
public class NathChatMenuDialogController {
    
    private NathChatMenuDialog menuDialog;
    
    protected NathChatMenuDialogController(NathChatMenuDialog menuDialog){
        
        this.menuDialog=menuDialog;
        
    }
    
    protected void userStatusLabelMouseEntr(){
        
        menuDialog.userStatusLabel.setBackground(Color.decode("#28ABB9"));
        setIconOnLabel(menuDialog.userStatusLabel,menuDialog.userStatusLabelIcon,menuDialog.userStatusLabelImage_h);
        
    }
    
    protected void userStatusLabelMouseExt(){
        
        menuDialog.userStatusLabel.setBackground(Color.decode("#EEEEEE"));
        setIconOnLabel(menuDialog.userStatusLabel,menuDialog.userStatusLabelIcon,menuDialog.userStatusLabelImage_uh);
        
    }
    
    protected void showLogLabelMouseEntr(){
        
        menuDialog.showLogLabel.setBackground(Color.decode("#28ABB9"));
        setIconOnLabel(menuDialog.showLogLabel,menuDialog.showLogLabelIcon,menuDialog.showLogLabelImage_h);
        
    }
    
    protected void showLogLabelMouseExt(){
        
        menuDialog.showLogLabel.setBackground(Color.decode("#EEEEEE"));
        setIconOnLabel(menuDialog.showLogLabel,menuDialog.showLogLabelIcon,menuDialog.showLogLabelImage_uh);
        
    }
    
    protected void editProfileLabelMouseEnt(){
        
        menuDialog.editProfileLabel.setBackground(Color.decode("#28ABB9"));
        setIconOnLabel(menuDialog.editProfileLabel,menuDialog.editProfileLabelIcon,menuDialog.editProfileLabelImage_h);
        
    }
    
    protected void editProfileLabelMouseExt(){
        
        menuDialog.editProfileLabel.setBackground(Color.decode("#EEEEEE"));
        setIconOnLabel(menuDialog.editProfileLabel,menuDialog.editProfileLabelIcon,menuDialog.editProfileLabelImage_uh);
        
    }
    
    protected void openRFolderLabelMouseEnt(){
        
        menuDialog.openRFolderLabel.setBackground(Color.decode("#28ABB9"));
        setIconOnLabel(menuDialog.openRFolderLabel,menuDialog.openRFolderLabelIcon,menuDialog.openRFolderLabelImage_h);
        
    }
    
    protected void openRFolderLabelMouseExt(){
        
        menuDialog.openRFolderLabel.setBackground(Color.decode("#EEEEEE"));
        setIconOnLabel(menuDialog.openRFolderLabel,menuDialog.openRFolderLabelIcon,menuDialog.openRFolderLabelImage_uh);
        
    }
    
    protected Image getResizedImageLabel(ImageIcon imageIcon){
        
        Image image=imageIcon.getImage().getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
        return image;
        
    }
    
    protected void setIconOnLabel(JLabel label,ImageIcon imageIcon,Image image){
        
        imageIcon.setImage(image);
        label.setIcon(imageIcon);
        
    }
    
}
