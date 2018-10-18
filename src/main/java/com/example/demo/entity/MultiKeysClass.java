package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by qzj on 2018/4/25
 * PatentMonitor的复合主键类
 *
 * @Param phone
 * @Param applicant
 * @Param id
 * 由这三个共同组成复合主键
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiKeysClass implements Serializable {

    private String id;//商品id
    private String username;//评论者

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((username == null) ? 0 : username.hashCode());
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final MultiKeysClass other = (MultiKeysClass)obj;
        if(username == null){
            if(other.username != null){
                return false;
            }
        }else if(!username.equals(other.username)){
            return false;
        }

        if(id == null){
            if(other.id != null){
                return false;
            }
        }else if(!id.equals(other.id)){
            return false;
        }
        return true;
    }

}
