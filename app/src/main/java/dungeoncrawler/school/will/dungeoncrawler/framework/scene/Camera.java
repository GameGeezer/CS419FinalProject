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

    private final Matrix4 view = new Matrix4(), projection = new Matrix4(), position = new Matrix4(), scale = new Matrix4();

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

        projection.data[Matrix4.M00] = xScale;
        projection.data[Matrix4.M11] = yScale;
        projection.data[Matrix4.M22] = -((far + near) / frustumLength);
        projection.data[Matrix4.M23] = -((2 * near * far) / frustumLength);
        projection.data[Matrix4.M32] = -1;
        projection.data[Matrix4.M33] = 0;

        projectionUniform.setUniformData(projection.data);
    }

    public void updateViewUniform() {


        scale.data[Matrix4.M00] = transform.scale.x;
        scale.data[Matrix4.M11] = transform.scale.y;
        scale.data[Matrix4.M22] = transform.scale.z;

        view.set(scale);

        Matrix4.multiply(view, transform.orientation.computeMatrix(), view);

        position.data[Matrix4.M03] = transform.position.x;
        position.data[Matrix4.M13] = transform.position.y;
        position.data[Matrix4.M23] = transform.position.z;

        Matrix4.multiply(view, position, view);

        viewUniform.setUniformData(view.data);
    }
}
