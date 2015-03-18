package dungeoncrawler.school.will.dungeoncrawler.framework.scene.components;

import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.ShaderProgram;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.IUniformWrapper;
import dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform.MatrixUniform;
import dungeoncrawler.school.will.dungeoncrawler.framework.scene.Entity;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.exceptions.EntityException;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.math.Matrix4;
import dungeoncrawler.school.will.dungeoncrawler.framework.util.math.Transform;

/**
 * @author William Gervasio
 */
public class TransformComponent extends Entity.EntityComponent implements IUniformWrapper {

    private final MatrixUniform modelUniform = new MatrixUniform(MODEL_UNIFORM, MatrixUniform.MatrixUniformType.MATRIX4);

    private Matrix4 position = new Matrix4(), scale = new Matrix4(), model = new Matrix4();

    public static final String MODEL_UNIFORM = "u_modelMatrix";

    public final Transform transform = new Transform();

    public TransformComponent() {

        updateModelUniform();
    }

    public TransformComponent(Transform other) {

        set(other);
        updateModelUniform();
    }

    @Override
    protected void onAttach() throws EntityException {

        if (getParent().getComponentsOfType(TransformComponent.class).size() > 1) {

            removeSelfFromParent();

            throw new EntityException("Only one TransformComponent may be attached to an entity");
        }
    }

    @Override
    protected void onDetach() {

    }

    @Override
    protected void onComponentAttachedToParent(Entity.EntityComponent component) {

    }

    @Override
    protected void onComponentDetachedFromParent(Entity.EntityComponent component) {

    }

    public TransformComponent set(Transform other) {

        transform.setTranslation(other.position.x, other.position.y, other.position.z);
        transform.setScale(other.scale.x, other.scale.y, other.scale.z);
        transform.setOrientationEuler(other.orientation.getRoll(), other.orientation.getPitch(), other.orientation.getYaw());

        return this;
    }

    @Override
    public void addListener(ShaderProgram shader) {

        modelUniform.addListener(shader);
    }

    @Override
    public void removeListener(ShaderProgram shader) {

        modelUniform.removeListener(shader);
    }

    public void updateModelUniform() {

        scale.data[Matrix4.M00] = transform.scale.x;
        scale.data[Matrix4.M11] = transform.scale.y;
        scale.data[Matrix4.M22] = transform.scale.z;

        model.set(scale);

        Matrix4.multiply(model, transform.orientation.computeMatrix(), model);

        position.data[Matrix4.M03] = transform.position.x;
        position.data[Matrix4.M13] = transform.position.y;
        position.data[Matrix4.M23] = transform.position.z;

        Matrix4.multiply(model, position, model);

        modelUniform.setUniformData(model.data);
    }
}
