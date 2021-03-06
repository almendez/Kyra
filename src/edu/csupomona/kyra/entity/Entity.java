/**************************************************************
 *	file:		Entity.java
 *	author:		Anthony Mendez
 *	class:		CS499 - Game Programming
 *
 *	assignment:	Class Project
 *	date last modified:	04/30/2011
 *
 *	purpose: This interface defines the basic fields that any 
 *	entity in the game engine should have.
**************************************************************/


package edu.csupomona.kyra.entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.csupomona.kyra.component.Component;
import edu.csupomona.kyra.component.gun.GunComponent;
import edu.csupomona.kyra.component.health.HealthComponent;
import edu.csupomona.kyra.component.ai.AIComponent;
import edu.csupomona.kyra.component.input.InputComponent;
import edu.csupomona.kyra.component.physics.PhysicsComponent;
import edu.csupomona.kyra.component.physics.objects.Direction;
import edu.csupomona.kyra.component.render.RenderComponent;
import edu.csupomona.kyra.component.sound.SoundComponent;
import edu.csupomona.kyra.state.level.ScoreComponent;

public class Entity {
	String id;
	EntityType type;
	
	Vector2f position;
	Direction xDirection, yDirection;
	float scale;
	float rotation;
	
	
	ArrayList<RenderComponent> renderComponents;
	PhysicsComponent physicsComponent;
	InputComponent inputComponent;
	AIComponent aiComponent;
	SoundComponent soundComponent;
	HealthComponent healthComponent;
	GunComponent gunComponent;
	ScoreComponent scoreComponent;
	
	
	protected ArrayList<Component> components = null;
	
	public Entity(String id, EntityType type) {
		this.id = id;
		this.type = type;
		
		components = new ArrayList<Component>();
		renderComponents = new ArrayList<RenderComponent>();
		
		position = new Vector2f(0, 0);
		scale = 1;
		rotation = 0;
		
		xDirection = Direction.NONE;
		yDirection = Direction.NONE;
	}
	
	public void addComponent(Component component) {
		if (RenderComponent.class.isInstance(component))
			renderComponents.add((RenderComponent)component);
		else if (PhysicsComponent.class.isInstance(component))
			physicsComponent = (PhysicsComponent)component;
		else if (InputComponent.class.isInstance(component))
			inputComponent = (InputComponent)component;
		else if (SoundComponent.class.isInstance(component))
			soundComponent = (SoundComponent)component;
		else if (HealthComponent.class.isInstance(component))
			healthComponent = (HealthComponent)component;
		else if (AIComponent.class.isInstance(component))
			aiComponent = (AIComponent)component;
		else if (GunComponent.class.isInstance(component))
			gunComponent = (GunComponent)component;
		else if (ScoreComponent.class.isInstance(component))
			scoreComponent = (ScoreComponent)component;
		
		component.setOwnerEntity(this);
		components.add(component);
	}
	
	public Component getComponent(String id) {
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				return comp;
		}
		return null;
	}
	
	/* -- Getters -- */
	public Vector2f getPosition() {
		return position;
	}
	
	public float getScale() {
		return scale;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public String getId() {
		return id;
	}
	
	public EntityType getType() {
		return type;
	}
	
	/* -- Setters -- */
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public void setRotation(float rotate) {
		rotation = rotate;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	/* -- Getters -- */
	public ArrayList<RenderComponent> getRenderComponents() {
		return renderComponents;
	}
	
	public PhysicsComponent getPhysicsComponent() {
		return physicsComponent;
	}
	
	public InputComponent getInputComponent() {
		return inputComponent;
	}
	
	public AIComponent getAIComponent() {
		return aiComponent;
	}
	
	public SoundComponent getSoundComponent() {
		return soundComponent;
	}
	
	public HealthComponent getHealthComponent() {
		return healthComponent;
	}
	
	public GunComponent getGunComponent() {
		return gunComponent;
	}
	
	public ScoreComponent getScoreComponent() {
		return scoreComponent;
	}
	
	public Direction getXDirection() {
		return xDirection;
	}
	
	public void setXDirection(Direction xDirection) {
		this.xDirection = xDirection;
	}
	
	public Direction getYDirection() {
		return yDirection;
	}
	
	/* -- Setter -- */
	public void setYDirection(Direction yDirection) {
		this.yDirection = yDirection;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (Component component : components) {
			component.update(gc, sb, delta);
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for (RenderComponent renderComponent : renderComponents)
			renderComponent.render(gc, sb, gr);
		if (gunComponent != null)
			gunComponent.render(gc, sb, gr);
	}
}
