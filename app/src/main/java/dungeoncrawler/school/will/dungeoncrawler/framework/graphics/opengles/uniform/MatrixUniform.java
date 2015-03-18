package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform;

import static android.opengl.GLES20.glUniformMatrix3fv;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES30.*;

import java.nio.FloatBuffer;

import dungeoncrawler.school.will.dungeoncrawler.framework.util.BufferUtil;

/**
 * @author William Gervasio
 */
public class MatrixUniform extends Uniform {

    public enum MatrixUniformType {

        MATRIX2(4), MATRIX3(9), MATRIX4(16);

        public final int numberOfUniforms;

        MatrixUniformType(int numberOfUniforms) {

            this.numberOfUniforms = numberOfUniforms;
        }
    }

    private final MatrixUniformType uniformType;
    private final FloatBuffer uniformData;

    public MatrixUniform(final String uniformName, final MatrixUniformType uniformType) {

        super(uniformName);

        this.uniformType = uniformType;
        uniformData = BufferUtil.createFloatBuffer(uniformType.numberOfUniforms);
    }

    public final void setUniformData(final float... data) {

        uniformData.put(data, 0, uniformType.numberOfUniforms);
        uniformData.flip();

        updateListeningShaders();
    }

    @Override
    protected final void updateProgram(final int uniformHandle) {

        switch (uniformType) {

            case MATRIX2:
                glUniformMatrix2fv(uniformHandle, uniformType.numberOfUniforms, false, uniformData);
                break;

            case MATRIX3:
                glUniformMatrix3fv(uniformHandle, uniformType.numberOfUniforms, false, uniformData);
                break;

            case MATRIX4:
                glUniformMatrix4fv(uniformHandle, uniformType.numberOfUniforms, false, uniformData);
                break;
        }
    }
}
