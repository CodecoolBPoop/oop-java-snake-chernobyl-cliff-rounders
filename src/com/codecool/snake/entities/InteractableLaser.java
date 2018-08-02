package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.LaserShooter;

public interface InteractableLaser {

    void apply(LaserShooter laserShooter);

    String getMessage();
}
