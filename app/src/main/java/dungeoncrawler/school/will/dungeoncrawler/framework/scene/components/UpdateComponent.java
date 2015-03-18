package dungeoncrawler.school.will.dungeoncrawler.framework.scene.components;

import dungeoncrawler.school.will.dungeoncrawler.framework.scene.Entity;

/**
 * An UpdateComponent is an EntityComponent that forces an update method to be implemented
 *
 * @author William Gervasio
 */
public abstract class UpdateComponent extends Entity.EntityComponent {

    /**
     * Update should be called once per frame.
     *
     * @param delta The time between frames
     */
    public abstract void update(int delta);
}
