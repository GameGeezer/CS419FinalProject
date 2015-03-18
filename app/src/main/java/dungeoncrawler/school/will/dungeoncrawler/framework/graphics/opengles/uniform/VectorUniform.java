package dungeoncrawler.school.will.dungeoncrawler.framework.graphics.opengles.uniform;

/**
 * @author William Gervasio
 */
@SuppressWarnings("WeakerAccess")
public abstract class VectorUniform extends Uniform {

    public enum VectorUniformType {

        VECTOR1(1), VECTOR2(2), VECTOR3(3), VECTOR4(3);

        public final int numberOfUniforms;

        VectorUniformType(int numberOfUniforms) {

            this.numberOfUniforms = numberOfUniforms;
        }
    }

    protected final VectorUniformType uniformType;

    public VectorUniform(final String name, final VectorUniformType uniformType) {

        super(name);

        this.uniformType = uniformType;
    }
}
