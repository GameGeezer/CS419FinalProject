package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform;

import java.nio.IntBuffer;

import dungeoncrawler.school.will.dungeoncrawler.framework.util.BufferUtil;
import static android.opengl.GLES30.*;

/**
 * @author William Gervasio
 */
@SuppressWarnings("UnusedDeclaration")
public class IntVectorUniform extends VectorUniform {

    private final IntBuffer uniformData;

    public IntVectorUniform(final String uniformName, final VectorUniform.VectorUniformType uniformType) {

        super(uniformName, uniformType);

        uniformData = BufferUtil.createIntBuffer(uniformType.numberOfUniforms);
    }

    public final void setUniformData(final int... data) {

        uniformData.put(data, 0, uniformType.numberOfUniforms);
        uniformData.flip();

        updateListeningShaders();

    }

    @Override
    protected final void updateProgram(final int handle) {

        switch (uniformType) {

            case VECTOR1:
                glUniform1iv(handle, uniformType.numberOfUniforms, uniformData);
                break;

            case VECTOR2:
                glUniform2iv(handle, uniformType.numberOfUniforms, uniformData);
                break;

            case VECTOR3:
                glUniform3iv(handle, uniformType.numberOfUniforms, uniformData);
                break;

            case VECTOR4:
                glUniform4iv(handle, uniformType.numberOfUniforms, uniformData);
        }
    }
}
