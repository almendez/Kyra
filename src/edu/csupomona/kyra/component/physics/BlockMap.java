package edu.csupomona.kyra.component.physics;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
 
public class BlockMap {
	public static TiledMap tmap;
	public static int mapWidth;
	public static int mapHeight;
	private int square[] = {1,1,31,1,31,31,1,31}; //square shaped tile
	public static ArrayList<Object> entities;
 
	public BlockMap(TiledMap map) throws SlickException {
		entities = new ArrayList<Object>();
		tmap = map;
		mapWidth = tmap.getWidth() * tmap.getTileWidth();
		mapHeight = tmap.getHeight() * tmap.getTileHeight();
 
		for (int x = 0; x < tmap.getWidth(); x++) {
			for (int y = 0; y < tmap.getHeight(); y++) {
				int tileID = tmap.getTileId(x, y, 0);
				if (tileID == 1) {
					entities.add(new Block(x * 32, y * 32, square, "square"));
				}
			}
		}
	}
}