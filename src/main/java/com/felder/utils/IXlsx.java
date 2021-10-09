package com.felder.utils;

public interface IXlsx<O, S> {

    public void make(O object, S path) throws Exception;
}
