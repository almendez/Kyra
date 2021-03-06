/**************************************************************
 *	file:		PhysicsComponent.java
 *	author:		Andrew King, Anthony Mendez, Ghislain Muberwa
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	
 *
 *	purpose: Abstract class for physics
**************************************************************/
package edu.csupomona.kyra.component.physics;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.physics.objects.Block;
import edu.csupomona.kyra.component.physics.objects.BlockMap;
import edu.csupomona.kyra.component.physics.objects.ForceVector;

public abstract class PhysicsComponent extends Component {
	Polygon polygon;
	float[] points;
	float height, width;
	Line top, bottom, left, right;
	BlockMap blockMap;
	ForceVector forceVector;
	public boolean onFloor;
	
	public PhysicsComponent(String id, float height, float width, TiledMap map)  {
		super(id);
		this.height = height;
		this.width = width;
		blockMap = new BlockMap(map);
	}
	
	//Creates a polygon around the entity
	protected void setPolygon() {
		Vector2f position = owner.getPosition();
		points = new float[] {
				position.x, position.y,
				position.x+width, position.y,
				position.x+width, position.y+height,
				position.x, position.y+height
		};
		polygon = new Polygon(points);
		setLines();
	}
	
	//Sets the lines around the entity
	private void setLines() {
		top = new Line(points[0], points[1], points[2], points[3]);
		bottom = new Line(points[4], points[5], points[6], points[7]);
		left = new Line(points[0], points[1], points[6], points[7]);
		right = new Line(points[2], points[3], points[4], points[5]);
	}
	
	//Returns if there is a intersection of entity's
	public boolean intersections(Shape shape) {
		return polygon.intersects(shape);
	}
	
	//Sees if the entity collides with the floor
	protected boolean testFloorCollision(ForceVector gravity) {
		float polyBottom = bottom.getCenterY();
		Polygon testPoly = new Polygon(points);
		testPoly.setLocation(gravity.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockTop = block.getTop().getCenterY();
			if ((polyBottom < blockTop) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	//Sees if the entity collides with the ceiling
	protected  boolean testCeilingCollision(ForceVector up) {
		float polyTop = top.getCenterY();
		Polygon testPoly = new Polygon(points);
		testPoly.setLocation(up.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockBottom = block.getBottom().getCenterY();
			if ((polyTop > blockBottom) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	//Public facing test for KioskPlayerAI
	public boolean testCollision_LeftWall(ForceVector right) {
		return testLeftWallCollision(right);
	}
	
	//Sees if the entity collides with the left wall
	protected boolean testLeftWallCollision(ForceVector right) {
		float polyRight = this.right.getCenterX();
		Polygon testPoly = new Polygon(points);
		testPoly.setLocation(right.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockLeft = block.getLeft().getCenterX();
			if ((polyRight < blockLeft) && testPoly.intersects(block.getPolygon()))
				return true;
		}
		return false;
	}
	
	//Sees if the entity collides with the right wall
	protected boolean testRightWallCollision(ForceVector left) {
		float polyLeft = this.left.getCenterX();
		Polygon testPoly = new Polygon(points);
		testPoly.setLocation(left.shiftPosition(owner.getPosition()));
		for (Block block : blockMap.getBlocks()) {
			float blockRight = block.getRight().getCenterX();
			if ((polyLeft > blockRight) && testPoly.intersects(block.getPolygon()))
					return true;
		}
		return false;
	}
	
	//Sees for all walls collisions
	protected boolean testWallCollisions(ForceVector force) {
		return testLeftWallCollision(force) || testRightWallCollision(force);
	}
	
	//Test for all collisions
	protected boolean testCollisions(ForceVector force) {
		return testLeftWallCollision(force) ||
			testRightWallCollision(force) ||
			testCeilingCollision(force) ||
			testFloorCollision(force);
	}
	
	/* -- Setters -- */
	
	public void setLocation(Vector2f position) {
		owner.setPosition(position);
		polygon.setLocation(position);
		points = polygon.getPoints();
		setLines();
	}
	
	public void setX(float x) {
		setLocation(new Vector2f(x, polygon.getY()));
	}
	
	public void setY(float y) {
		setLocation(new Vector2f(polygon.getX(), y));
	}
	
	/* -- Getters -- */
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public Line getTop() {
		return top;
	}
	
	public Line getBottom() {
		return bottom;
	}
	
	public Line getLeft() {
		return left;
	}
	
	public Line getRight() {
		return right;
	}
	
	public ForceVector getForceVector() {
		return forceVector;
	}
}
