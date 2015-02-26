package dungeoncrawler.school.will.dungeoncrawler.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.Descriptor;
import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.VAO;
import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects.BufferedObjectUsage;
import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects.IBO;
import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects.VBO;
import dungeoncrawler.school.will.dungeoncrawler.graphics.vertices.VertexAttribute;
import dungeoncrawler.school.will.dungeoncrawler.util.BufferUtil;
import dungeoncrawler.school.will.dungeoncrawler.util.dataTypes.DatatypeUtil;

import static android.opengl.GLES30.*;

/**
 * Given an array if indices and and a List of IVertexAttributes a Mesh generates a VAO.
 *
 * @author william gervasio
 */

public class Mesh {

    private final VAO vao;

    private final List<VertexAttribute> vertexElements;

    /**
     * @param indices The triangles indices of the mesh
     * @param vertexElements A list of all the vertex attributes that will be added to the vbo
     */
    public Mesh(int[] indices, List<VertexAttribute> vertexElements) {

        this.vertexElements = vertexElements;

        //create vertex buffer (VBO)
        FloatBuffer verticesBuffer = BufferUtil.createFloatBuffer(countTotalElements());
        for (VertexAttribute element : vertexElements) {
            verticesBuffer.put(element.getData());
        }
        verticesBuffer.flip();
        final VBO vbo = new VBO(verticesBuffer, BufferedObjectUsage.STATIC_DRAW);

        // Create the Index Buffer (IBO)
        IntBuffer indicesBuffer = BufferUtil.createIntBuffer(indices.length);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
        final IBO ibo = new IBO(indicesBuffer, BufferedObjectUsage.STATIC_DRAW);

        // Create the Vertex Array Object (VAO)
        vao = new VAO(vbo, ibo, indices.length);
        int offset = 0;
        int i = 0;

        for (VertexAttribute element : vertexElements) {
            // Find the number of bytes per element for the attribute
            final int sizeInBytes = element.getElementsPerVertex() * DatatypeUtil.FLOAT_SIZE_BYTES;
            // Find the number of vertices contained within the attribute data
            final int numberOfVertices = element.getData().length / element.getElementsPerVertex();
            vao.addVertexAttribute(i, new Descriptor(element.getElementsPerVertex(), GL_FLOAT, false, sizeInBytes, offset));
            offset += sizeInBytes * numberOfVertices;
            ++i;
        }

        vao.init();
    }

    /**
     * Draw the mesh
     */
    public void draw() {

        vao.draw();
    }

    /**
     * Destroy the VAO
     */
    public void destroy() {

        vao.destroy();
    }

    private int countTotalElements() {

        int totalElementsCount = 0;

        for (VertexAttribute element : vertexElements) {

            totalElementsCount += element.getData().length;
        }

        return totalElementsCount;
    }
}
