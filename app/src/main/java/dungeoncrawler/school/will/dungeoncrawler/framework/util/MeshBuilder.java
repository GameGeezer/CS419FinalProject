package dungeoncrawler.school.will.dungeoncrawler.framework.util;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.Mesh;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices.DynamicVertexAttribute;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices.VertexAttribute;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.dataTypes.IntArrayList;

import java.util.*;

/**
 * @author William Gervasio
 */
public class MeshBuilder {

	private final Map < String, DynamicVertexAttribute> components = new HashMap < > ();
	private final IntArrayList indices = new IntArrayList ();

	public void addToComponent ( final String name, final float... data ) {

		components.get ( name ).addData ( data );
	}

	public void createComponent ( final String name, final int size ) {

		components.put ( name, new DynamicVertexAttribute ( size ) );

	}

	public void addIndex ( final int index ) {

		indices.add ( index );
	}

	public Mesh build () {

		final List <VertexAttribute> attributes = (List<VertexAttribute>) (List<?>) new ArrayList<>(components.values());

        return new Mesh ( indices.toArray (), attributes );
	}
}
