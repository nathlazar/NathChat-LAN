/* 
 * Copyright (C) 2020 Jonathan Lazar <jonathanlazar17@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package serialized;

import nathChatEntities.Constants;

/**
 *
 * @author Lazar
 */
public class NetUser implements java.io.Serializable{
    
    private String ip,hostName;
    private Constants userStatus;
    
    public NetUser(String ip,String hostName){
        
        this.ip=ip;
        this.hostName=hostName;
        
    }
    
    public String getIP(){
        
        return this.ip;
        
    }
    
    public String getHostName(){
        
        return this.hostName;
        
    }
    
    public void setUserStatus(Constants userStatus){
        
        this.userStatus=userStatus;
        
    }
    
    public Constants getUserStatus(){
        
        return this.userStatus;
        
    }
    
}
