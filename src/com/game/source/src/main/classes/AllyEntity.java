package com.game.source.src.main.classes;
import java.awt.Graphics;
import java.awt.Rectangle;

/** Entity type that represents the player and the bullets */
public interface AllyEntity {
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}
