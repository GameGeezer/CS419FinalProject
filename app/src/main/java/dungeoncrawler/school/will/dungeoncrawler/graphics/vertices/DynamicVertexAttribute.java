package dungeoncrawler.school.will.dungeoncrawler.graphics.vertices;


import dungeoncrawler.school.will.dungeoncrawler.util.dataTypes.FloatArrayList;

/**
 * DynamicVertexAttribute is a VertexAttribute that can be added to. This is useful if not all the
 * data for the attribute is known upfront and needs to be built.
 *
 * @author William Gervasio
 */
public class DynamicVertexAttribute implements VertexAttribute {

	private final FloatArrayList vertexData = new FloatArrayList ();
	private final int elementsPerVertex;

	public DynamicVertexAttribute ( final int elementsPerVertex ) {
		this.elementsPerVertex = elementsPerVertex;
	}

	@Override
	public float [] getData () {
		return vertexData.toArray ();
	}

	@Override
	public int getElementsPerVertex () {
		return elementsPerVertex;
	}

	public void addData ( final float... data ) {
        for (float aData : data) {

            vertexData.add(aData);
        }
	}
}