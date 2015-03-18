package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices;

/**
 * A VertexAttribute that cannot be altered. Fastest if all the data for the attribute is known up front.
 *
 * @author William Gervasio
 */
public class StaticVertexAttribute extends VertexAttribute {

    private final float[] data;

    /**
     * @param elementsPerVertex The number of elements  in the array for each vertex.
     * @param data              An array of data for all the vertices.
     */
    public StaticVertexAttribute(final int elementsPerVertex, final float[] data) {

        super(elementsPerVertex);

        this.data = data;
    }

    @Override
    public final float[] getData() {

        return data;
    }
}
