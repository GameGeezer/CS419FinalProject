package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices;


import dungeoncrawler.school.will.dungeoncrawler.framework.util.dataTypes.FloatArrayList;

/**
 * DynamicVertexAttribute is a VertexAttribute that can be added to. This is useful if not all the
 * data for the attribute is known upfront and needs to be built.
 *
 * @author William Gervasio
 */
public class DynamicVertexAttribute extends VertexAttribute {

	private final FloatArrayList vertexData = new FloatArrayList();

	public DynamicVertexAttribute(final int elementsPerVertex) {

		super(elementsPerVertex);
	}

	@Override
	public final float[] getData() {

        return vertexData.toArray();
	}

	public final void addData(final float... data) {

        for(float aData : data) {

            vertexData.add(aData);
        }
	}
}
