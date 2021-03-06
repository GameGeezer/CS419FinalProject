package dungeoncrawler.school.will.dungeoncrawler.framework.util.math;

/**
 * @author William Gervasio
 */
public class Vector2f implements Cloneable {

    public static final Vector2f UP = new Vector2f(0, 1);
    public static final Vector2f DOWN = new Vector2f(0, -1);
    public static final Vector2f RIGHT = new Vector2f(1, 0);
    public static final Vector2f LEFT = new Vector2f(-1, 0);

    public float x, y;

    public Vector2f() {

        this.x = 0;
        this.y = 0;
    }

    public Vector2f(final float x, final float y) {

        this.x = x;
        this.y = y;
    }

    public Vector2f set(final float x, final float y) {

        this.x = x;
        this.y = y;

        return this;
    }

    public Vector2f set(final Vector2f other) {

        this.x = other.x;
        this.y = other.y;

        return this;
    }

    public Vector2f add(final float x, final float y) {

        this.x += x;
        this.y += y;

        return this;
    }

    public static Vector2f add(final Vector2f left, final Vector2f right, final Vector2f result) {

        result.x = left.x + right.x;
        result.y = left.y + right.y;

        return result;
    }

    public Vector2f sub(final float x, final float y) {

        this.x -= x;
        this.y -= y;

        return this;
    }

    public static Vector2f sub(final Vector2f left, final Vector2f right, final Vector2f result) {

        result.x = left.x - right.x;
        result.y = left.y - right.y;

        return result;
    }

    public Vector2f mul(final float x, final float y) {

        this.x *= x;
        this.y *= y;

        return this;
    }

    public static Vector2f mul(final Vector2f left, final Vector2f right, final Vector2f result) {

        result.x = left.x * right.x;
        result.y = left.y * right.y;

        return result;
    }

    public Vector2f div(final float x, final float y) {

        this.x /= x;
        this.y /= y;

        return this;
    }

    public static Vector2f div(final Vector2f left, final Vector2f right, final Vector2f result) {

        result.x = left.x / right.x;
        result.y = left.y / right.y;

        return result;
    }

    public Vector2f scale(final float scalar) {

        this.x *= scalar;
        this.y *= scalar;

        return this;
    }

    public static float dotProduct(final Vector2f vector1, final Vector2f vector2) {

        return (vector1.x * vector2.x) + (vector1.y * vector2.y);
    }

    public float length() {

        return (float) Math.sqrt(length2());
    }

    public float length2() {

        return x * x + y * y;
    }

    public Vector2f normalize() {

        final float length = length();

        x /= length;
        y /= length;

        return this;
    }

    public static Vector2f rotate(final Vector2f vector, final Vector2f axis, final Vector2f result, final double angle) {

        result.set(vector);
        Vector2f.sub(result, axis, result);
        result.rotate(angle);
        Vector2f.add(result, axis, result);

        return result;
    }

    public Vector2f rotate(final double angle) {

        final float xh = x;
        final float yh = y;

        x = (float) (xh * Math.cos(angle) - (yh * Math.sin(angle)));
        y = (float) (xh * Math.sin(angle) + (yh * Math.cos(angle)));

        return this;
    }

    public static void projectAlongX(final Vector2f vector, final Vector2f result) {

        result.set(Vector2f.dotProduct(vector, RIGHT), 0);
    }

    public static void projectAlongY(final Vector2f vector, final Vector2f result) {

        result.set(0, Vector2f.dotProduct(vector, UP));
    }

    public static void project(final Vector2f vector, final Vector2f against, final Vector2f result) {

        final float dp = Vector2f.dotProduct(vector, against);
        final float dpByLength2 = dp / against.length2();

        final float x = dpByLength2 * against.x;
        final float y = dpByLength2 * against.y;

        result.set(x, y);
    }

    public static void findRightHandNormal(final Vector2f vector, final Vector2f resultVector) {

        resultVector.set(-vector.y, vector.x);
    }

    public static void findLeftHandNormal(final Vector2f vector, final Vector2f resultVector) {

        resultVector.set(vector.y, -vector.x);
    }

    public Vector2f clone() {

        return new Vector2f(x, y);
    }

    public Float[] getElements() {

        return new Float[]{x, y};
    }
}
