package dungeoncrawler.school.will.dungeoncrawler.framework.scene;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.ShaderProgram;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.IUniformWrapper;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.MatrixUniform;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.math.Matrix4;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.math.Transform;

/**
 * @author William Gervasio
 */
public class Camera implements IUniformWrapper {

    public static final String PROJECTION_UNIFORM = "u_projectionMatrix";
    public static final String VIEW_UNIFORM = "u_viewMatrix";

    private final MatrixUniform projectionUniform = new MatrixUniform(PROJECTION_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);
    private final MatrixUniform viewUniform = new MatrixUniform(VIEW_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private final Matrix4 view = new Matrix4(), dummy = new Matrix4();

    public final Transform transform = new Transform();

    public final float width, height, near, far, fieldOfView;

    public Camera(float width, float height, float near, float far, float fieldOfView) {

        this.width = width;
        this.height = height;
        this.near = near;
        this.far = far;
        this.fieldOfView = fieldOfView;

        updateViewUniform();

        updateProjectionUniform();
    }

    @Override
    public void addListener(ShaderProgram shader) {

        projectionUniform.addListener(shader);
        viewUniform.addListener(shader);
    }

    @Override
    public void removeListener(ShaderProgram shader) {

        projectionUniform.removeListener(shader);
        viewUniform.removeListener(shader);
    }

    public void updateProjectionUniform() {

        float aspectRatio = width / height;

        float yScale = (float) (1 / Math.tan(Math.toRadians(fieldOfView / 2)));
        float xScale = yScale / aspectRatio;
        float frustumLength = far - near;

        dummy.loadZero();

        dummy.data[Matrix4.M00] = xScale;
        dummy.data[Matrix4.M11] = yScale;
        dummy.data[Matrix4.M22] = -((far + near) / frustumLength);
        dummy.data[Matrix4.M23] = -((2 * near * far) / frustumLength);
        dummy.data[Matrix4.M32] = -1;
        dummy.data[Matrix4.M33] = 0;

        projectionUniform.setUniformData(dummy.data);
    }

    public void updateViewUniform() {

        dummy.loadZero();

        view.data[Matrix4.M00] = transform.scale.x;
        view.data[Matrix4.M11] = transform.scale.y;
        view.data[Matrix4.M22] = transform.scale.z;

        Matrix4.multiply(view, transform.orientation.computeMatrix(), view);

        dummy.data[Matrix4.M03] = transform.position.x;
        dummy.data[Matrix4.M13] = transform.position.y;
        dummy.data[Matrix4.M23] = transform.position.z;

        Matrix4.multiply(view, dummy, view);

        viewUniform.setUniformData(view.data);
    }
}
