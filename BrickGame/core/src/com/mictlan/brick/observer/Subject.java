package com.mictlan.brick.observer;

import com.mictlan.brick.entities.GameObject;

public interface Subject {

    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers(GameObject gameObject);
}
