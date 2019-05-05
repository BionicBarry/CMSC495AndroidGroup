package com.example.samplechatapplication;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/*
this class is model class for message
 */
public class Message implements Serializable {
    @Nullable
    String message;
    @Nullable
    User sender;
    @Nullable
    String createdAt;
}
