package dungeoncrawler.school.will.dungeoncrawler.framework.util.math;

/**
 * A transform stores an objects position, scale, and orientation
 *
 * @author William Gervasio
 */
public class Transform {

    public Vector3f position = new Vector3f(), scale = new Vector3f();
    public Orientation orientation = new Orientation();

    public Transform() {

    }

    public Transform translate(float x, float y, float z) {

        position.x += x;
        position.y += y;
        position.z += z;

        return this;
    }

    public Transform translate(Vector3f translation) {

        return translate(translation.x, translation.y, translation.z);
    }

    public Transform setTranslation(float x, float y, float z) {

        position.x = x;
        position.y = y;
        position.z = z;

        return this;
    }

    public Transform setTranslation(Vector3f translation) {

        return setTranslation(translation.x, translation.y, translation.z);
    }

    public Transform setScale(float x, float y, float z) {

        scale.x = x;
        scale.y = y;
        scale.z = z;

        return this;
    }

    public Transform setScale(Vector3f scale) {

        return setScale(scale.x, scale.y, scale.z);
    }

    public Transform setOrientationEuler(float roll, float pitch, float yaw) {

        orientation.setEuler(roll, pitch, yaw);

        return this;
    }

    public Transform rotateEuler(float roll, float pitch, float yaw) {

        orientation.rotateEuler(roll, pitch, yaw);

        return this;
    }

    public Transform rotateEuler(Vector3f euler) {

        return rotateEuler(euler.x, euler.y, euler.z);
    }
}
