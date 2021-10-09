
package com.felder.utils;

public interface IPdf <O, S> {
    
    public void make(O object, S path) throws Exception;
    
}
