package com.koushik.myrestapi;

public interface Responselistener {
    void Start();

    void OnSucess(String Responseobject);

    void OnError(String Error);

}
