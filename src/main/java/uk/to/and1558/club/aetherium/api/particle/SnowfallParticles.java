package uk.to.and1558.club.aetherium.api.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.commons.lang3.RandomUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import uk.to.and1558.Plugins.GuiUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a particle effect simulating falling snowflakes.
 * This header must not be removed from this file.
 *
 * @author refactoring
 */
public class SnowfallParticles {
    ScaledResolution res;

    private final List<Particle> particles = new ArrayList<>();

    private final int particleCount;

    /**
     * Constructs a SnowfallParticles instance with the specified number of particles.
     *
     * @param particleCount The number of snowfall particles.
     */
    public SnowfallParticles(int particleCount) {
        this.particleCount = particleCount;
        createParticles();
        res = new ScaledResolution(Minecraft.getMinecraft());
    }

    /**
     * Creates a new instance of SnowfallParticles with the specified number of particles.
     *
     * @param particleCount The number of snowfall particles.
     * @return A new SnowfallParticles instance.
     */
    public static SnowfallParticles create(int particleCount) {
        return new SnowfallParticles(particleCount);
    }

    private void createParticles() {
        for (int i = 0; i < particleCount; i++) {
            float x = RandomUtils.nextFloat(0, Minecraft.getMinecraft().displayWidth);
            float y = RandomUtils.nextFloat(0, Minecraft.getMinecraft().displayHeight);
            float speed = 1.0f + RandomUtils.nextFloat(0, 2);
            particles.add(new Particle(x, y, speed));
        }
    }

    private void update() {
        for (Particle particle : particles) {
            particle.update();
            if (particle.getY() > Minecraft.getMinecraft().displayHeight) {
                particle.reset();
            }
        }
    }

    /**
     * Renders the snowfall particles on the screen.
     * This method should be called once every frame.
     */
    public void render() {
        update();

        for (Particle particle : particles) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.pushMatrix();

            GlStateManager.scale(1f / res.getScaleFactor(), 1f / res.getScaleFactor(), 1f / res.getScaleFactor());
            GuiUtils.drawRoundedRect(
                    particle.getX(), particle.getY(),
                    particle.getX() + 10,  particle.getY() + 10, 10,
                    new Color(255, 255, 255, (int) (particle.getAlpha() * 255)).getRGB()
            );
            GlStateManager.popMatrix();
        }
    }

    private class Particle {

        private float x, y, speed, alpha;

        /**
         * Creates a new particle with the specified position and speed.
         * It updates when the update method is called.
         *
         * @param x The initial x of the particle.
         * @param y The initial y of the particle.
         * @param speed The speed of the particle.
         */
        public Particle(float x, float y, float speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.alpha = 1.0f;
        }

        /**
         * Updates the position.
         */
        public void update() {
            y += speed;

            float fadeStartY = Minecraft.getMinecraft().displayHeight - (Minecraft.getMinecraft().displayHeight / 3);

            if (y > fadeStartY && y < Minecraft.getMinecraft().displayHeight) {
                alpha = 1.0f - (y - fadeStartY) / (Minecraft.getMinecraft().displayHeight - fadeStartY);
            } else if (y >= Minecraft.getMinecraft().displayHeight) {
                alpha = 0.0f;
            }
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getAlpha() {
            return Math.max(alpha, 0.0f);
        }

        /**
         * Resets the position and alpha value of the particle to its initial state.
         */
        public void reset() {
            y = -20;
            x = RandomUtils.nextFloat(0, Minecraft.getMinecraft().displayWidth);
            alpha = 1.0f;
        }
    }
}
