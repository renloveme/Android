package com.example.filesocket.utils;



public interface Transferable {



    /**
     *
     * @throws Exception
     */
    void init() throws Exception;



    /**
     *
     * @throws Exception
     */
    void parseBody() throws Exception;


    /**
     *
     * @throws Exception
     */
    void finish() throws Exception;
}
