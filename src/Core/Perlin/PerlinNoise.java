package Core.Perlin;

import java.util.Random;

/**
 * Created by Ollie on 26/04/14.
 */
public class PerlinNoise {

    private static Random rand = new Random();
    private static long seed;

    public static void setSeed(long seed){
        PerlinNoise.seed = seed;
    }

    public static float Noise2D(float x, float y) {
        long temp = (long) (x * 71 * seed + y * 57 * seed);
        rand.setSeed(temp);
        return (rand.nextFloat() * 2) - 1;
    }

    public static float SmoothNoise2D(float x, float y) {
        float corners = (Noise2D(x - 1, y - 1) + Noise2D(x + 1, y - 1) + Noise2D(x - 1, y + 1) + Noise2D(x + 1, y + 1)) / 16;
        float sides = (Noise2D(x - 1, y) + Noise2D(x + 1, y) + Noise2D(x, y - 1) + Noise2D(x, y + 1)) / 8;
        float center = Noise2D(x, y) / 4;
        return corners + sides + center;
    }

    public static float InterpolatedNoise2D(float x, float y) {
        int iX = (int) x;
        int iY = (int) y;

        float fX = x - iX;
        float fY = y - iY;

        float v1 = SmoothNoise2D(iX, iY);
        float v2 = SmoothNoise2D(iX + 1, iY);
        float v3 = SmoothNoise2D(iX, iY + 1);
        float v4 = SmoothNoise2D(iX + 1, iY + 1);

        return biLerp(fX, fY, v1, v3, v2, v4, 0, 1, 0, 1);
    }

    public static float PerlinNoise_2D(float x, float y) {
        float total = 0;

        double freq;
        double amp;

        for (int i = 0; i < 1; i++) {

            freq = Math.pow(2, i);
            amp = Math.pow(1, i);

            total += InterpolatedNoise2D((float) (x * freq), (float) (y * freq)) * amp;
        }
        return total;
    }

    public static float lerp(float x, float x1, float x2, float q00, float q01) {
        return ((x2 - x) / (x2 - x1)) * q00 + ((x - x1) / (x2 - x1)) * q01;
    }

    public static float biLerp(float x, float y, float q11, float q12, float q21, float q22, float x1, float x2, float y1, float y2) {
        float r1 = lerp(x, x1, x2, q11, q21);
        float r2 = lerp(x, x1, x2, q12, q22);

        return lerp(y, y1, y2, r1, r2);
    }
}
