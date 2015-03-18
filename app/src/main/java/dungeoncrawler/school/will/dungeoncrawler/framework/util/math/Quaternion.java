package dungeoncrawler.school.will.dungeoncrawler.framework.util.math;

/**
 * @author William Gervasio
 *
 */
public class Quaternion {

    public float x, y, z, w;

    public Quaternion() {
        this(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Quaternion(final float x, final float y, final float z, final float w) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static void multiply(Quaternion left, Quaternion right, Quaternion targer) {

        float x = left.w * right.w - left.x * right.x - left.y * right.y - left.z * right.z;
        float y = left.w * right.x + left.x * right.w + left.y * right.z - left.z * right.y;
        float z = left.w * right.y - left.x * right.z + left.y * right.w + left.z * right.x;
        float w = left.w * right.z + left.x * right.y - left.y * right.x + left.z * right.w;
        targer.x = x;
        targer.y = y;
        targer.z = z;
        targer.w = w;
    }

    public float length2() {

        return x * x + y * y + z * z + w * w;
    }

    public float length() {

        return (float) Math.sqrt(length2());
    }

    public void inverse() {

        float length = length2();

        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public Quaternion normalize() {

        float norm = (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);

        if (norm > 0.0f) {

            this.x /= norm;
            this.y /= norm;
            this.z /= norm;
            this.w /= norm;

        } else {

            this.x = (float) 0.0;
            this.y = (float) 0.0;
            this.z = (float) 0.0;
            this.w = (float) 1.0;
        }

        return this;
    }
}
