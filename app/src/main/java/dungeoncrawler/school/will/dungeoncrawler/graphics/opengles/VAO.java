package dungeoncrawler.school.will.dungeoncrawler.graphics.opengles;

import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects.IBO;
import dungeoncrawler.school.will.dungeoncrawler.graphics.opengles.bufferObjects.VBO;

import java.util.HashMap;
import java.util.Map;

import static android.opengl.GLES30.*;

/**
 * The VAO (Vertex Array Object) wraps an VBO and IBO and uses vertex attribute pointers to describe
 * how data in the VBO should be passed to the shader.
 *
 * @author William Gervasio
 */
public final class VAO {

    private final Map<Integer, Descriptor> descriptors = new HashMap<>();

    private final VBO vbo;
    private final IBO ibo;

    private final int[] handleBuffer = new int[1];
    private final int size;

    public VAO(VBO vbo, IBO ibo, int size) {

        this.vbo = vbo;
        this.ibo = ibo;
        this.size = size;

        glGenVertexArrays(1, handleBuffer, 0);
    }

    /**
     * Using passed IVertexAttributes
     */
    public final void init() {

        glBindVertexArray(handleBuffer[0]);

        vbo.bind();
        ibo.bind();

        for (int i : descriptors.keySet()) {

            final Descriptor descriptor = descriptors.get(i);

            // For this VAO assign an attribute at position i in the vertex shader
            glEnableVertexAttribArray(i);
            // Describe the data at position i
            glVertexAttribPointer(i, descriptor.getSize(),
                    descriptor.getType(), descriptor.isNormalized(),
                    descriptor.getStride(), descriptor.getPointer());

        }



        glBindVertexArray(0);

        vbo.unbind();
        ibo.unbind();
    }

    /**
     * @param index      The position of the attribute in the vertex shader
     * @param descriptor How the data is formatted
     */
    public final void addVertexAttribute(final int index, final Descriptor descriptor) {

        descriptors.put(index, descriptor);
    }

    /**
     * Draws the elements as triangles
     */
    public final void draw() {

        glBindVertexArray(handleBuffer[0]);

        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
    }

    /**
     * Delete the VertexArray and it's corresponding VBO and IBO
     */
    public final void destroy() {

        // Delete the vertex array
        glDeleteVertexArrays(1, handleBuffer, 0);

        vbo.destroy();

        ibo.destroy();
    }
}