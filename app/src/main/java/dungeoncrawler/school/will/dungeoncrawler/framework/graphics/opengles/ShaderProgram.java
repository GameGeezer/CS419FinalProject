package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles;

import static android.opengl.GLES30.*;

import java.util.Map;

import dungeoncrawler.school.will.dungeoncrawler.framework.util.exceptions.GraphicsException;


/**
 * ShaderPrograms manage shaders for the opengl pipeline.
 *
 * @author William Gervasio
 */
public class ShaderProgram {

    private final int handle;

    /**
     * Create a shader with vertex shader and fragment shader stages.
     *
     * @param vertexShader   The vertex shader source
     * @param fragmentShader The fragment shader source
     * @param attributes A mapping between vertex attribute names and ids
     * @throws GraphicsException
     */
    public ShaderProgram(final String vertexShader, final String fragmentShader, final Map<Integer, String> attributes) throws GraphicsException {

        // Create the shader and grab the handle
        handle = glCreateProgram();

        // Compile the vertex and fragment shaders then grab their handles. Throw an exception if they can't be compiled
        final int vertexHandle = compileShader(vertexShader, GL_VERTEX_SHADER);
        final int fragmentHandle = compileShader(fragmentShader, GL_FRAGMENT_SHADER);

        // Attach the the vertex and fragment shaders to the program.
        glAttachShader(handle, vertexHandle);
        glAttachShader(handle, fragmentHandle);

        // Bind all of the attributes of name(value) to a location(Key)
        for (Map.Entry<Integer, String> e : attributes.entrySet()) {

            glBindAttribLocation(handle, e.getKey(), e.getValue());
        }

        // Try and link the program
        glLinkProgram(handle);

        // Check if there was any errors while linking
        if (checkProgramForLinkError(handle)) {

            throw new GraphicsException("Failed to link shader");
        }

        // Now that the program is created we can free memory
        glDetachShader(handle, vertexHandle);
        glDetachShader(handle, fragmentHandle);
        glDeleteShader(vertexHandle);
        glDeleteShader(fragmentHandle);
    }

    /**
     * Bind the shader
     */
    public final void bind() {

        glUseProgram(handle);
    }

    /**
     * Unbind the shader
     */
    public final void unbind() {

        glUseProgram(0);
    }

    /**
     * Delete the shader
     */
    public final void destroy() {

        glDeleteProgram(handle);
    }

    /**
     * Finds the location of a uniform
     *
     * @param uniform The name of the uniform being located
     * @return The handle to the uniforms location
     */
    public final int getUniformLocation(final String uniform) {

        return glGetUniformLocation(handle, uniform);
    }

    public final int getUniformBlockLocation(final String uniformBlock) {

        return glGetUniformBlockIndex(handle, uniformBlock);
    }

    /**
     * Compiles a vertex, fragment, or geometry shader
     *
     * @param shader The shader script being compiled
     * @param type The type of shader being compiled. i.e. vertex, fragment, or geometry
     * @return A handle to the compiled shader
     * @throws GraphicsException
     */
    public static int compileShader(final String shader, final int type) throws GraphicsException {

        final int handle = glCreateShader(type);

        // Link the source
        glShaderSource(handle, shader);
        // Compile the shader
        glCompileShader(handle);

        // If the shader failed to compile throw an exception
        final int[] compileStatus = new int[1];
        glGetShaderiv(handle, GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == GL_FALSE) {

            switch (type) {

                case GL_VERTEX_SHADER:
                    throw new GraphicsException("Failed to compile vertex shader");

                case GL_FRAGMENT_SHADER:
                    throw new GraphicsException("Failed to compile fragment shader");

                default:
                    // Should never be hit
                    throw new GraphicsException("Failed to compile unknown shader shader");
            }
        }

        return handle;
    }

    /**
     * Check to see if the shader was linked properly
     *
     * @param handle the handle of the program being checked
     * @return True if properly linked
     */
    private boolean checkProgramForLinkError(final int handle) {

        final int[] linkStatus = new int[1];
        glGetProgramiv(handle, GL_LINK_STATUS, linkStatus, 0);

        return linkStatus[0] == GL_FALSE;
    }
}
