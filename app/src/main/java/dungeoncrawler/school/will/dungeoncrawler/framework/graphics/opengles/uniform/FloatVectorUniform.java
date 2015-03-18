package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform;

import static android.opengl.GLES30.*;
import java.nio.FloatBuffer;

import dungeoncrawler.school.will.dungeoncrawler.framework.util.BufferUtil;

/**
 * @author William Gervasio
 */
public class FloatVectorUniform extends VectorUniform {

    private final FloatBuffer uniformData;

    public FloatVectorUniform(String uniformName, VectorUniformType uniformType) {

        super(uniformName, uniformType);

        uniformData = BufferUtil.createFloatBuffer(uniformType.numberOfUniforms);
    }

    public final void setUniformData(float... data) {

        uniformData.put(data, 0, uniformType.numberOfUniforms);
        uniformData.flip();

        updateListeningShaders();
    }

    @Override
    protected final void updateProgram(int handle) {

        switch (uniformType) {

            case VECTOR1:
                glUniform1fv(handle, uniformType.numberOfUniforms, uniformData);
                break;

            case VECTOR2:
                glUniform2fv(handle, uniformType.numberOfUniforms, uniformData);
                break;

            case VECTOR3:
                glUniform3fv(handle, uniformType.numberOfUniforms, uniformData);
                break;

            case VECTOR4:
                glUniform4fv(handle, uniformType.numberOfUniforms, uniformData);
        }
    }
}
