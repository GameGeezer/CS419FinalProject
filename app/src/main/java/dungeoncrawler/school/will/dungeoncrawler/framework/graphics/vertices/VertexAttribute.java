package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.vertices;

/**
 * A VertexAttribute is a wrapper for data being used to create a mesh.
 *
 * @author William Gervasio
 */
public abstract class VertexAttribute {

    public final int elementsPerVertex;

    public VertexAttribute(final int elementsPerVertex) {

        this.elementsPerVertex = elementsPerVertex;
    }
    /**
     * Get the vertex attribute data
     *
     * @return The vertex data as a float array
     */
    public abstract float[] getData();

    /**
     * How many indexes in the data make up one vertex
     */
}
