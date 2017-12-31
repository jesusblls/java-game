package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

import java.util.ArrayList;

public class CollisionController implements Observer {

    private Subject ball;
    private ArrayList<GameObject> entities;

    public CollisionController (Subject ball) {
        entities = new ArrayList<GameObject>();
        this.ball = ball;
        ball.register(this);

    }

    public void addEntity (GameObject entity) {
        entities.add(entity);
    }

    public void addEntities (ArrayList<GameObject> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public void update (GameObject gameObject) {
        System.out.println("Collision updated");
    }
}
