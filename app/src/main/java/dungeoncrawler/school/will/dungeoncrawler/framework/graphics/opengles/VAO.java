package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.bufferObjects.IBO;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.bufferObjects.VBO;

import java.util.Map;

import static android.opengl.GLES30.*;

/**
 * The VAO (Vertex Array Object) wraps an VBO and IBO and uses vertex attribute pointers to describe
 * how data in the VBO should be passed to the shader.
 *
 * @author William Gervasio
 */
public class VAO {

    private final VBO vbo;
    private final IBO ibo;

    private final int[] handleBuffer = new int[1];

    public final int size;

    public VAO(final VBO vbo, final IBO ibo, final int size, final Map<Integer, Descriptor> descriptors) {

        this.vbo = vbo;
        this.ibo = ibo;
        this.size = size;

        glGenVertexArrays(1, handleBuffer, 0);

        glBindVertexArray(handleBuffer[0]);

        vbo.bind();
        ibo.bind();

        for (int i : descriptors.keySet()) {

            final Descriptor descriptor = descriptors.get(i);

            // For this VAO assign an attribute at position i in the vertex shader
            glEnableVertexAttribArray(i);
            // Describe the data at position i
            glVertexAttribPointer(i, descriptor.size, descriptor.type, descriptor.normalized,descriptor.stride, descriptor.pointer);
        }

        glBindVertexArray(0);

        vbo.unbind();
        ibo.unbind();
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